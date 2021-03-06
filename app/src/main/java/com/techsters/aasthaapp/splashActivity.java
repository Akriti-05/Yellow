package com.techsters.aasthaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(2500);
                }
                catch (Exception e)
                {
                    e.printStackTrace();

                }
                finally {
                    Intent intent = new Intent(splashActivity.this, SignInActivity.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();

    }
}