package com.example.echozone_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Machine extends AppCompatActivity {

    //하단바 연결
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);

        Button ma_machine = (Button) findViewById(R.id.ma_machine);
        ma_machine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ma_machine = new Intent(getApplicationContext(), Machine.class);
                startActivity(ma_machine);
                overridePendingTransition(0,0);
            }
        });
        Button ma_calendar = (Button) findViewById(R.id.ma_calendar);
        ma_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ma_calendar = new Intent(getApplicationContext(),date_reservation.class);
                startActivity(ma_calendar);
                overridePendingTransition(0,0);
            }
        });
        Button ma_store = (Button) findViewById(R.id.ma_store);
        ma_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ma_store = new Intent(getApplicationContext(),info_cap.class);
                startActivity(ma_store);
                overridePendingTransition(0,0);
            }
        });

        Button ma_menu = (Button) findViewById(R.id.ma_menu);
        ma_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ma_menu = new Intent(getApplicationContext(),storemenu.class);
                startActivity(ma_menu);
                overridePendingTransition(0,0);
            }
        });
            }
}