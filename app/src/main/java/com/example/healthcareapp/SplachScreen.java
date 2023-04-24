package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplachScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);
        Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(3000);
                    startActivity(new Intent(SplachScreen.this, LoginActivity.class));
                    finish();
                }catch (InterruptedException e){

                }
            }
        }; thread.start();
    }
}

