package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class CalculIMCIMGActivity extends AppCompatActivity {
    private EditText editTextWeight;
    private EditText editTextHeight;
    private Button calculateButton;
    private TextView resultTextView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul_imcimgactivity);

        editTextWeight = findViewById(R.id.edit_text_weight);
        editTextHeight = findViewById(R.id.edit_text_height);
        calculateButton = findViewById(R.id.calculate_button);
        resultTextView = findViewById(R.id.result_text_view);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = editTextWeight.getText().toString();
        String heightStr = editTextHeight.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr) / 100; // Convert height to meters

            float bmi = weight / (height * height);

            String result = String.format("Your BMI: %.2f", bmi);

            if (bmi < 18.5) {
                result += "\nUnderweight";
            } else if (bmi >= 18.5 && bmi < 25) {
                result += "\nNormal weight";
            } else if (bmi >= 25 && bmi < 30) {
                result += "\nOverweight";
            } else {
                result += "\nObese";
            }

            resultTextView.setText(result);
        } else {
            resultTextView.setText("");
        }
    }
}