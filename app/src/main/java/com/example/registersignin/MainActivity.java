package com.example.registersignin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView signIn;
    private Button signUpBtn;
    private EditText userEmail, userPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        if(user!= null){
            startActivity(new Intent(MainActivity.this, Registration.class));
            finish();
        }
        
        userEmail = findViewById(R.id.emailSignUpId);
        userPass = findViewById(R.id.passSignUpId);
        signIn = findViewById(R.id.signInId);
        signUpBtn = findViewById(R.id.signUpBtnId);

        signIn.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signInId:
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                break;
            case R.id.signUpBtnId:
                userRegister();
                break;

        }
    }

    private void userRegister() {
        String email = userEmail.getText().toString().trim();
        String pass = userPass.getText().toString().trim();

        if (email.isEmpty()){
            userEmail.setError("Email is required!");
            userEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            userEmail.setError("Provide Valid Email!");
            userEmail.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            userPass.setError("Password is required!");
            userPass.requestFocus();
            return;
        }
        if(pass.length()!=6){
            userPass.setError("Password must be 6 numbers!");
            userPass.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(MainActivity.this, Login.class));
                            finish();
                        }
                    }
                });
    }
}
