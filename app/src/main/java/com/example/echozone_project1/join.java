package com.example.echozone_project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.util.HashMap;
import java.util.Map;

public class join extends AppCompatActivity {

    private EditText join_id;
    private EditText join_pw;
    private EditText join_name;
    private EditText join_phone;
    private EditText join_address;
    private RadioGroup join_type;
    private Button btn_signUp;
    private RadioButton type_result;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        join_id = findViewById(R.id.join_id);
        join_pw = findViewById(R.id.join_pw);
        join_name = findViewById(R.id.join_name);
        join_phone = findViewById(R.id.join_phone);
        join_address = findViewById(R.id.join_address);
        join_type = (RadioGroup) findViewById(R.id.join_type);
        btn_signUp = findViewById(R.id.btn_signUp);

        int id = join_type.getCheckedRadioButtonId();
        type_result = (RadioButton) findViewById(id);

        Log.v("RadioButtonCheck", type_result.getText().toString());

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });
    }

    public void sendRequest(){
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url = "http://220.80.203.109:8081/web/joinInsert.do";

        // 요청 시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length() > 0) {
                    Intent intent = new Intent(getApplicationContext(), login.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            // 포스트 파라미터
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("user_id", join_id.getText().toString().trim());
                params.put("user_pw", join_pw.getText().toString().trim());
                params.put("user_type", type_result.getText().toString().trim());
                params.put("user_phone", join_phone.getText().toString().trim());
                params.put("user_address", join_address.getText().toString().trim());
                params.put("user_nm", join_name.getText().toString().trim());

                Log.v("test", params.toString());

                return params;
            }
        };

        // Add the request to the RequestQueue
        requestQueue.add(stringRequest);
    }
}