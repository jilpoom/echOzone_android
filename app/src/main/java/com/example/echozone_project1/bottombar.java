package com.example.echozone_project1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottombar extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottombar);
    bottomNavigationView=findViewById(R.id.bottomNavigationView);
    bottomNavigationView.setBackground(null);
    }
}