package com.example.registersignin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class IncomeList extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private List<StoreData> storeDataList;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_list);

        String userId = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference(userId).child("Income").child("Information");
        storeDataList = new ArrayList<>();
        /*listAdapter = new ListAdapter(IncomeList.this, storeDataList);*/

        listView = findViewById(R.id.incomeListViewId);
    }
    int sum = 0;
    @Override
    protected void onStart(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                storeDataList.clear();
                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    StoreData storeData = dataSnapshot1.getValue(StoreData.class);
                    sum = sum + dataSnapshot1.child("amount").getValue(Integer.class);
                    storeDataList.add(storeData);
                }
                listAdapter = new ListAdapter(IncomeList.this,storeDataList);

                listView.setAdapter(listAdapter);
                System.out.println(sum);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        super.onStart();
    }

}