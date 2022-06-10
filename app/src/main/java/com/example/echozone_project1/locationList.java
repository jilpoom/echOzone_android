package com.example.echozone_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class locationList extends AppCompatActivity {

    private TextView tv_location;
    private TextView tv_count;
    private ListView listView;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    private List<shopVO> placeList = new ArrayList<shopVO>();
    private List<shopVO> empty = new ArrayList<shopVO>();

    /*     서버 연결 부분 시작     */
    public void sendRequest() {
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 서버에 요청할 주소
        String url = "http://220.80.203.109:8081/web/searchList.do";

        // 요청 시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length() > 0) {
                    Log.v("resultValue", response.toString());
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        String jsonArray = response;
                        placeList = objectMapper.readValue(jsonArray, new TypeReference<List<shopVO>>(){});

                        for(int i = 0; i < placeList.size(); i++){
                            shopVO vo = new shopVO();

                            vo.mainImage = R.drawable.historycup;
                            vo.gps = R.drawable.ic_location;

                            vo.title = placeList.get(i).getShop_nm();
                            vo.body_1 = placeList.get(i).getShop_address();

                            empty.add(vo);
                        }

                        ListAdapter adapter = new CustomListView(empty);
                        listView.setAdapter(adapter);
                        tv_count.setText("총 " + placeList.size() + "건");

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

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

                Intent intent = getIntent();
                String shop_address = intent.getStringExtra("shop_address");
                tv_location.setText(shop_address);

                params.put("shop_address", shop_address);

                Log.v("sendValueCheck", shop_address);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    /*      서버 연결 부분 끝      */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        tv_location = findViewById(R.id.tv_location);
        tv_count = findViewById(R.id.tv_count);
        listView = findViewById(R.id.listView);

        ImageView list_cancle = (ImageView) findViewById(R.id.list_cancle);
        list_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent list_cancle = new Intent(getApplicationContext(),location.class);
                startActivity(list_cancle);
                overridePendingTransition(0,0);
            }
        });
        sendRequest();
    }
}