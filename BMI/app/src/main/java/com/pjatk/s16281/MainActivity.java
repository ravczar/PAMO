package com.pjatk.s16281;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "I tak jesteś gruby", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // Remake
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        result = findViewById(R.id.result);
        calc = findViewById(R.id.calculate);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You are fat anyway", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBmi();
            }
        });
        //

    }

    public void calculateBmi(){
        String inputWeightTxt = weight.getText().toString();
        String inputHeightTxt = height.getText().toString();

        double weight = Double.parseDouble(inputWeightTxt);
        double height = Double.parseDouble(inputHeightTxt);

        try{
            bmi = weight / Math.pow(height/100, 2);
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
            answer = bmi+": wygłodzenie";
        else if(bmi>=16 && bmi<17)
            answer = bmi+": wychudzenie";
        else if(bmi>=17 && bmi<18.5)
            answer = bmi+": niedowaga";
        else if(bmi>=18.5 && bmi<24.5)
            answer = bmi+": pożądana masa ciała";
        else if(bmi>=24.5 && bmi<30)
            answer = bmi+": nadwaga";
        else if(bmi>=30 && bmi<35)
            answer = bmi+": otyłość I stopnia";
        else if(bmi>=35 && bmi<40)
            answer = bmi+": otyłość II stopnia (duża)";
        else if(bmi>=40)
            answer = bmi+": otyłość III stopnia (chorobliwa)";
        else
            answer = "Error! You can't be so fat";

        result.setText("BMI: "+ answer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
