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
import java.util.List;

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

        Button store1= (Button) findViewById(R.id.sc_machine1);
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
        ImageView info = findViewById(R.id.info80);
        textView25 = findViewById(R.id.tv_text);

        sendRequest();

        int tv_text = Integer.parseInt((String) textView25.getText());
                        if(tv_text>=95){
                            info.setImageResource(R.drawable.a95);
                        }
                        else if (tv_text>=90){
                            info.setImageResource(R.drawable.a90);
        }
                        else if (tv_text>=85){
                            info.setImageResource(R.drawable.a85);
                        }
                        else if (tv_text>=80){
                            info.setImageResource(R.drawable.a80);
                        }
                        else if (tv_text>=75){
                            info.setImageResource(R.drawable.a75);
                        }
                        else if (tv_text>=70){
                            info.setImageResource(R.drawable.a70);
                        }
                        else if (tv_text>=65){
                            info.setImageResource(R.drawable.a65);
                        }
                        else if (tv_text>=60){
                            info.setImageResource(R.drawable.a60);
                        }
                        else if (tv_text>=55){
                            info.setImageResource(R.drawable.a55);
                        }
                        else if (tv_text>=50){
                            info.setImageResource(R.drawable.a50);
                        }
                        else if (tv_text>=45){
                            info.setImageResource(R.drawable.a45);
                        }
                        else if (tv_text>=40){
                            info.setImageResource(R.drawable.a40);
                        }
                        else if (tv_text>=35){
                            info.setImageResource(R.drawable.a35);
                        }
                        else if (tv_text>=30){
                            info.setImageResource(R.drawable.a30);
                        }
                        else if (tv_text>=25){
                            info.setImageResource(R.drawable.a25);
                        }
                        else if (tv_text>=20){
                            info.setImageResource(R.drawable.a20);
                        }
                        else if (tv_text>=15){
                            info.setImageResource(R.drawable.a15);
                        }
                        else if (tv_text>=10){
                            info.setImageResource(R.drawable.a10);
                        }
                        else if (tv_text>=5){
                            info.setImageResource(R.drawable.a5);
                        }
                        else{
                            info.setImageResource(R.drawable.a0);
                        }
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
                        textView25.setText(productInfo.get(0).getCurrent_cap());

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

}