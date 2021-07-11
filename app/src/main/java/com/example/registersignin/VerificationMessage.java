package com.example.registersignin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationMessage extends AppCompatActivity implements View.OnClickListener {
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_message);

        login = findViewById(R.id.backLoginBtnId);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLoginBtnId:
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                break;
        }
    }
}