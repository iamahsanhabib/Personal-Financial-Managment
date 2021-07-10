package com.example.registersignin;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExpenseList extends AppCompatActivity {
    private ListView listView;
    private List<StoreData> dataInformation;
    private ExpenseListAdapter expenseListAdapter;

    DatabaseReference dRef;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        String userId = user.getUid();
        dRef = FirebaseDatabase.getInstance().getReference(userId).child("Expense").child("Information");

        dataInformation = new ArrayList<>();

        listView = findViewById(R.id.expenseListViewId);
    }
    @Override
    protected  void onStart(){

        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                dataInformation.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    StoreData data = dataSnapshot1.getValue(StoreData.class);
                    dataInformation.add(data);
                }
                expenseListAdapter = new ExpenseListAdapter(ExpenseList.this, dataInformation);
                listView.setAdapter(expenseListAdapter);
            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });
        super.onStart();
    }
}