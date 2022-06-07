package com.example.echozone_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
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

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView user_name;
    private TextView user_mileage;
    private TextView tv_date;
    private TextView tv_recently;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    private TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6, tv_7, tv_8, tv_9, tv_10, tv_11, tv_12;

    private List<mileageVO> mileageVOList = new ArrayList<mileageVO>();

    /*     서버 연결 부분 시작 (회원정보 가져오기)     */
    public void sendRequest() {
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 서버에 요청할 주소
        String url = "http://220.80.203.109:8081/web/mileageList.do";

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
                        mileageVOList = objectMapper.readValue(jsonArray, new TypeReference<List<mileageVO>>(){});

                        for(int i = 0; i < 5; i++){
                            switch (i){
                                case 1:
                                    tv_1.setText(mileageVOList.get(1).getMileage_dt());
                                    tv_2.setText(mileageVOList.get(1).getShop_nm());
                                    tv_3.setText(mileageVOList.get(1).getShop_address());
                                    break;
                                case(2):
                                    tv_4.setText(mileageVOList.get(2).getMileage_dt());
                                    tv_5.setText(mileageVOList.get(2).getShop_nm());
                                    tv_6.setText(mileageVOList.get(2).getShop_address());
                                    break;
                                case(3):
                                    tv_7.setText(mileageVOList.get(3).getMileage_dt());
                                    tv_8.setText(mileageVOList.get(3).getShop_nm());
                                    tv_9.setText(mileageVOList.get(3).getShop_address());
                                    break;
                                case(4):
                                    tv_10.setText(mileageVOList.get(4).getMileage_dt());
                                    tv_11.setText(mileageVOList.get(4).getShop_nm());
                                    tv_12.setText(mileageVOList.get(4).getShop_address());
                                    break;
                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "정보조회 실패", Toast.LENGTH_SHORT).show();
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
            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // 서버에 보낼 값
                Map<String, String> params = new HashMap<>();
                String user_id = LoginCheck.info.getId();

                params.put("user_id", user_id);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    /*      서버 연결 부분 끝      */

    /*     서버 연결 부분 시작 (적립내역)      */
    public void sendRequest1() {
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 서버에 요청할 주소
        String url = "http://220.80.203.109:8081/web/mileageList.do";

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
                        mileageVOList = objectMapper.readValue(jsonArray, new TypeReference<List<mileageVO>>(){});

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "정보조회 실패", Toast.LENGTH_SHORT).show();
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
            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // 서버에 보낼 값
                Map<String, String> params = new HashMap<>();
                String user_id = LoginCheck.info.getId();

                params.put("user_id", user_id);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_name = findViewById(R.id.user_name);
        user_mileage = findViewById(R.id.user_mileage);
        tv_date = findViewById(R.id.tv_date);
        tv_recently = findViewById(R.id.tv_recently);

        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);
        tv_4 = findViewById(R.id.tv_4);
        tv_5 = findViewById(R.id.tv_5);
        tv_6 = findViewById(R.id.tv_6);
        tv_7 = findViewById(R.id.tv_7);
        tv_8 = findViewById(R.id.tv_8);
        tv_9 = findViewById(R.id.tv_9);
        tv_10 = findViewById(R.id.tv_10);
        tv_11 = findViewById(R.id.tv_11);
        tv_12 = findViewById(R.id.tv_12);


        user_name.setText(LoginCheck.info.getName());

        sendRequest();

    }
}