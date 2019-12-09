package com.example.danyaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class slot2Adapter extends ArrayAdapter<Slot2> {
    private Context mContext;
    private int mRecource;
    public slot2Adapter(Context Context, int Recource, ArrayList<Slot2> objects){
        super(Context,Recource,objects);
        mContext = Context;
        mRecource = Recource;
    }
    public View getView(int position, View v, ViewGroup parent){
        String title = getItem(position).getKorztitle();
        int img = getItem(position).getKorzimageView();
        String opisanie = getItem(position).getKorzopisanie();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(mRecource,parent,false);
        TextView Title = v.findViewById(R.id.korztitle);
        ImageView Img = v.findViewById(R.id.korzimageView);
        TextView Opisanie = v.findViewById(R.id.korztext);
        Img.setImageResource(img);
        Title.setText(title);
        Opisanie.setText(opisanie);
        return v;
    }
}
