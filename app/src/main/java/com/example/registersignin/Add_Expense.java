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

public class Add_Expense extends AppCompatActivity implements View.OnClickListener{
    private TextView expenseCancel;
    private EditText amount;
    private Button addExpenseBtn;
    private TextView addPlane, addHome, addStationary, addFood, addSport, addCall, addBreakfast;
    private  TextView addTrain, addShirt, addShooping, addCar, addMovie, addGift, addBook;

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference databaseReference;

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

        addPlane = findViewById(R.id.addPlaneId);
        addPlane.setOnClickListener(this);
        addHome = findViewById(R.id.addHomeId);
        addHome.setOnClickListener(this);
        addStationary = findViewById(R.id.addStationaryId);
        addStationary.setOnClickListener(this);
        addFood = findViewById(R.id.addFoodId);
        addFood.setOnClickListener(this);
        addSport = findViewById(R.id.addSportId);
        addSport.setOnClickListener(this);
        addCall = findViewById(R.id.addCommunicationId);
        addCall.setOnClickListener(this);
        addBreakfast = findViewById(R.id.addBreakfastId);
        addBreakfast.setOnClickListener(this);
        addTrain = findViewById(R.id.addTrainId);
        addTrain.setOnClickListener(this);
        addShooping = findViewById(R.id.addShoppingId);
        addShooping.setOnClickListener(this);
        addShirt = findViewById(R.id.addShirtId);
        addShirt.setOnClickListener(this);
        addCar = findViewById(R.id.addCarId);
        addCar.setOnClickListener(this);
        addBook = findViewById(R.id.addBookId);
        addBook.setOnClickListener(this);
        addMovie = findViewById(R.id.addMovieId);
        addMovie.setOnClickListener(this);
        addGift = findViewById(R.id.addGiftId);
        addGift.setOnClickListener(this);

        String userId = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference(userId);
    }

    int day1,month1,year1;

    @Override
    public void onClick(View view) {
        String expense = amount.getText().toString().trim();
        switch (view.getId()){
            case R.id.expenseCancelId:
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
                break;
            case R.id.addPlaneId:
                if(expense.isEmpty()) {
                    displayError();
                }
                else{
                    saveData("Aeroplane");
                }
                break;
            case R.id.addHomeId:
                if(expense.isEmpty()) {
                    displayError();
                }
                else{
                    saveData("Home");
                }
                break;
            case R.id.addStationaryId:
                if(expense.isEmpty()) {
                    displayError();
                }
                else{
                    saveData("Stationary");
                }
                break;
            case R.id.addFoodId:
                if(expense.isEmpty()) {
                    displayError();
                }
                else{
                    saveData("Food");
                }
                break;
            case R.id.addSportId:
                if(expense.isEmpty()) {
                    displayError();
                }
                else{
                    saveData("Sport");
                }
                break;
            case R.id.addCommunicationId:
                if(expense.isEmpty()) {
                    displayError();
                }
                else{
                    saveData("Communication");
                }
                break;
            case R.id.addBreakfastId:
                if(expense.isEmpty()) {
                    displayError();
                }
                else{
                    saveData("Breakfast");
                }
                break;
            case R.id.addTrainId:
                if(expense.isEmpty()) {
                    displayError();
                }
                else{
                    saveData("Train");
                }
                break;
            case R.id.addShoppingId:
                if(expense.isEmpty()) {
                    displayError();
                }
                else{
                    saveData("Shopping");
                }
                break;
            case R.id.addShirtId:
                if(expense.isEmpty()) {
                    displayError();
                }
                else{
                    saveData("T-Shirt");
                }
                break;
            case R.id.addCarId:
                if(expense.isEmpty()) {
                    amount.setError("Amount can't be empty!");
                    amount.requestFocus();
                }
                else{
                    saveData("Car");
                }
                break;
            case R.id.addBookId:
                if(expense.isEmpty()) {
                    displayError();
                }
                else{
                    saveData("Books");
                }
                break;
            case R.id.addMovieId:
                if(expense.isEmpty()) {
                    displayError();
                }
                else{
                    saveData("Movies");
                }
                break;
            case R.id.addGiftId:
                if(expense.isEmpty()) {
                    displayError();
                }
                else{
                    saveData("Gift");
                }
                break;
        }
    }

    private void displayError() {
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
        String expense = amount.getText().toString().trim();
        if(user!=null) {
            int balance = Integer.parseInt(expense);
            String date = makeDateString(day1, month1, year1);
            String key = databaseReference.push().getKey();
            StoreData storeData = new StoreData(category, balance, date);
            databaseReference.child("Expense").child("Information").child(key).setValue(storeData);
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

        return "Jan";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

}