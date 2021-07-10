package com.example.registersignin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ExpenseListAdapter extends ArrayAdapter<StoreData> {
    private Activity context;
    private List<StoreData> expenseInformationList;

    public ExpenseListAdapter(Activity context, List<StoreData> expenseInformationList) {
        super(context, R.layout.sample_expense_layout, expenseInformationList);
        this.context = context;
        this.expenseInformationList = expenseInformationList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_expense_layout, null, true);
        StoreData data = expenseInformationList.get(position);
        TextView t1 = view.findViewById(R.id.expenseDateId);
        TextView t2 = view.findViewById(R.id.expenseCategoryId);
        TextView t3 = view.findViewById(R.id.expenseAmountId);
        t1.setText(data.getDate());
        t2.setText(data.getCategory());
        t3.setText(String.valueOf(data.getAmount()));


        return view;
    }

}
