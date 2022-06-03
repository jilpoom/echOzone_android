package com.example.echozone_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
// GIF add library+
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
// GIF add libary -
public class logoscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logoscreen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent (getApplicationContext(),login.class);
                startActivity(intent);
                finish();
            }
        },4500);
        ImageView water = (ImageView) findViewById(R.id.water);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(water);
        Glide.with(this).load(R.drawable.screen).into(gifImage);
// GIF 시작화면 실행
}
    }