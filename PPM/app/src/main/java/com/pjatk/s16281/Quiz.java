package com.pjatk.s16281;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pjatk.s16281.model.QuestionDatabase;

public class Quiz extends AppCompatActivity {
    private Button backToMain;
    private ImageView questionImage;
    private int score;
    private QuestionDatabase questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        // BTN initialize
        backToMain = findViewById(R.id.quiz_back);
        questionImage = findViewById(R.id.quiz_image);

        // questions
        try{
            questions = new QuestionDatabase();
        }
        catch(Exception ex){
            Log.e("Error", ex.getMessage());
        }


        // set images
        Drawable newPicture = getResources().getDrawable( R.drawable.ic_launcher_foreground );
        questionImage.setImageDrawable(newPicture);

        //Back to main activity listener
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
