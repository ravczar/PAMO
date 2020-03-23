package com.pjatk.s16281;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private double bmiValue;
    private double ppmValue;
    private double heightValue;
    private double weightValue;
    private String sexValue;

    private Button bmiButton;
    private Button ppmButton;
    private Button recipeButton;
    private TextView bmiView;
    private TextView ppmView;
    private TextView sexView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize buttons
        bmiButton = findViewById(R.id.button_bmi);
        ppmButton = findViewById(R.id.button_ppm);
        recipeButton = findViewById(R.id.button_recipe);
        bmiView = findViewById(R.id.bmi_view);
        ppmView = findViewById(R.id.ppm_view);
        sexView = findViewById(R.id.sex_view);

        bmiButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityBMI();
            }
        });
        ppmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityPPM();
            }
        });
        recipeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityRecipes();
            }
        });
    }

    public void openActivityBMI(){
        //Intent intent = new Intent(this, BMI.class);
        //startActivityForResult(intent, 1);
        Intent intent = new Intent(getBaseContext(), BMI.class);
        intent.putExtra("height_passed", heightValue);
        intent.putExtra("weight_passed", weightValue);
        startActivityForResult(intent, 1);
    }

    public void openActivityPPM(){
        // Intent intent = new Intent(this, PPM.class);
        // startActivityForResult(intent, 2);
        Intent intent = new Intent(getBaseContext(), PPM.class);
        intent.putExtra("height_passed", heightValue);
        intent.putExtra("weight_passed", weightValue);
        startActivityForResult(intent, 2);
    }

    public void openActivityRecipes(){
        Intent intent = new Intent(this, Recipe.class);
        startActivity(intent);
    }

    private double roundMyDouble(double val){
        DecimalFormat df = new DecimalFormat("#.##");
        double roundedValue = Double.valueOf(df.format(val));
        return roundedValue;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1) : {
                if (resultCode == Activity.RESULT_OK) {
                    double newBmiValue = data.getDoubleExtra("bmi_return", 0.0);
                    double newHeightValue = data.getDoubleExtra("height_return", 0.0);
                    double newWeightValue = data.getDoubleExtra("weight_return", 0.0);
                    bmiValue = newBmiValue;
                    heightValue = newHeightValue;
                    weightValue = newWeightValue;
                    bmiView.setText(Double.toString(newBmiValue));

                }
                break;
            }
            case (2) : {
                if (resultCode == Activity.RESULT_OK) {
                    double newHeightValue = data.getDoubleExtra("height_return", 0.0);
                    double newWeightValue = data.getDoubleExtra("weight_return", 0.0);
                    double newPpmValue = data.getDoubleExtra("ppm_return", 0.0);
                    String newSexValue = data.getStringExtra("sex_return");
                    heightValue = newHeightValue;
                    weightValue = newWeightValue;
                    sexValue = newSexValue;
                    ppmValue = newPpmValue;
                    ppmView.setText(Double.toString(newPpmValue));
                    bmiValue =  weightValue / Math.pow(heightValue/100, 2);
                    sexView.setText(newSexValue );
                    // reset bmi
                    bmiView.setText(Double.toString(roundMyDouble(bmiValue)));

                }
                break;
            }
        }
    }

}
