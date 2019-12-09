package com.example.danyaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;

public class mainAdapter extends ArrayAdapter<Slot1> {
    private Context mContext;
    private int mRecource;
    public mainAdapter(Context Context, int Recource, ArrayList<Slot1> objects){
        super(Context,Recource,objects);
        mContext = Context;
        mRecource = Recource;
    }
    public View getView(int position, View v, ViewGroup parent){
        String title = getItem(position).getTitle();
        int img = getItem(position).getImg();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(mRecource,parent,false);
        TextView Title = v.findViewById(R.id.title);
        ImageView Img = v.findViewById(R.id.imageView);
        Img.setImageResource(img);
        Title.setText(title);
        return v;
    }
}
