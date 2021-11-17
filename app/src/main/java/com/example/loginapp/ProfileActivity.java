package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button SaveButton;
    EditText TitleTexteditor, MultilineEditText;
    DatabaseReference databaseReference;
    Button noteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SaveButton = findViewById(R.id.button_SaveData);
        TitleTexteditor = findViewById(R.id.editTextText_TitleID);
        MultilineEditText = findViewById(R.id.editTextTextMultiLine);
        noteButton = findViewById(R.id.button_Notes);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Note");

        noteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                startActivity(intent);
            }
        });


        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_layout, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.SignOut_MenuID) {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    private void saveData() {


        String Title = TitleTexteditor.getText().toString().trim();
        String MultiText = MultilineEditText.getText().toString().trim();

        String key = databaseReference.push().getKey();

        DataText mydata = new DataText(Title, MultiText);
        if (Title.isEmpty()) {
            TitleTexteditor.setError("Title is empty");
            TitleTexteditor.requestFocus();
           // Toast.makeText(this, " Title is empty ", Toast.LENGTH_SHORT).show();
            return;

        }

        else if ( MultiText.isEmpty()) {
            MultilineEditText.setError("Note is empty");
            MultilineEditText.requestFocus();
            // Toast.makeText(this, " Title is empty ", Toast.LENGTH_SHORT).show();
            return;        }
        else {
            databaseReference.child(key).setValue(mydata);

            Toast.makeText(this, " Text added in storage !", Toast.LENGTH_SHORT).show();

            TitleTexteditor.setText("");
            MultilineEditText.setText("");

        }


    }
}