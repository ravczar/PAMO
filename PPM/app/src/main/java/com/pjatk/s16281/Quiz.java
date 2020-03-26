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
import android.widget.TextView;
import android.widget.Toast;

import com.pjatk.s16281.model.QuestionDatabase;
import com.pjatk.s16281.model.QuestionItem;

public class Quiz extends AppCompatActivity {
    private Button backToMainButton, nextQuestionButton, answer1Btn, answer2Btn, answer3Btn, answer4Btn;
    private TextView questionView, quizTitleView;
    private ImageView questionImage;
    private int score, questionId, step;
    private QuestionDatabase questions;
    private QuestionItem selectedQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        // BTN, View initialize
        backToMainButton = findViewById(R.id.quiz_back);
        answer1Btn = findViewById(R.id.btn_answer_1);
        answer2Btn = findViewById(R.id.btn_answer_2);
        answer3Btn = findViewById(R.id.btn_answer_3);
        answer4Btn = findViewById(R.id.btn_answer_4);
        questionView = findViewById(R.id.qestion_view);
        quizTitleView = findViewById(R.id.quiz_title);
        nextQuestionButton = findViewById(R.id.button_next);
        questionImage = findViewById(R.id.quiz_image);

        // questions, questionId, score
        questionId = 1;
        score = 0;
        step = 1;
        try{
            questions = new QuestionDatabase();
        }
        catch(Exception ex){
            Log.e("Error", ex.getMessage());
        }

        // set images
        Drawable newPicture = getResources().getDrawable(R.drawable.ic_launcher_background);
        questionImage.setImageDrawable(newPicture);

        //Back to main activity listener
        backToMainButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                returnToMainActivity();
            }
        });
        nextQuestionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try{
                    nextQuestion();
                }
                catch (CloneNotSupportedException e){
                    Log.e("Next question fail", e.getMessage());
                }
            }
        });
        answer1Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                CheckSelectedAnswer(answer1Btn);
            }
        });
        answer2Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                CheckSelectedAnswer(answer2Btn);
            }
        });
        answer3Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                CheckSelectedAnswer(answer3Btn);
            }
        });
        answer4Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                CheckSelectedAnswer(answer4Btn);
            }
        });

    }

    private int getScoreValue(){
        //score = 75;
        return score;
    }

    private void returnToMainActivity(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("score_return", getScoreValue());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    private void nextQuestion() throws CloneNotSupportedException {
        int size = questions.getDatabaseSize();

        // Question from db by questionId(initial(1))
        try{
            selectedQuestion = questions.getQuestionCloneById(questionId);
        }
        catch (CloneNotSupportedException e){
            Log.e("Cloning question failed", e.getMessage());
        }


        // Draw image
        Drawable newPicture = getResources().getDrawable(selectedQuestion.getPhoto());
        questionImage.setImageDrawable(newPicture);

        //Set question and answers
        quizTitleView.setText("Quiz step:" + step);
        questionView.setText(selectedQuestion.getQuestion());
        answer1Btn.setText(selectedQuestion.getCorrectAnswer());
        answer2Btn.setText(selectedQuestion.getWrongAnswer(1));
        answer3Btn.setText(selectedQuestion.getWrongAnswer(2));
        answer4Btn.setText(selectedQuestion.getWrongAnswer(3));



        // Sprawdź czy nie wychodzimy ponad liczbę pytań i wróć do początku
        if(questionId < size ){
            questionId ++;
            step ++;
        }
        else{
            questionId = 1;
            step = 1;
        }


    }

    private void CheckSelectedAnswer(Button selectedBtn){
        // Pobierz nazwę klikniętego przycisku
        String answer = selectedBtn.getText().toString();

        if(selectedQuestion.getCorrectAnswer() == answer){
            score ++;
            selectedBtn.setBackgroundColor(getResources().getColor(R.color.colorSuccess));
            Toast myToast = Toast.makeText(this, "sukces", Toast.LENGTH_SHORT);
            myToast.show();
        }
        else{
            selectedBtn.setBackgroundColor(getResources().getColor(R.color.colorFailure));
            Toast myToast = Toast.makeText(this, "porażka", Toast.LENGTH_SHORT);
            myToast.show();
        }
    }

}
