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

import java.util.Collections;

public class Quiz extends AppCompatActivity {
    private Button backToMainButton, nextQuestionButton, answer1Btn, answer2Btn, answer3Btn, answer4Btn;
    private Button[] answerButtons;
    private TextView questionView, quizTitleView, scoreView;
    private ImageView questionImage;
    private int score, questionId, step;
    private QuestionDatabase questions;
    private QuestionItem selectedQuestion;
    private Boolean answeredCorrect;

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
        answerButtons = new Button[] {answer1Btn, answer2Btn, answer3Btn, answer4Btn};
        questionView = findViewById(R.id.qestion_view);
        quizTitleView = findViewById(R.id.quiz_title);
        scoreView = findViewById(R.id.score_view);
        nextQuestionButton = findViewById(R.id.button_next);
        questionImage = findViewById(R.id.quiz_image);

        // questions, questionId, score
        questionId = 1;
        score = 0;
        step = 1;
        answeredCorrect = false;
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
                setDefaultButtonBackgroundToAllAnswersBtns();
                CheckSelectedAnswer(answer1Btn);
            }
        });
        answer2Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setDefaultButtonBackgroundToAllAnswersBtns();
                CheckSelectedAnswer(answer2Btn);
            }
        });
        answer3Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setDefaultButtonBackgroundToAllAnswersBtns();
                CheckSelectedAnswer(answer3Btn);
            }
        });
        answer4Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setDefaultButtonBackgroundToAllAnswersBtns();
                CheckSelectedAnswer(answer4Btn);
            }
        });

    }

    private int getScoreValue(){
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

        // Question from db by questionId(initial(1)) clone! shuffled!
        try{
            selectedQuestion = questions.getQuestionCloneById(questionId);
            selectedQuestion.ShuffleMyAnswersForDisplay();
        }
        catch (CloneNotSupportedException e){
            Log.e("Cloning question failed", e.getMessage());
        }


        // Draw image
        Drawable newPicture = getResources().getDrawable(selectedQuestion.getPhoto());
        questionImage.setImageDrawable(newPicture);

        //Set question and answers
        setDefaultButtonBackgroundToAllAnswersBtns();
        quizTitleView.setText("Quiz step: " + step);
        questionView.setText(selectedQuestion.getQuestion());
        answer1Btn.setText(selectedQuestion.getAnswerById(0));
        answer2Btn.setText(selectedQuestion.getAnswerById(1));
        answer3Btn.setText(selectedQuestion.getAnswerById(2));
        answer4Btn.setText(selectedQuestion.getAnswerById(3));

        // Sprawdź czy nie wychodzimy ponad liczbę pytań i wróć do początku
        if(questionId < size ){
            score = answeredCorrect ? ++score : score;
            scoreView.setText("Score: " + score);
            questionId ++;
            step ++;
        }
        else{
            questionId = 1;
            step = 1;
        }

    }

    private void setDefaultButtonBackgroundToAllAnswersBtns(){
        for (Button button: answerButtons) {
            button.setBackgroundColor(getResources().getColor(R.color.colorLigtGray));
        }
    }

    private void CheckSelectedAnswer(Button selectedBtn){
        // Pobierz nazwę klikniętego przycisku
        String answer = selectedBtn.getText().toString();

        if(selectedQuestion.getCorrectAnswer() == answer){
            answeredCorrect = true;
            selectedBtn.setBackgroundColor(getResources().getColor(R.color.colorSuccess));
            Toast myToast = Toast.makeText(this, "Success", Toast.LENGTH_SHORT);
            myToast.show();
        }
        else{
            answeredCorrect = false;
            selectedBtn.setBackgroundColor(getResources().getColor(R.color.colorFailure));
            Toast myToast = Toast.makeText(this, "Failure", Toast.LENGTH_SHORT);
            myToast.show();
        }
    }

}
