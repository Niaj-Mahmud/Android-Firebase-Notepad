package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText LogineditTextEmailAddress, LogineditTextPassword;
    private TextView SignUpView;
    private Button LoginButton;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Log In ");

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progress_Bar);


        LogineditTextEmailAddress = findViewById(R.id.editTextTextEmailAddressLogin);
        LogineditTextPassword = findViewById(R.id.editTextTextPasswordLogin);

        SignUpView = findViewById(R.id.ViewRegister);
        LoginButton = findViewById(R.id.buttonLogin);

        SignUpView.setOnClickListener(this);
        LoginButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.buttonLogin:
                userLogin();

                break;

            case R.id.ViewRegister:
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                break;

        }

    }

    private void userLogin() {

        String email = LogineditTextEmailAddress.getText().toString().trim();
        String pass = LogineditTextPassword.getText().toString();

        if (email.isEmpty()) {
            LogineditTextEmailAddress.setError("Enter an email address");
            LogineditTextEmailAddress.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            LogineditTextEmailAddress.setError("Enter a valid email address");
            LogineditTextEmailAddress.requestFocus();
            return;
        }

        //checking the validity of the password
        if (pass.isEmpty()) {
            LogineditTextPassword.setError("Enter a password");
            LogineditTextPassword.requestFocus();
            return;
        }

        if (pass.length() < 8) {

            LogineditTextPassword.setError("Password Length Must be 8 Digits");
            LogineditTextPassword.requestFocus();
            return;


        }

        progressBar.setVisibility(View.VISIBLE);

        Task<AuthResult> authResultTask = mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);

                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
