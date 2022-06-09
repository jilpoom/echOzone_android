package com.example.echozone_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class storemenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storemenu);

        //로그아웃
        Button st_logout = findViewById(R.id.st_logout);
        st_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent st_logout = new Intent(getApplicationContext(),login.class);
                startActivity(st_logout);
            }
        });

        //기계등록
        ImageView machine_me = findViewById(R.id.machine_me);
        machine_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent machine_me = new Intent(getApplicationContext(),Machine.class);
                startActivity(machine_me);
            }
        });

        //업체예약
        ImageView collection = findViewById(R.id.collection);
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent collection = new Intent(getApplicationContext(),date_reservation.class);
                startActivity(collection);
            }
        });

        //수거현황
    ImageView imageView9 = findViewById(R.id.imageView9);
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageView9 = new Intent(getApplicationContext(),info_cap.class);
                startActivity(imageView9);
            }
        });
    }
}