package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    CustomAdapter customAdapter;
    private List<DataText> dataList;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        databaseReference = FirebaseDatabase.getInstance().getReference("Note");
        dataList = new ArrayList<>();
        customAdapter = new CustomAdapter(DetailsActivity.this, dataList);


        listView = findViewById(R.id.listView_ID);

    }

    @Override
    protected void onStart() {

        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                dataList.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    //dataSnapshot1.getValue(DataText.class);
                    Log.d( "id", "onDataChange: "+dataSnapshot1.getValue(DataText.class).getTitle());
                    DataText dataText =  dataSnapshot1.getValue(DataText.class);
                    dataList.add(dataText);

                }
                listView.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}