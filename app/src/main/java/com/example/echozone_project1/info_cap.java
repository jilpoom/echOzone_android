package com.example.echozone_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class info_cap extends AppCompatActivity {

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
                                      }
                                  });
                Button calender1 = (Button) findViewById(R.id.sc_calendar1);
                calender1.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {
                                                     Intent intent1 = new Intent(getApplicationContext(), date_reservation.class);
                                                     startActivity(intent1);
                                                 }
                                             });
                        Button home1 = (Button) findViewById(R.id.sc_home1);
                        home1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), join.class);
                                startActivity(intent);
                            }
                        });
                    }
}