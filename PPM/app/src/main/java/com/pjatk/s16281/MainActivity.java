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
    private double ageValue;
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
                openActivityRecipe();
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
        intent.putExtra("age_passed", ageValue);
        startActivityForResult(intent, 2);
    }

    public void openActivityRecipe(){
        // Intent intent = new Intent(this, RecipeItem.class);
        // startActivity(intent);
        Intent intent = new Intent(getBaseContext(), Recipe.class);
        intent.putExtra("bmi_passed", bmiValue);
        intent.putExtra("ppm_passed", ppmValue);
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
                    if (bmiValue != 0 && bmiValue > 0) {
                        bmiView.setText(Double.toString(newBmiValue));
                    }
                }
                break;
            }
            case (2) : {
                if (resultCode == Activity.RESULT_OK) {
                    double newHeightValue = data.getDoubleExtra("height_return", 0.0);
                    double newWeightValue = data.getDoubleExtra("weight_return", 0.0);
                    double newPpmValue = data.getDoubleExtra("ppm_return", 0.0);
                    double newAgeValue = data.getDoubleExtra("age_return", 0.0);
                    String newSexValue = data.getStringExtra("sex_return");
                    heightValue = newHeightValue;
                    weightValue = newWeightValue;
                    sexValue = newSexValue;
                    ageValue = newAgeValue;
                    if( newPpmValue > 0) {
                        ppmValue = newPpmValue;
                        ppmView.setText(Double.toString(newPpmValue));
                    }
                    if (heightValue != 0 && heightValue > 0){
                        bmiValue =  roundMyDouble(weightValue / Math.pow(heightValue/100, 2) );
                        bmiView.setText(Double.toString(roundMyDouble(bmiValue)));
                    }
                    sexView.setText(newSexValue );

                }
                break;
            }
        }
    }

}
