package com.example.echozone_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    private EditText user_id;
    private EditText user_pw;
    private Button bnt_login;
    private CheckBox ck_saveId;
    private CheckBox ck_autoLogin;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_id = findViewById(R.id.user_id);
        user_pw = findViewById(R.id.user_pw);
        bnt_login = findViewById(R.id.btn_login);
        ck_saveId = findViewById(R.id.ck_saveId);
        ck_autoLogin = findViewById(R.id.ck_autoLogin);

        bnt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });
    }

    public void sendRequest() {
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 서버에 요청할 주소
        String url = "http://220.80.203.109:8081/web/loginSelect.do";

        // 요청 시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("resultValue", response);
                if (response.length() > 0) {
                    // 로그인 성공
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String id = jsonObject.getString("user_id");
                        String pw = jsonObject.getString("user_pw");
                        int user_seq = jsonObject.getInt("user_seq");
                        String type = jsonObject.getString("user_type");
                        String phone = jsonObject.getString("user_phone");
                        String address = jsonObject.getString("user_address");
                        String name = jsonObject.getString("user_nm");
                        String joinDate = jsonObject.getString("user_joindate");
                        Log.v("resultValue", id + "/" + pw + "/");

                        // 로그인 성공 시 id, pw, user_seq, type, phone, address, name, joinDate 데이터를
                        // MainActivity로 전달해서 정보 노출시키기
                        // MemberVO 사용
                        LoginCheck.info = new userVO(id, pw, type, phone, address,
                                                        name, joinDate);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    } catch (JSONException e) {
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
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }

            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // 서버에 보낼 값
                Map<String, String> params = new HashMap<>();
                String id = user_id.getText().toString();
                String pw = user_pw.getText().toString();

                params.put("id", id);
                params.put("pw", pw);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}