package com.example.echozone_project1;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationSource;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class location extends AppCompatActivity implements OnMapReadyCallback{

    private static final String TAG = "location";

    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private ImageView btn_search;
    private EditText edt_search;

    private FusedLocationSource mLocationSource;
    private NaverMap mNaverMap;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;


    Bundle bundle = new Bundle();

    private List<shopVO> placeList = new ArrayList<shopVO>();

    /*     서버 연결 부분 시작     */
    public void sendRequest() {
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 서버에 요청할 주소
        String url = "http://220.80.203.109:8081/web/shopList.do";

        // 요청 시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length() > 0) {
                    Log.v("resultValue", response.toString());
                    // 로그인 성공
                    try {

                        ObjectMapper objectMapper = new ObjectMapper();
                        String jsonArray = response;
                        placeList = objectMapper.readValue(jsonArray, new TypeReference<List<shopVO>>(){});

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // 로그인 실패
                    Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e){
                    // log error
                    return Response.error(new ParseError(e));
                }
            }
        };
        requestQueue.add(stringRequest);
    }
    /*      서버 연결 부분 끝      */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        edt_search = findViewById(R.id.edt_search);
        btn_search = findViewById(R.id.btn_search);

        Geocoder geocoder = new Geocoder(this);
        // 서버 연결
        sendRequest();

        // 프래그먼트 준비
        FragmentManager fm = getSupportFragmentManager();

        // 지도 객체(프래그먼트) 생성(선언)
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);

        if(mapFragment == null){
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        // getMapAsync를 호출하여 비동기로 onMapReady 콜백 메서드 호출
        // onMapReady에서 NaverMap객체를 받음
        mapFragment.getMapAsync(this);

        // 위치를 반환하는 구현체인 FusedLocationSource 생성
        mLocationSource = new FusedLocationSource(this, PERMISSION_REQUEST_CODE);

        // str = "광주 동구 예술길 31-16";

        // btn_search 클릭 시, 프래그먼트로 데이터 전달
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();

                // 1. 입력 메시지
                String strSearch = edt_search.getText().toString();

                // 2. 데이터 담기
                bundle.putString("strSearch", strSearch);

                // 3. 프래그먼트 선언
                MapFragment mapFragment = new MapFragment();

                // 4. 프래그먼트에 데이터 넘기기
                assert mapFragment != null;
                mapFragment.setArguments(bundle);

                // 5. 프래그먼트 화면 보여주기
                transaction.replace(R.id.map, mapFragment).commit();
                mapFragment.getMapAsync(location.this::onMapReady);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (mLocationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)){
            return;
        }
    }

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Log.d(TAG, "onMapReady");

        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        mNaverMap = naverMap;

        mNaverMap.setLocationSource(mLocationSource);
        mNaverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

        // 지도 상에 마커 표시
        for (int i = 0; i < placeList.size(); i++) {
            Marker marker = new Marker();
            marker.setWidth(100);
            marker.setHeight(100);
            marker.setIcon(OverlayImage.fromResource(R.drawable.marker));
            final Geocoder geocoder = new Geocoder(this);
            try {
                List<Address> list = geocoder.getFromLocationName(placeList.get(i).getShop_address(), 10);
                String city = "";
                String country = "";
                if (list.size() == 0){
                    Toast.makeText(getApplicationContext(), "올바른 주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Address address = list.get(0);
                    double lat = address.getLatitude();
                    double lon = address.getLongitude();
                    marker.setPosition(new LatLng(lat, lon));
                    marker.setCaptionText(placeList.get(i).getShop_nm().toString());
                    marker.setMap(mNaverMap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (bundle.getString("strSearch") != null){
                String location = bundle.getString("strSearch");
                try {
                    List<Address> list = geocoder.getFromLocationName(location, 10);
                    String city = "";
                    String country = "";
                    if (list.size() == 0){
                        Toast.makeText(getApplicationContext(), "올바른 주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        Address address = list.get(0);
                        double lat = address.getLatitude();
                        double lon = address.getLongitude();
                        LatLng coord = new LatLng(lat, lon);
                        mNaverMap.moveCamera(CameraUpdate.scrollTo(coord));
                        mNaverMap.setLocationTrackingMode(LocationTrackingMode.None);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 권한확인. 결과는 onRequestPermissionResult 콜백 메서드 호출
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE);
    }

}
