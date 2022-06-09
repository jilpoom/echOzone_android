package com.example.echozone_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Machine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);

        Button ma_store = (Button) findViewById(R.id.ma_store);
        ma_store.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent ma_store = new Intent(getApplicationContext(), info_cap.class);
                                            startActivity(ma_store);
                                        }
                                    });
                Button ma_calendar = (Button) findViewById(R.id.ma_calendar);
                ma_calendar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent ma_calendar = new Intent(getApplicationContext(),date_reservation.class);
                        startActivity(ma_calendar);
                    }
                });

            }
}