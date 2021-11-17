package com.example.loginapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<DataText> {
    private Activity context;
    private List<DataText> dataList;

    public CustomAdapter(Activity context, List<DataText> dataList) {
        super(context, R.layout.sample_layout, dataList);
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout, null, true);
        DataText dataText = dataList.get(position);

        TextView t1 = view.findViewById(R.id.textView_Title);
        TextView t2 = view.findViewById(R.id.textView_note);
        t1.setText(dataText.getTitle());
        t2.setText(dataText.getMultiText());

        return view;
    }
}
