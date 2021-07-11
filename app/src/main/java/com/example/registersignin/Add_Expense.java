package com.example.registersignin;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Add_Expense extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private TextView expenseCancel;
    private EditText amount;
    private Button addExpenseBtn;

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference databaseReference;

    String[] itemNames={"Home","Breakfast","Communication","Train","Movie","Gift","Shirt","Books","Car","Sports","Food","Aeroplane","Shopping","Stationary"};
    int flags[] = {R.drawable.ic_home, R.drawable.breakfast, R.drawable.ic_call, R.drawable.ic_train, R.drawable.ic_filter, R.drawable.ic_gift, R.drawable.tshirt, R.drawable.ic_book,R.drawable.ic_car,R.drawable.ic_sport, R.drawable.ic_food,R.drawable.ic_plane, R.drawable.ic_phone, R.drawable.ic_taxi };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        expenseCancel = findViewById(R.id.expenseCancelId);
        expenseCancel.setOnClickListener(this);

        amount = findViewById(R.id.addExpenseAmountId);
        addExpenseBtn = findViewById(R.id.addExpenseBtnId);

        addExpenseBtn.setOnClickListener(this);

        Spinner spin = (Spinner) findViewById(R.id.simpleSpinner);
        spin.setOnItemSelectedListener(this);

        ExpenseAdapter expenseAdapter=new ExpenseAdapter(getApplicationContext(),flags,itemNames);
        spin.setAdapter(expenseAdapter);

        String userId = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference(userId);
    }

    int day1,month1,year1;

    String itemName;
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getApplicationContext(), itemNames[position], Toast.LENGTH_LONG).show();
        itemName = itemNames[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.expenseCancelId:
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
                break;
            case R.id.addExpenseBtnId:
                String expense = amount.getText().toString().trim();
                if(expense.isEmpty()){
                    amount.setError("Amount can't be empty!");
                    amount.requestFocus();
                }
                else{
                    saveData();
                    intent = new Intent(getApplicationContext(), Registration.class);
                    startActivity(intent);
                    break;
                }
        }
    }

    public String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        day1 = day; month1 = month; year1 = year;
        return makeDateString(day, month, year);
    }
    private void saveData() {
        String expense = amount.getText().toString().trim();
        if(user!=null) {
            int balance = Integer.parseInt(expense);
            String category = itemName;
            String date = makeDateString(day1, month1, year1);
            databaseReference.child("Expense").child("Category").child(category).setValue(balance);
            String key = databaseReference.push().getKey();
            StoreData storeData = new StoreData(category, balance, date);
            databaseReference.child("Expense").child("Information").child(key).setValue(storeData);
        }
    }
    public void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                day1 = day;
                month1 = month;
                year1 = year;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    public String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    public String getMonthFormat(int month)
    {
        if(month == 1)
            return "Jan";
        if(month == 2)
            return "Feb";
        if(month == 3)
            return "Mar";
        if(month == 4)
            return "Apr";
        if(month == 5)
            return "May";
        if(month == 6)
            return "Jun";
        if(month == 7)
            return "Jul";
        if(month == 8)
            return "Aug";
        if(month == 9)
            return "Sep";
        if(month == 10)
            return "Oct";
        if(month == 11)
            return "Nov";
        if(month == 12)
            return "Dec";

        return "Jan";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

}