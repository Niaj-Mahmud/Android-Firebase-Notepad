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

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private EditText SignUpeditTextEmailAddress , SignUpeditTextPassword;
    private TextView SignUpView;
    private Button SignUpButton;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign Up ");



        SignUpeditTextEmailAddress = findViewById(R.id.editTextTextEmailAddressSign_Up);
        SignUpeditTextPassword =  findViewById(R.id.editTextTextPasswordSign_Up);

        SignUpView =  findViewById(R.id.textView);
        SignUpButton = findViewById(R.id.buttonSign_Up);
        progressBar =findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        SignUpView.setOnClickListener(this);
        SignUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.buttonSign_Up:
                UserRegister();

                break;

            case R.id.textView:
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                break;
    }
}

    private void UserRegister() {

        String email = SignUpeditTextEmailAddress.getText().toString().trim();
        String pass = SignUpeditTextPassword.getText().toString();

        if(email.isEmpty())
        {
            SignUpeditTextEmailAddress.setError("Enter an email address");
            SignUpeditTextEmailAddress.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            SignUpeditTextEmailAddress.setError("Enter a valid email address");
            SignUpeditTextEmailAddress.requestFocus();
            return;
        }

        //checking the validity of the password
        if(pass.isEmpty())
        {
            SignUpeditTextPassword.setError("Enter a password");
            SignUpeditTextPassword.requestFocus();
            return;
        }

        if (pass.length()<8){

            SignUpeditTextPassword.setError("Password Length Must be 8 Digits");
            SignUpeditTextPassword.requestFocus();
            return;



        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {

                    
                    Toast.makeText(getApplicationContext(), "Register is successfull", Toast.LENGTH_SHORT).show();
                    // Sign in success, update UI with the signed-in user's information
                    /*Log.d(TAG, "signInWithCredential:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);*/
                } else {

                    Toast.makeText(getApplicationContext(), "Mail Already Registered ", Toast.LENGTH_SHORT).show();

                    // If sign in fails, display a message to the user.
                    /*Log.w(TAG, "signInWithCredential:failure", task.getException());
                    Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                    updateUI(null);*/
                }
            }
        });

    }
}