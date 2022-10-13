package com.example.monitoring.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.monitoring.R;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new Handler() .postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(SplashscreenActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        }, 2000);
    }
}