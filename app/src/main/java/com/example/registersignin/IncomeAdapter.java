package com.example.registersignin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IncomeAdapter extends BaseAdapter {
    Context context;
    int flags[];
    String[] itemNames;
    LayoutInflater inflter;

    public IncomeAdapter(Context applicationContext, int[] flags, String[] itemNames) {
        this.context = applicationContext;
        this.flags = flags;
        this.itemNames = itemNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return flags.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spiner_items2, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView1);
        TextView names = (TextView) view.findViewById(R.id.textView1);
        icon.setImageResource(flags[i]);
        names.setText(itemNames[i]);
        return view;
    }
}
