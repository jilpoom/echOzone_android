package com.example.echozone_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //로그아웃
        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent(getApplicationContext(),login.class);
                startActivity(logout);
            }
        });
        //즐겨찾기
        ImageView imageView23 = (ImageView) findViewById(R.id.imageView23);
        imageView23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageView23= new Intent(getApplicationContext(),favorite.class);
                startActivity(imageView23);
            }
        });
        //적립내역
        ImageView imageView24 = (ImageView) findViewById(R.id.imageView24);
        imageView24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageView24= new Intent(getApplicationContext(),cashhistory.class);
                startActivity(imageView24);
            }
        });
        //위치
        ImageView imageView25 = (ImageView) findViewById(R.id.imageView25);
        imageView25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageView25 = new Intent(getApplicationContext(),location.class);
                startActivity(imageView25);
            }
        });
        //메인으로
        ImageView imageView18 = (ImageView) findViewById(R.id.imageView18);
        imageView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageView18 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(imageView18);
            }
        });
        //큐알사진
        ImageView btn_qr = (ImageView) findViewById(R.id.btn_qr);
        btn_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btn_qr = new Intent(getApplicationContext(), CreateQR.class);
                startActivity(btn_qr);
            }
        });
    }
}