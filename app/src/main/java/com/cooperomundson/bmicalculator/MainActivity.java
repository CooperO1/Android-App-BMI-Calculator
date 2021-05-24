package com.cooperomundson.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButon;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews(){
        maleRadioButton = findViewById(R.id.radio_button_male);
        femaleRadioButon = findViewById(R.id.radio_button_female);

        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);

        calculateButton = findViewById(R.id.button_calculate);

        resultTextView = findViewById(R.id.text_view_result);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if(age >= 18){
                    displayResult(calculateBMI());
                } else {
                    displayGuidance(calculateBMI());
                }

            }
        });
    }

    private double calculateBMI() {
        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        //Converting the number 'Strings' into 'int' variables.
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        // Height in meters is the inches multiplied by 0.0254
        double heightInMeters = totalInches * 0.0254;

        // BMI formula = weight in kg divided by height in meters squared
        return weight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi){
        DecimalFormat bmiDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = bmiDecimalFormatter.format(bmi);

        String fullResultString;

        if(bmi < 18.5){
            //Display underweight
            fullResultString = bmiTextResult + " - You are underweight.";
        } else if(bmi > 25){
            //Display overweight
            fullResultString = bmiTextResult + " - You are overweight.";
        } else {
            // Display healthy
            fullResultString = bmiTextResult + " - You are a healthy weight. ";
        }
        resultTextView.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat bmiDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = bmiDecimalFormatter.format(bmi);

        String fullResultString;

        if(maleRadioButton.isChecked()){
            //Display boy guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for boys.";
        } else if(femaleRadioButon.isChecked()){
            //Display girl guidance.
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for girls.";

        } else {
            //Display general guidance.
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range.";

        }
    }

}