package com.example.echozone_project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class date_reservation extends AppCompatActivity {

    private CalendarView calendarView;
    private EditText edt_searchText;
    private TextView tv_result1;
    private TextView tv_result2;
    private ImageView btn_click;

    //하단바연결
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_reservation);

        Button da_back = (Button) findViewById(R.id.da_back);
        da_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent da_back = new Intent(getApplicationContext(),storemenu.class);
                startActivity(da_back);
            }
        });

        calendarView = findViewById(R.id.calendarView);
        edt_searchText = findViewById(R.id.edt_searchText);
        tv_result1 = findViewById(R.id.tv_result1);
        tv_result2 = findViewById(R.id.tv_result2);
        btn_click = findViewById(R.id.btn_click);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                tv_result2.setText(String.format("%d년 %d월 %d일", year, month + 1, dayOfMonth));
            }
        });

        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_result1.setText(edt_searchText.getText());
            }
        });



    }
}