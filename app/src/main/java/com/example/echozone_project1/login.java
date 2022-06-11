package com.example.echozone_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    private EditText user_id;
    private EditText user_pw;
    private Button btn_login;
    private CheckBox ck_saveId;
    private CheckBox ck_autoLogin;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private TextView tv_join;
    private SwitchCompat sw_type;
    private int cnt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_id = findViewById(R.id.user_id);
        user_pw = findViewById(R.id.user_pw);
        btn_login = findViewById(R.id.btn_login);
        ck_saveId = findViewById(R.id.ck_saveId);
        ck_autoLogin = findViewById(R.id.ck_autoLogin);
        tv_join = findViewById(R.id.tv_join);
        sw_type = findViewById(R.id.sw_type);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sw_type.isChecked()) {
                    sendRequest();
                }else {
                    sendRequest2();
                }
            }
        });

        tv_join.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), join.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        sw_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnt++;
                if(cnt % 2 == 0) {
                    sw_type.setText("가맹  ");
                } else {
                    sw_type.setText("일반  ");
                }
            }
        });
    }

    public void sendRequest() {
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 서버에 요청할 주소
        String url = "http://220.80.203.109:8081/web/andLoginSelect.do";

        // 요청 시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length() > 0) {
                    Log.v("checkcheck", "메롱");
                    // 로그인 성공
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String user_id = jsonObject.getString("user_id");
                        String user_pw = jsonObject.getString("user_pw");
                        String user_type = jsonObject.getString("user_type");
                        String user_phone = jsonObject.getString("user_phone");
                        String user_address = jsonObject.getString("user_address");
                        String user_nm = jsonObject.getString("user_nm");
                        String user_joindate = jsonObject.getString("user_joindate");
                        Log.v("userInfo", response.toString());

                        // 로그인 성공 시 id, pw, user_seq, type, phone, address, name, joinDate 데이터를
                        // MainActivity로 전달해서 정보 노출시키기
                        // MemberVO 사용
                        LoginCheck.info = new userVO(user_id, user_pw, user_type, user_phone, user_address,
                                                        user_nm, user_joindate);
                        Toast.makeText(getApplicationContext(), user_nm + "님 환영합니다.", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "아이디/비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 로그인 실패
                    Toast.makeText(getApplicationContext(), "아이디/비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "아이디/비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
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
                String id = user_id.getText().toString().trim();
                String pw = user_pw.getText().toString().trim();

                params.put("user_id", id);
                params.put("user_pw", pw);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void sendRequest2() {
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 서버에 요청할 주소
        String url = "http://220.80.203.109:8081/web/andLoginSelectShop.do";

        // 요청 시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length() > 0) {
                    Log.v("checkcheck", "메롱");
                    // 로그인 성공
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String shop_id = jsonObject.getString("shop_id");
                        String shop_pw = jsonObject.getString("shop_pw");
                        int product_seq = Integer.parseInt(jsonObject.getString("product_seq"));
                        String inst_dt = jsonObject.getString("inst_dt");
                        String shop_nm = jsonObject.getString("shop_nm");
                        String shop_address = jsonObject.getString("shop_address");
                        Log.v("shopInfo", response.toString());

                        // 로그인 성공 시 id, pw, user_seq, type, phone, address, name, joinDate 데이터를
                        // MainActivity로 전달해서 정보 노출시키기
                        // MemberVO 사용
                        LoginCheck2.info = new shopVO(shop_id, shop_pw, product_seq, inst_dt, shop_nm,
                                shop_address);
                        Toast.makeText(getApplicationContext(), shop_nm + "님 환영합니다.", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), info_cap.class);
                            startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "아이디/비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 로그인 실패
                    Toast.makeText(getApplicationContext(), "아이디/비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "아이디/비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
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
                String id = user_id.getText().toString().trim();
                String pw = user_pw.getText().toString().trim();

                params.put("user_id", id);
                params.put("user_pw", pw);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}