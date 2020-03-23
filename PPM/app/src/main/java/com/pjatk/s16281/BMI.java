package com.pjatk.s16281;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BMI extends AppCompatActivity {
    private double weightPassed;
    private double heightPassed;
    private Button backToMain;
    private EditText weight;
    private double weight_entered;
    private double height_entered;
    private EditText height;
    private TextView result;
    private Button calc;
    private double bmi;
    private String answer;
    private Exception error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);

        // BTN initialize
        backToMain = findViewById(R.id.bmi_back);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        result = findViewById(R.id.result);
        calc = findViewById(R.id.calculate_btn);

        // retrieve passed weight and height
        weightPassed = getIntent().getDoubleExtra("weight_passed",0.0);
        heightPassed = getIntent().getDoubleExtra("height_passed",0.0);
        // set weight and height to inputs fields to be user-visible
        weight.setText(  weightPassed == 0 ? "" : Double.toString(weightPassed) );
        height.setText(  heightPassed == 0 ? "" : Double.toString(heightPassed) );

        backToMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                returnToMainActivity();
            }
        });
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBmi();
            }
        });
    }

    public void calculateBmi(){
        String inputWeightTxt = weight.getText().toString();
        String inputHeightTxt = height.getText().toString();

        weight_entered = inputWeightTxt.isEmpty() ? 0 : Double.parseDouble(inputWeightTxt);
        height_entered = inputHeightTxt.isEmpty() ? 0 : Double.parseDouble(inputHeightTxt);

        try{

            bmi = height_entered > 0 ? weight_entered / Math.pow(height_entered/100, 2) : 0;
            bmi = roundMyDouble(bmi);
            displayBmi();

        }
        catch (ArithmeticException ex){
            error = ex;
        }

        if (height_entered == 0){
            result.setText("Height cannot be 0");
        }

    }

    private void displayBmi(){
        if ( bmi < 16 )
            answer = bmi+"\n starvation";
        else if(bmi>=16 && bmi<17)
            answer = bmi+"\n extra skinny";
        else if(bmi>=17 && bmi<18.5)
            answer = bmi+"\n to slim-shady";
        else if(bmi>=18.5 && bmi<24.5)
            answer = bmi+"\n perfect weight";
        else if(bmi>=24.5 && bmi<30)
            answer = bmi+"\n overweighted";
        else if(bmi>=30 && bmi<35)
            answer = bmi+"\n obesity I level";
        else if(bmi>=35 && bmi<40)
            answer = bmi+"\n obesity II level (be aware)";
        else if(bmi>=40)
            answer = bmi+"\n obesity III level (you are sick)";
        else
            answer = "Error! You can't be so fat";

        result.setText("BMI: \n"+ answer);
    }

    private double roundMyDouble(double val){
        DecimalFormat df = new DecimalFormat("#.##");
        double roundedValue = Double.valueOf(df.format(val));
        return roundedValue;
    }

    private double getBmiValue(){
        return bmi;
    }

    private double getHeightValue() {
        return height_entered == 0.0 ? 0.0 : roundMyDouble(height_entered);
    }

    private double getWeightValue() {
        return weight_entered == 0.0  ? 0.0 : roundMyDouble(weight_entered);
    }

    private void returnToMainActivity(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("bmi_return", getBmiValue());
        resultIntent.putExtra("height_return", getHeightValue());
        resultIntent.putExtra("weight_return", getWeightValue());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
