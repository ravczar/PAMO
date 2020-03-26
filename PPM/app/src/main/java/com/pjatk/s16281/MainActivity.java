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
    private double bmiValue, ppmValue, heightValue, weightValue, ageValue ;
    private double percentScore;
    private String sexValue;
    private Button bmiButton,ppmButton, recipeButton, quizButton, chartButton;
    private TextView bmiView, ppmView, sexView, quizView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize buttons
        bmiButton = findViewById(R.id.button_bmi);
        ppmButton = findViewById(R.id.button_ppm);
        recipeButton = findViewById(R.id.button_recipe);
        quizButton = findViewById(R.id.button_quiz);
        chartButton = findViewById(R.id.button_chart);
        bmiView = findViewById(R.id.bmi_view);
        ppmView = findViewById(R.id.ppm_view);
        sexView = findViewById(R.id.sex_view);
        quizView = findViewById(R.id.quiz_view);

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
        quizButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityQuiz();
            }
        });
        chartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityChart();
            }
        });
    }

    public void openActivityBMI(){
        //Intent intent = new Intent(this, BMI.class);
        //startActivity(intent);
        Intent intent = new Intent(getBaseContext(), BMI.class);
        intent.putExtra("height_passed", heightValue);
        intent.putExtra("weight_passed", weightValue);
        startActivityForResult(intent, 1);
    }

    public void openActivityPPM(){
        Intent intent = new Intent(getBaseContext(), PPM.class);
        intent.putExtra("height_passed", heightValue);
        intent.putExtra("weight_passed", weightValue);
        intent.putExtra("age_passed", ageValue);
        startActivityForResult(intent, 2);
    }

    public void openActivityRecipe(){
        Intent intent = new Intent(getBaseContext(), Recipe.class);
        intent.putExtra("bmi_passed", bmiValue);
        intent.putExtra("ppm_passed", ppmValue);
        startActivity(intent);
    }

    public void openActivityQuiz(){
        Intent intent = new Intent(getBaseContext(), Quiz.class);
        startActivityForResult(intent, 3);
    }

    public void openActivityChart(){
        Intent intent = new Intent(this, Chart.class);
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
                    bmiValue = data.getDoubleExtra("bmi_return", 0.0);
                    heightValue = data.getDoubleExtra("height_return", 0.0);
                    weightValue = data.getDoubleExtra("weight_return", 0.0);
                    if (bmiValue != 0 && bmiValue > 0) {
                        bmiView.setText(Double.toString(bmiValue));
                    }
                }
                break;
            }
            case (2) : {
                if (resultCode == Activity.RESULT_OK) {
                    heightValue = data.getDoubleExtra("height_return", 0.0);
                    weightValue = data.getDoubleExtra("weight_return", 0.0);
                    ageValue = data.getDoubleExtra("age_return", 0.0);
                    ppmValue = data.getDoubleExtra("ppm_return", 0.0);
                    sexValue = data.getStringExtra("sex_return");

                    if( ppmValue > 0) {
                        ppmView.setText(Double.toString(ppmValue));
                    }
                    if (heightValue != 0 && heightValue > 0){
                        bmiValue =  roundMyDouble(weightValue / Math.pow(heightValue/100, 2) );
                        bmiView.setText(Double.toString(roundMyDouble(bmiValue)));
                    }
                    sexView.setText(sexValue );

                }
                break;
            }
            case (3) : {
                if (resultCode == Activity.RESULT_OK) {
                    percentScore = data.getDoubleExtra("percent_return", 0.0);
                    if (percentScore != 0 && percentScore > 0) {
                        quizView.setText(Double.toString(percentScore) + " %");
                    }
                }
                break;
            }
        }
    }

}
