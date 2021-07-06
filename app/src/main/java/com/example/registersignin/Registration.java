package com.example.registersignin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth auth;
    private TextView income, expense, incomeList, expenseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

        income = findViewById(R.id.incomeId);
        income.setOnClickListener(this);

        incomeList = findViewById(R.id.incomeListId);
        incomeList.setOnClickListener(this);

        expense = findViewById(R.id.expenseId);
        expense.setOnClickListener(this);

        expenseList = findViewById(R.id.expenseListId);
        expenseList.setOnClickListener(this);

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
        else if (id==R.id.settingId){
            Toast.makeText(getApplicationContext(), "You Click Setting",Toast.LENGTH_SHORT).show();
        }
        else if (id==R.id.exitId){
            FirebaseUser user = auth.getInstance().getCurrentUser();
            auth.signOut();
            finish();
            startActivity(new Intent(Registration.this, Login.class));
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
}