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

public class Login extends AppCompatActivity implements View.OnClickListener {
    private TextView signUp, forgotPass;
    private Button signInBtn;
    private EditText emailSignIn, passSignIn;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getInstance().getCurrentUser();
        if(user!= null){
            startActivity(new Intent(Login.this, Registration.class));
            finish();
        }

        signUp = findViewById(R.id.signUpId);
        signInBtn = findViewById((R.id.signInBtnId));
        emailSignIn = findViewById(R.id.emailSignInId);
        passSignIn = findViewById((R.id.passSignInId));

        signUp.setOnClickListener(this);
        signInBtn.setOnClickListener(this);

        forgotPass = findViewById(R.id.forgotPassId);
        forgotPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signUpId:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.signInBtnId:
                userLogin();
                break;
            case R.id.forgotPassId:
                intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(intent);
                break;

        }
    }

    private void userLogin() {
        String email = emailSignIn.getText().toString().trim();
        String pass = passSignIn.getText().toString().trim();

        if (email.isEmpty()){
            emailSignIn.setError("Email is required!");
            emailSignIn.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailSignIn.setError("Provide Valid Email!");
            emailSignIn.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            emailSignIn.setError("Password is required!");
            emailSignIn.requestFocus();
            return;
        }
        if(pass.length()<6 || pass.length()>6){
            passSignIn.setError("Password must be 6 numbers!");
            passSignIn.requestFocus();
            return;
        }
        auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>()  {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login Failed"+ task.getException(), Toast.LENGTH_LONG).show();

                        } else {
                            Intent intent = new Intent(Login.this, Registration.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

    }
}