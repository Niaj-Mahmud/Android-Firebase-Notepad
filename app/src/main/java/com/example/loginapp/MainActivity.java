package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_id);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doprogress();
                nectactivity();
            }
        });
        thread.start();
    }

    private void nectactivity() {
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
    }

    private void doprogress() {
        for (int value = 0; value < 100; value += 20) {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(value + 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}