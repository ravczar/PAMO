package com.pjatk.s16281;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PPM extends AppCompatActivity {
    private Button backToMain;
    private Switch gender;
    private Boolean sex;
    private EditText weight;
    private EditText height;
    private EditText age;
    private Button calc;
    private TextView result;
    private double ppm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ppm);

        // BTN initialize
        backToMain = findViewById(R.id.ppm_back);
        gender = findViewById(R.id.gender_input);
        //Check brn state at init and save boolean
        sex = gender.isChecked();
        weight = findViewById(R.id.weight_input);
        height = findViewById(R.id.height_input);
        age = findViewById(R.id.age_input);
        calc = findViewById(R.id.calculate_btn);
        result = findViewById(R.id.ppm_result);

        backToMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
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

        double weight = Double.parseDouble(inputWeightTxt);
        double height = Double.parseDouble(inputHeightTxt);
        double age = Double.parseDouble(inputAgeTxt);

        if(sex){
            ppm = 655.1 + 9.563 * weight + 1.85 * height - 4.676 * age;
        }
        else{
            ppm = 66.5 + 13.75 * weight + 5.003 * height - 6.775 * age;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        double roundedResult = Double.valueOf(df.format(ppm));
        result.setText("PPM result: " + roundedResult + " [Calories]");

    }




}
