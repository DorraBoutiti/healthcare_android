package com.example.healthcareapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor stepSensor;
    private TextView stepCountTextview;
    private int stepCount;
    boolean isCountSonorPresent;
    private Button clearCounterButton;
    private double MagnitudePrevious = 0;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedPreferences;

    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        sharedPreferences = getSharedPreferences("WorkoutPrefs", MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("workoutCount", 0);

        stepCountTextview= findViewById(R.id.step_count_textview);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        clearCounterButton = findViewById(R.id.clear_button);
        clearCounterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                stepCount = 0;
                stepCountTextview.setText(String.valueOf(stepCount));
            }
        });






    }


    @Override
    public void onResume() {
        super.onResume();
        if(stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }
        else {
            Toast.makeText(this, "Sensor not available!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        //unregister sensor
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent != null) {
            float x_acceleration = sensorEvent.values[0];
            float y_acceleration = sensorEvent.values[1];
            float z_acceleration = sensorEvent.values[2];
            double Magnitude = Math.sqrt(x_acceleration*x_acceleration + y_acceleration*y_acceleration
                    + z_acceleration*z_acceleration);
            double MagnitudeDelta = Magnitude - MagnitudePrevious;
            MagnitudePrevious = Magnitude;
            if (sensorEvent.values[0] > 6){
                stepCount++;
            }
            stepCountTextview.setText(String.valueOf(stepCount));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("workoutCount", stepCount);
            editor.apply();

            // Start the workout activity or perform any other desired action



        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}