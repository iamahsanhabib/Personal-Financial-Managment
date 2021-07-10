package com.example.registersignin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth auth;
    private TextView income, expense, incomeList, expenseList, balance, incomeShow, expenseShow;
    private TextView plane1, home1, stationary1,food1,sport1,communication1,breakfast1;
    private TextView train1, shopping1, shirt1, car1,book1,movie1, gift1;
    DatabaseReference dRef;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        String userId = user.getUid();
        dRef = FirebaseDatabase.getInstance().getReference(userId);

        income = findViewById(R.id.incomeId);
        income.setOnClickListener(this);

        incomeList = findViewById(R.id.incomeListId);
        incomeList.setOnClickListener(this);

        expense = findViewById(R.id.expenseId);
        expense.setOnClickListener(this);

        expenseList = findViewById(R.id.expenseListId);
        expenseList.setOnClickListener(this);

        balance = findViewById(R.id.mainBalanceId);
        incomeShow = findViewById(R.id.incomeTextViewId);
        expenseShow = findViewById(R.id.expenseTextViewId);

        plane1 = findViewById(R.id.planeId1);
        home1 = findViewById(R.id.homeId1);
        stationary1 = findViewById(R.id.stationaryId1);
        food1 = findViewById(R.id.foodId1);
        sport1 = findViewById(R.id.sportId1);
        communication1 = findViewById(R.id.communicatinId1);
        breakfast1 = findViewById(R.id.breakfastId1);
        train1 = findViewById(R.id.trainId1);
        shopping1 = findViewById(R.id.shoppingId1);
        shirt1 = findViewById(R.id.shirtId1);
        car1 = findViewById(R.id.carId1);
        book1 = findViewById(R.id.bookId1);
        movie1 = findViewById(R.id.movieId1);
        gift1 = findViewById(R.id.giftId1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.shareId){
            Toast.makeText(getApplicationContext(), "You Click share",Toast.LENGTH_SHORT).show();
        }
        else if (id==R.id.aboutId){
            Toast.makeText(getApplicationContext(), "You Click About",Toast.LENGTH_SHORT).show();
        }

        else if (id==R.id.exitId){
            FirebaseUser user = auth.getInstance().getCurrentUser();
            auth.signOut();
            finish();
            startActivity(new Intent(Registration.this, Login.class));
        }
        else if (id==R.id.dayId){
            Intent intent = new Intent(getApplicationContext(), Day.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "You Click Day",Toast.LENGTH_SHORT).show();
        }
        else if (id==R.id.weekId){
            Intent intent = new Intent(getApplicationContext(), Week.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "You Click Week",Toast.LENGTH_SHORT).show();
        }
        else if (id==R.id.monthId){
            Intent intent = new Intent(getApplicationContext(), Registration.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "You Click Month",Toast.LENGTH_SHORT).show();
        }
        else if (id==R.id.yearId){
            Intent intent = new Intent(getApplicationContext(), Year.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "You Click Year",Toast.LENGTH_SHORT).show();
        }
        else if (id==R.id.allId){
            Intent intent = new Intent(getApplicationContext(), All.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "You Click All",Toast.LENGTH_SHORT).show();
        }

        return true;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.incomeId:
                Intent intent = new Intent(getApplicationContext(), Income.class);
                startActivity(intent);
                break;
            case R.id.expenseId:
                intent = new Intent(getApplicationContext(), Add_Expense.class);
                startActivity(intent);
                break;
            case R.id.incomeListId:
                intent = new Intent(getApplicationContext(), IncomeList.class);
                startActivity(intent);
                break;

            case R.id.expenseListId:
                intent = new Intent(getApplicationContext(), ExpenseList.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onStart(){
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                int expense = 0, income=0;
                String[] x = getTodaysDate().split(" ");
                int[] categoryAmount={0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                for (DataSnapshot dataSnapshot1: dataSnapshot.child("Expense").child("Information").getChildren()){
                    String[] k = dataSnapshot1.child("date").getValue(String.class).split(" ");
                    if(x[0].equals(k[0]) && x[2].equals(k[2])) {
                        expense = expense + dataSnapshot1.child("amount").getValue(Integer.class);
                        if(dataSnapshot1.child("category").getValue(String.class).equals("Aeroplane")){
                            categoryAmount[0]+=dataSnapshot1.child("amount").getValue(Integer.class);
                        }
                        else if(dataSnapshot1.child("category").getValue(String.class).equals("Home")){
                            categoryAmount[1]+=dataSnapshot1.child("amount").getValue(Integer.class);
                        }
                        else if(dataSnapshot1.child("category").getValue(String.class).equals("Stationary")){
                            categoryAmount[2]+=dataSnapshot1.child("amount").getValue(Integer.class);
                        }
                        else if(dataSnapshot1.child("category").getValue(String.class).equals("Food")){
                            categoryAmount[3]+=dataSnapshot1.child("amount").getValue(Integer.class);
                        }
                        else if(dataSnapshot1.child("category").getValue(String.class).equals("Sports")){
                            categoryAmount[4]+=dataSnapshot1.child("amount").getValue(Integer.class);
                        }
                        else if(dataSnapshot1.child("category").getValue(String.class).equals("Communication")){
                            categoryAmount[5]+=dataSnapshot1.child("amount").getValue(Integer.class);
                        }
                        else if(dataSnapshot1.child("category").getValue(String.class).equals("Breakfast")){
                            categoryAmount[6]+=dataSnapshot1.child("amount").getValue(Integer.class);
                        }
                        else if(dataSnapshot1.child("category").getValue(String.class).equals("Train")){
                            categoryAmount[7]+=dataSnapshot1.child("amount").getValue(Integer.class);
                        }
                        else if(dataSnapshot1.child("category").getValue(String.class).equals("Shopping")){
                            categoryAmount[8]+=dataSnapshot1.child("amount").getValue(Integer.class);
                        }
                        else if(dataSnapshot1.child("category").getValue(String.class).equals("Shirt")){
                            categoryAmount[9]+=dataSnapshot1.child("amount").getValue(Integer.class);
                        }
                        else if(dataSnapshot1.child("category").getValue(String.class).equals("Car")){
                            categoryAmount[10]+=dataSnapshot1.child("amount").getValue(Integer.class);
                        }
                        else if(dataSnapshot1.child("category").getValue(String.class).equals("Books")){
                            categoryAmount[11]+=dataSnapshot1.child("amount").getValue(Integer.class);
                        }
                        else if(dataSnapshot1.child("category").getValue(String.class).equals("Movie")){
                            categoryAmount[12]+=dataSnapshot1.child("amount").getValue(Integer.class);
                        }
                        else if(dataSnapshot1.child("category").getValue(String.class).equals("Gift")){
                            categoryAmount[13]+=dataSnapshot1.child("amount").getValue(Integer.class);
                        }
                    }
                }
                for (DataSnapshot dataSnapshot1: dataSnapshot.child("Income").child("Information").getChildren()){
                    String[] k = dataSnapshot1.child("date").getValue(String.class).split(" ");
                    if(x[0].equals(k[0]) && x[2].equals(k[2])) {
                        income = income + dataSnapshot1.child("amount").getValue(Integer.class);
                    }

                }
                balance.setText(String.valueOf(income-expense));
                incomeShow.setText(String.valueOf(income));
                expenseShow.setText(String.valueOf(expense));
                if(expense==0)expense=1;
                plane1.setText(String.valueOf(categoryAmount[0]*100/expense)+"%");
                home1.setText(String.valueOf(categoryAmount[1]*100/expense)+"%");
                stationary1.setText(String.valueOf(categoryAmount[2]*100/expense)+"%");
                food1.setText(String.valueOf(categoryAmount[3]*100/expense)+"%");
                sport1.setText(String.valueOf(categoryAmount[4]*100/expense)+"%");
                communication1.setText(String.valueOf(categoryAmount[5]*100/expense)+"%");
                breakfast1.setText(String.valueOf(categoryAmount[6]*100/expense)+"%");
                train1.setText(String.valueOf(categoryAmount[7]*100/expense)+"%");
                shopping1.setText(String.valueOf(categoryAmount[8]*100/expense)+"%");
                shirt1.setText(String.valueOf(categoryAmount[9]*100/expense)+"%");
                car1.setText(String.valueOf(categoryAmount[10]*100/expense)+"%");
                book1.setText(String.valueOf(categoryAmount[11]*100/expense)+"%");
                movie1.setText(String.valueOf(categoryAmount[12]*100/expense)+"%");
                gift1.setText(String.valueOf(categoryAmount[13]*100/expense)+"%");
            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });
        super.onStart();
    }
    public String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
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
}