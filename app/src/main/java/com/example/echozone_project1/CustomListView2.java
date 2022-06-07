package com.example.echozone_project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomListView2 extends BaseAdapter {

    LayoutInflater layoutInflater = null;
    private List<mileageVO> list = null;
    private int count = 0;

    public CustomListView2(List<mileageVO> listData){
        list = listData;
        count = list.size();
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            final Context context = parent.getContext();
            if (layoutInflater == null)
            {
                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = layoutInflater.inflate(R.layout.custom_listview2, parent, false);
        }

        ImageView mainImage = convertView.findViewById(R.id.mainImage);

        TextView mileage = convertView.findViewById(R.id.mileage);
        TextView title = convertView.findViewById(R.id.title);
        TextView body_1 = convertView.findViewById(R.id.body_1);
        TextView body_2 = convertView.findViewById(R.id.body_2);
        TextView body_3 = convertView.findViewById(R.id.body_3);



        mainImage.setImageResource(list.get(position).mainImage);

        mileage.setText(list.get(position).mileage);
        title.setText(list.get(position).title);
        body_1.setText(list.get(position).body_1);
        body_2.setText(list.get(position).body_2);
        body_3.setText(list.get(position).body_3);

        return convertView;
    }
}
