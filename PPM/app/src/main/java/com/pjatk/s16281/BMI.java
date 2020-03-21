package com.pjatk.s16281;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BMI extends AppCompatActivity {
    private Button backToMain;
    private EditText weight;
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

        backToMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
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

        double weight = Double.parseDouble(inputWeightTxt);
        double height = Double.parseDouble(inputHeightTxt);

        try{
            bmi = weight / Math.pow(height/100, 2);
            bmi = roundMyDouble(bmi);
            displayBmi();
        }
        catch (ArithmeticException ex){
            error = ex;
        }

        if (height == 0){
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

    public double getBmiValue(){
        return bmi;
    }
}
