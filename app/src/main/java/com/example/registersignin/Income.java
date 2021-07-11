package com.example.registersignin;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Income extends AppCompatActivity implements View.OnClickListener {
    private TextView cancel, addSalary, addFamily, addTution, addBusiness, addOthers;
    private EditText amount;
    private Button addIncomeBtn;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private FirebaseAuth auth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        cancel = findViewById(R.id.incomeCancelId);
        cancel.setOnClickListener(this);

        amount = findViewById(R.id.addIncomeAmountId);

        addSalary = findViewById(R.id.addSalaryId);
        addSalary.setOnClickListener(this);
        addFamily = findViewById(R.id.addFamilyId);
        addFamily.setOnClickListener(this);
        addTution = findViewById(R.id.addTutionId);
        addTution.setOnClickListener(this);
        addBusiness = findViewById(R.id.addBusinessId);
        addBusiness.setOnClickListener(this);
        addOthers = findViewById(R.id.addOthersId);
        addOthers.setOnClickListener(this);

        String userId = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference(userId);
    }
    int day1,month1,year1;
    @Override
    public void onClick(View view) {
        String income = amount.getText().toString().trim();
        switch (view.getId()){
            case R.id.addSalaryId:
                if(income.isEmpty()){
                    errorDisplay();
                }
                else saveData("Salary");
                break;
            case R.id.addFamilyId:
                if(income.isEmpty()){
                    errorDisplay();
                }
                else saveData("Family");
                break;
            case R.id.addTutionId:
                if(income.isEmpty()){
                    errorDisplay();
                }
                else saveData("Tution");
                break;
            case R.id.addBusinessId:
                if(income.isEmpty()){
                    errorDisplay();
                }
                else saveData("Business");
                break;
            case R.id.addOthersId:
                if(income.isEmpty()){
                    errorDisplay();
                }
                else saveData("Others");
                break;
            case R.id.incomeCancelId:
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
                break;

        }
    }

    private void errorDisplay() {
        amount.setError("Amount can't be empty!");
        amount.requestFocus();
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
    private void saveData(String category) {
        String income = amount.getText().toString().trim();
        if(user!=null) {
            int balance = Integer.parseInt(income);
            String date = makeDateString(day1, month1, year1);
            String key = databaseReference.push().getKey();
            StoreData storeData = new StoreData(category, balance, date);
            databaseReference.child("Income").child("Information").child(key).setValue(storeData);
            Toast.makeText(getApplicationContext(),"Data Saved Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Registration.class);
            startActivity(intent);
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

        return getTodaysDate();
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

}