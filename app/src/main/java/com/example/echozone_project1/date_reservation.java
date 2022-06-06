package com.example.echozone_project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

public class date_reservation extends AppCompatActivity {

    private CalendarView calendarView;
    private EditText edt_searchText;
    private TextView tv_result1;
    private TextView tv_result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_reservation);

        calendarView = findViewById(R.id.calendarView);
        edt_searchText = findViewById(R.id.edt_searchText);
        tv_result1 = findViewById(R.id.tv_result1);
        tv_result2 = findViewById(R.id.tv_result2);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                tv_result2.setText(String.format("%d년 %d월 %d일", year, month, dayOfMonth));
            }
        });



    }
}