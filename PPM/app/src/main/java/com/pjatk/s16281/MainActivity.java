package com.pjatk.s16281;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button bmiButton;
    private Button ppmButton;
    private Button recipeButton;
    private TextView bmiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize buttons
        bmiButton = findViewById(R.id.button_bmi);
        ppmButton = findViewById(R.id.button_ppm);
        recipeButton = findViewById(R.id.button_recipe);
        bmiView = findViewById(R.id.bmi_view);

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
        Intent intent = new Intent(this, BMI.class);
        startActivity(intent);
    }

    public void openActivityPPM(){
        Intent intent = new Intent(this, PPM.class);
        startActivity(intent);
    }

    public void openActivityRecipes(){
        Intent intent = new Intent(this, Recipe.class);
        startActivity(intent);
    }

}
