package com.example.healthcareapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HeaderNav extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    TextView emailHeader;
    TextView nameHeader;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_main);
        nameHeader = findViewById(R.id.header_name);
        emailHeader = findViewById(R.id.header_email);
        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
        Log.d("nom header", sharedpreferences.getString("name",""));
        nameHeader.setText(sharedpreferences.getString("name",""));
        emailHeader.setText(sharedpreferences.getString("email",""));


    }

}
