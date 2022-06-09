package com.example.echozone_project1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cashhistory extends AppCompatActivity {

    private TextView date_number;
    private TextView cash;
    private TextView text_date1;
    private TextView text_date2;
    private Button date_select;
    private ListView listView;
    private DatePickerDialog.OnDateSetListener callbackMethod;

    int cnt;
    int sum;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    private List<mileageVO> mileageList = new ArrayList<mileageVO>();
    private List<mileageVO> empty = new ArrayList<mileageVO>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashhistory);

        Button ca_qrcode = (Button) findViewById(R.id.ca_qrcode);
        ca_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ca_qrcode = new Intent(getApplicationContext(),CreateQR.class);
                startActivity(ca_qrcode);
            }
        });

        Button ca_history = (Button) findViewById(R.id.ca_history);
        ca_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ca_history = new Intent(getApplicationContext(),cashhistory.class);
                startActivity(ca_history);
            }
        });

        Button ca_location = (Button) findViewById(R.id.ca_location);
        ca_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ca_location = new Intent(getApplicationContext(),location.class);
                startActivity(ca_location);
            }
        });


        Button ca_menu = (Button) findViewById(R.id.ca_menu);
        ca_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ca_menu = new Intent(getApplicationContext(),menu.class);
                startActivity(ca_menu);
            }
        });

        Button ca_mypage = (Button) findViewById(R.id.ca_mypage);
        ca_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ca_mypage = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(ca_mypage);
            }
        });
        //------- 하단바 연결 --------

        text_date1 = findViewById(R.id.text_date1);
        text_date2 = findViewById(R.id.text_date2);
        date_number = findViewById(R.id.date_number);
        cash = findViewById(R.id.cash);

        listView = findViewById(R.id.listview);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String today = sdf.format(now);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = sdf1.format(now);


        Log.v("오늘날짜", today);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        String beforeDate = new java.text.SimpleDateFormat("yyyy.MM.dd").format(calendar.getTime());
        String date2 = new java.text.SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

        Log.v("전주날짜", beforeDate);

        // 오류.. 왜 오류남...?
        text_date1.setText((String) beforeDate);
        text_date2.setText((String) today);

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String fmonth = "";
                String fdayOfMonth = "";

                if (month + 1 < 10){
                    fmonth = "0" + (month + 1);
                } else{
                    fmonth = month + "";
                }

                if (dayOfMonth < 10){
                    fdayOfMonth = "0" + dayOfMonth;
                }else{
                    fdayOfMonth = dayOfMonth + "";
                }


                text_date1.setText(year + "." + fmonth + "." + fdayOfMonth);
            }
        }, mYear, mMonth, mDay);

        text_date1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               if(text_date1.isClickable()){
                   datePickerDialog.show();
               }
            }
        });

        text_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text_date2.isClickable()){
                    datePickerDialog.show();
                }
            }
        });

        sendRequest();




    }

    /*     서버 연결 부분 시작     */
    public void sendRequest() {
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 서버에 요청할 주소
        String url = "http://220.80.203.109:8081/web/history.do";

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
                        cnt = mileageList.size();
                        for(int i = 0; i < mileageList.size(); i++){
                            mileageVO vo = new mileageVO();

                            vo.mainImage = R.drawable.historycup;
                            vo.mileage = "+ " + String.valueOf(mileageList.get(i).getUser_mileage()) + "원";

                            vo.title = "적립 - 보증금 환급";
                            vo.body_1 = mileageList.get(i).getMileage_dt();
                            vo.body_2 = mileageList.get(i).getShop_address();
                            vo.body_3 = mileageList.get(i).getShop_nm();

                            empty.add(vo);
                            sum += mileageList.get(i).getUser_mileage();
                        }

                        ListAdapter adapter = new CustomListView2(empty);
                        listView.setAdapter(adapter);

                        date_number.setText(cnt + " 번");

                        DecimalFormat df = new DecimalFormat("###,###");
                        String money = df.format(sum);

                        cash.setText(money + "원");

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
