package com.pjatk.s16281;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Quiz extends AppCompatActivity {
    private Button backToMain;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        // BTN initialize
        backToMain = findViewById(R.id.quiz_back);

        //back to main activity listener
        backToMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                returnToMainActivity();
            }
        });

    }

    private int getScoreValue(){
        score = 75;
        return score;
    }

    private void returnToMainActivity(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("score_return", getScoreValue());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

}
