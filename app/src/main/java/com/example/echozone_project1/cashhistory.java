package com.example.echozone_project1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class cashhistory extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        String[] items = {"망고 쥬스", "토마토 쥬스", "포토 쥬스"};

        // 리스트 어탭터 - 실제로 리스트에 들어갈 내용을 담을

        ListAdapter adapter = new ImageAdapter(this, items);



        ListView listView = (ListView) findViewById(R.id.listview);

        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int i, long id){

                String item = String.valueOf(parent.getItemAtPosition(i));

                Toast.makeText(cashhistory.this, item, Toast.LENGTH_SHORT).show();

            }

        });

    }

}
