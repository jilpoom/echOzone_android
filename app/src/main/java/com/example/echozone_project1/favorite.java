package com.example.echozone_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
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

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class favorite extends AppCompatActivity {

    private TextView tv_first_nm, tv_second_nm, tv_third_nm;
    private TextView tv_first_cnt, tv_second_cnt, tv_third_cnt;
    private ListView favoriteList;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    private List<mileageVO> mileageList = new ArrayList<mileageVO>();
    private List<mileageVO> empty = new ArrayList<mileageVO>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        Button fa_qrcode = (Button) findViewById(R.id.fa_qrcode);
        fa_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fa_qrcode = new Intent(getApplicationContext(),CreateQR.class);
                startActivity(fa_qrcode);
                overridePendingTransition(0,0);
            }
        });

        Button fa_history = (Button) findViewById(R.id.fa_history);
        fa_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fa_history = new Intent(getApplicationContext(),cashhistory.class);
                startActivity(fa_history);
                overridePendingTransition(0,0);
            }
        });

        Button fa_location = (Button) findViewById(R.id.fa_location);
        fa_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fa_location = new Intent(getApplicationContext(),location.class);
                startActivity(fa_location);
                overridePendingTransition(0,0);
            }
        });

      Button fa_mypage = (Button) findViewById(R.id.fa_mypage);
        fa_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fa_mypage = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(fa_mypage);
                overridePendingTransition(0,0);
            }
        });

        Button fa_menu = (Button) findViewById(R.id.fa_menu);
        fa_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fa_menu = new Intent(getApplicationContext(),menu.class);
                startActivity(fa_menu);
                overridePendingTransition(0,0);
            }
        });
        // 하단바 연결

        tv_first_nm = findViewById(R.id.tv_first_nm);
        tv_second_nm = findViewById(R.id.tv_second_nm);
        tv_third_nm = findViewById(R.id.tv_third_nm);
        tv_first_cnt = findViewById(R.id.tv_first_cnt);
        tv_second_cnt = findViewById(R.id.tv_second_cnt);
        tv_third_cnt = findViewById(R.id.tv_third_cnt);
        favoriteList = findViewById(R.id.favoriteList);

        sendRequest();

    }

    /*     서버 연결 부분 시작     */
    public void sendRequest() {
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 서버에 요청할 주소
        String url = "http://220.80.203.109:8081/web/favoriteList.do";

        // 요청 시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length() > 0) {
                    Log.v("resultValue", response.toString());
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        String jsonArray = response;
                        mileageList = objectMapper.readValue(jsonArray, new TypeReference<List<mileageVO>>(){});
                        for(int i = 0; i < mileageList.size(); i++){

                            mileageVO vo = new mileageVO();
                            vo.mainImage = R.drawable.favoritelocation;

                            vo.title = mileageList.get(i).getShop_nm();
                            vo.body_1 = mileageList.get(i).getShop_address();
                            vo.cnt = mileageList.get(i).getCount() + "";

                            empty.add(vo);
                            /*
                            switch (i){
                                case (0):
                                    tv_first_nm.setText(mileageList.get(0).getShop_nm());
                                    tv_first_cnt.setText(mileageList.get(0).getCount());
                                case (1):
                                    tv_second_nm.setText(mileageList.get(1).getShop_nm());
                                    tv_second_cnt.setText(mileageList.get(1).getCount());
                                case (2):
                                    tv_third_nm.setText(mileageList.get(2).getShop_nm());
                                    tv_third_cnt.setText(mileageList.get(2).getCount());
                            } */

                        }

                        ListAdapter adapter = new CustomListView3(empty);
                        favoriteList.setAdapter(adapter);

                        favoriteList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                String clickName = empty.get(position).title;
                                Log.d("확인", "name : " + clickName);
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "검색결과가 없습니다.", Toast.LENGTH_SHORT).show();
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

                // 나중에 user_id를 LoginCheck.info.getId()를 통해 가져오도록 수정
                // String user_id = LoginCheck.info.getId();

                params.put("user_id", "nmmng03");

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    /*      서버 연결 부분 끝      */

}