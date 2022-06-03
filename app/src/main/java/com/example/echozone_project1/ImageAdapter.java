package com.example.echozone_project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class ImageAdapter extends ArrayAdapter<String> {
  ImageAdapter(Context context, String[] items){
  super(context,R.layout.item,items);}

  @NonNull
  @Override
  public View getView(int position, View convertView, ViewGroup parent){
    LayoutInflater imageInflater = LayoutInflater.from(getContext());
    View view = imageInflater.inflate(R.layout.item,parent,false);
    String item = getItem(position);

    TextView textView = (TextView) view.findViewById(R.id.textView);
    ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
    textView.setText(item);
    return view;
  }
}
