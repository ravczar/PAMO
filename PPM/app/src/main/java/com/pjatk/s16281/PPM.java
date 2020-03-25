package com.pjatk.s16281;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PPM extends AppCompatActivity {
    private double weightPassed;
    private double heightPassed;
    private double agePassed;
    private Button backToMain;
    private Switch gender;
    private Boolean sex;
    private EditText weight;
    private EditText height;
    private EditText age;
    private Button calc;
    private TextView result;
    private double ppm;
    private Exception error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ppm);

        // BTN initialize
        backToMain = findViewById(R.id.ppm_back);
        gender = findViewById(R.id.gender_input);
        //Check button state at init and save boolean
        sex = gender.isChecked();
        weight = findViewById(R.id.weight_input);
        height = findViewById(R.id.height_input);
        age = findViewById(R.id.age_input);
        calc = findViewById(R.id.calculate_btn);
        result = findViewById(R.id.ppm_result);

        // retrieve passed weight and height
        weightPassed = getIntent().getDoubleExtra("weight_passed",0.0);
        heightPassed = getIntent().getDoubleExtra("height_passed",0.0);
        agePassed = getIntent().getDoubleExtra("age_passed",0.0);

        // set weight and height to inputs fields to be user-visible
        weight.setText(  weightPassed == 0 ? "" : Double.toString(weightPassed) );
        height.setText(  heightPassed == 0 ? "" : Double.toString(heightPassed) );
        age.setText(  agePassed == 0 ? "" : Double.toString(agePassed) );

        backToMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //finish();
                returnToMainActivity();
            }
        });
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatePpm();
            }
        });
        gender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sex = true;
                } else {
                    sex = false;
                }
            }
        });
    }

    public void calculatePpm(){
        String inputWeightTxt = weight.getText().toString();
        String inputHeightTxt = height.getText().toString();
        String inputAgeTxt = age.getText().toString();

        weightPassed = inputWeightTxt.isEmpty() ? 0 : Double.parseDouble(inputWeightTxt);
        heightPassed = inputHeightTxt.isEmpty() ? 0 : Double.parseDouble(inputHeightTxt);
        agePassed = inputAgeTxt.isEmpty() ? 0 : Double.parseDouble(inputAgeTxt);

        try{
            if(weightPassed != 0 && heightPassed !=0  && agePassed !=0){
                if(sex){
                    ppm = 655.1 + 9.563 * weightPassed + 1.85 * heightPassed - 4.676 * agePassed;
                }
                else{
                    ppm = 66.5 + 13.75 * weightPassed + 5.003 * heightPassed - 6.775 * agePassed;
                }
                result.setText("PPM result: " + roundMyDouble(ppm) + " [Calories]");
            }

        }
        catch (Exception ex){
            error = ex;
        }


    }

    private double roundMyDouble(double val){
        DecimalFormat df = new DecimalFormat("#.##");
        double roundedValue = Double.valueOf(df.format(val));
        return roundedValue;
    }

    private double getPpmValue(){
        return roundMyDouble(ppm);
    }

    private String getSexValue() {
        return sex ? "female" : "male";
    }

    private void returnToMainActivity(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("sex_return", getSexValue());
        resultIntent.putExtra("weight_return", weightPassed);
        resultIntent.putExtra("height_return", heightPassed);
        resultIntent.putExtra("age_return", agePassed);
        resultIntent.putExtra("ppm_return", getPpmValue());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

}
