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

public class CustomListView3 extends BaseAdapter {

    LayoutInflater layoutInflater = null;
    private List<mileageVO> list = null;
    private int count = 0;

    public CustomListView3(List<mileageVO> listData){
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
            convertView = layoutInflater.inflate(R.layout.custom_listview3, parent, false);
        }

        ImageView mainImage = convertView.findViewById(R.id.mainImage);

        TextView title = convertView.findViewById(R.id.title);
        TextView body_1 = convertView.findViewById(R.id.body_1);
        TextView cnt = convertView.findViewById(R.id.tv_count);

        mainImage.setImageResource(list.get(position).mainImage);

        title.setText(list.get(position).title);
        body_1.setText(list.get(position).body_1);
        cnt.setText(list.get(position).cnt);

        return convertView;
    }
}
