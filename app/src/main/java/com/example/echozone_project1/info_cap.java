package com.example.echozone_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class info_cap extends AppCompatActivity {

    private ImageView info;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private TextView textView25;

    private List<productVO> productInfo = new ArrayList<productVO>();


    //하단바 연결
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_cap);

        textView25 = findViewById(R.id.tv_text);
        info = findViewById(R.id.info80);

        sendRequest();

        Button store1 = (Button) findViewById(R.id.sc_machine1);
        store1.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          Intent intent2 = new Intent(getApplicationContext(), Machine.class);
                                          startActivity(intent2);
                                          overridePendingTransition(0,0);
                                      }
                                  });
                Button calender1 = (Button) findViewById(R.id.sc_calendar1);
                calender1.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {
                                                     Intent intent1 = new Intent(getApplicationContext(), date_reservation.class);
                                                     startActivity(intent1);
                                                     overridePendingTransition(0,0);
                                                 }
                                             });
                        Button home1 = (Button) findViewById(R.id.sc_menu1);
                        home1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent st_menu1 = new Intent(getApplicationContext(), storemenu.class);
                                startActivity(st_menu1);
                                overridePendingTransition(0,0);
                            }
                        });

                        Button sc_home1 = (Button) findViewById(R.id.sc_home1);
        sc_home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sc_home1 = new Intent(getApplicationContext(),info_cap.class);
                startActivity(sc_home1);
                overridePendingTransition(0,0);
            }
        });


    }

    /*     서버 연결 부분 시작     */
    public void sendRequest() {
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 서버에 요청할 주소
        String url = "http://220.80.203.109:8081/web/productInfo.do";

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
                        productInfo = objectMapper.readValue(jsonArray, new TypeReference<List<productVO>>(){});

                        int current_cap = productInfo.get(0).getCurrent_cap();

                        textView25.setText(productInfo.get(0).getCurrent_cap() + "");

                        if(current_cap>=95){
                            info.setImageResource(R.drawable.a95);
                        }
                        else if (current_cap>=90){
                            info.setImageResource(R.drawable.a90);
                        }
                        else if (current_cap>=85){
                            info.setImageResource(R.drawable.a85);
                        }
                        else if (current_cap>=80){
                            info.setImageResource(R.drawable.a80);
                        }
                        else if (current_cap>=75){
                            info.setImageResource(R.drawable.a75);
                        }
                        else if (current_cap>=70){
                            info.setImageResource(R.drawable.a70);
                        }
                        else if (current_cap>=65){
                            info.setImageResource(R.drawable.a65);
                        }
                        else if (current_cap>=60){
                            info.setImageResource(R.drawable.a60);
                        }
                        else if (current_cap>=55){
                            info.setImageResource(R.drawable.a55);
                        }
                        else if (current_cap>=50){
                            info.setImageResource(R.drawable.a50);
                        }
                        else if (current_cap>=45){
                            info.setImageResource(R.drawable.a45);
                        }
                        else if (current_cap>=40){
                            info.setImageResource(R.drawable.a40);
                        }
                        else if (current_cap>=35){
                            info.setImageResource(R.drawable.a35);
                        }
                        else if (current_cap>=30){
                            info.setImageResource(R.drawable.a30);
                        }
                        else if (current_cap>=25){
                            info.setImageResource(R.drawable.a25);
                        }
                        else if (current_cap>=20){
                            info.setImageResource(R.drawable.a20);
                        }
                        else if (current_cap>=15){
                            info.setImageResource(R.drawable.a15);
                        }
                        else if (current_cap>=10){
                            info.setImageResource(R.drawable.a10);
                        }
                        else if (current_cap>=5){
                            info.setImageResource(R.drawable.a5);
                        }
                        else{
                            info.setImageResource(R.drawable.a0);
                        }

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
            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // 서버에 보낼 값
                Map<String, String> params = new HashMap<>();
                String id = LoginCheck2.info.getShop_id();

                params.put("shop_id", id);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    /*      서버 연결 부분 끝      */

}