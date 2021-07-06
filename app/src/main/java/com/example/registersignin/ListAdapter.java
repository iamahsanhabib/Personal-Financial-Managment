package com.example.registersignin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter<StoreData> {
    private Activity context;
    private List<StoreData> storeDataList;

    public ListAdapter(Activity context, List<StoreData> storeDataList) {
        super(context, R.layout.list_sample_view, storeDataList);
        this.context = context;
        this.storeDataList = storeDataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_sample_view, null, true);
        StoreData storeData = storeDataList.get(position);
        TextView t1 = view.findViewById(R.id.listDateId);
        TextView t2 = view.findViewById(R.id.listCategoryId);
        TextView t3 = view.findViewById(R.id.listAmountId);

        t1.setText(storeData.getDate());
        t2.setText(storeData.getCategory());
        t3.setText(String.valueOf(storeData.getAmount()));
        return view;
    }
}
