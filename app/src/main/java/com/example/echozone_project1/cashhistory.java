package com.example.echozone_project1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class cashhistory extends AppCompatActivity {

    private TextView date_number;
    private TextView cash;
    private TextView text_date1;
    private TextView text_date2;
    private Button date_select;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_date1 = findViewById(R.id.text_date1);
        text_date2 = findViewById(R.id.text_date2);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String today = sdf.format(now);

        Log.v("오늘날짜", today);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        String beforeDate = new java.text.SimpleDateFormat("yyyy.MM.dd").format(calendar.getTime());

        Log.v("전주날짜", beforeDate);

        // 오류.. 왜 오류남...?
        // text_date1.setText((String) beforeDate);
        // text_date2.setText((String) today);


    }

}
