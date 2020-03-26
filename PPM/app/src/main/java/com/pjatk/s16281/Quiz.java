package com.pjatk.s16281;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pjatk.s16281.model.QuestionDatabase;
import com.pjatk.s16281.model.QuestionItem;

import java.text.MessageFormat;

public class Quiz extends AppCompatActivity {
    private Button backToMainButton, nextQuestionButton, answer1Btn, answer2Btn, answer3Btn, answer4Btn;
    private Button[] answerButtons;
    private TextView questionView, quizTitleView, scoreView;
    private ImageView questionImage;
    private int score, questionId, step, questionsTotalCount;
    private QuestionDatabase questions;
    private QuestionItem selectedQuestion;
    private Boolean answeredCorrect, answerAlreadyGiven;

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
        answerAlreadyGiven = false;
        try{
            questions = new QuestionDatabase();
            questionsTotalCount = questions.getQuestionCount();
            nextQuestion();
        }
        catch(Exception ex){
            Log.e("Error", ex.getMessage());
        }

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
                    score = 0;
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
                answerAlreadyGiven = true;
                try{
                    nextQuestion();
                } catch (Exception e){
                    Log.e("Fail on next step", e.getMessage());
                }
            }
        });
        answer2Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setDefaultButtonBackgroundToAllAnswersBtns();
                CheckSelectedAnswer(answer2Btn);
                answerAlreadyGiven = true;
                try{
                    nextQuestion();
                } catch (Exception e){
                    Log.e("Fail after assess score", e.getMessage());
                }

            }
        });
        answer3Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setDefaultButtonBackgroundToAllAnswersBtns();
                CheckSelectedAnswer(answer3Btn);
                answerAlreadyGiven = true;
                try{
                    nextQuestion();
                } catch (Exception e){
                    Log.e("Fail after assess score", e.getMessage());
                }
            }
        });
        answer4Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setDefaultButtonBackgroundToAllAnswersBtns();
                CheckSelectedAnswer(answer4Btn);
                answerAlreadyGiven = true;
                try{
                    nextQuestion();
                } catch (Exception e){
                    Log.e("Fail after assess score", e.getMessage());
                }
            }
        });

    }

    private int getScoreValue(){
        return score;
    }

    private void returnToMainActivity(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("percent_return", countPercentResult());
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
        quizTitleView.setText("Quiz step: " + step + "/8");
        questionView.setText(selectedQuestion.getQuestion());
        answer1Btn.setText(selectedQuestion.getAnswerById(0));
        answer2Btn.setText(selectedQuestion.getAnswerById(1));
        answer3Btn.setText(selectedQuestion.getAnswerById(2));
        answer4Btn.setText(selectedQuestion.getAnswerById(3));
        scoreView.setText("Score: " + score);

        // Display on View
        displayAllQuizRelatedItems();

        // Check if we do not exceed number of available questions
        if(questionId <= size-1 ){
            questionId ++;
            step ++;
        }
        else{
            questionId = 1;
            step = 1;
            hideAllQuizRelatedItems();
            makeEndGameToast();
        }

    }

    private void setDefaultButtonBackgroundToAllAnswersBtns(){
        for (Button button: answerButtons) {
            button.setBackgroundColor(getResources().getColor(R.color.colorLigtGray));
        }
    }

    private void CheckSelectedAnswer(Button selectedBtn){
        // Get text displayed in answer button that was clicked
        String answer = selectedBtn.getText().toString();

        if(selectedQuestion.getCorrectAnswer() == answer){
            answeredCorrect = true;
            selectedBtn.setBackgroundColor(getResources().getColor(R.color.colorSuccess));
            makeAnswerAssessmentToast(answeredCorrect);
            score = answeredCorrect ? ++score : score;

        }
        else{
            answeredCorrect = false;
            selectedBtn.setBackgroundColor(getResources().getColor(R.color.colorFailure));
            makeAnswerAssessmentToast(answeredCorrect);
        }
    }

    private void makeAnswerAssessmentToast(Boolean answerStatus){
        String text = answerStatus ? "Success :)" : "Failure :(";
        int toastBg = answerStatus ? getResources().getColor(R.color.colorSuccess) : getResources().getColor(R.color.colorFailure);

        Toast myToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        myToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 650);
        View view = myToast.getView();
        view.setBackgroundColor(toastBg);
        myToast.show();
    }

    private double countPercentResult() {
        double percent  = (double)score/(double)questionsTotalCount*100;
        return percent;
    }

    private void makeEndGameToast(){
        String text = MessageFormat.format("You have finished the quiz with result of : {0}%", countPercentResult());
        Toast myToast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 650);
        View view = myToast.getView();
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        myToast.show();
    }

    private void hideAllQuizRelatedItems(){
        quizTitleView.setVisibility(View.INVISIBLE);
        questionImage.setVisibility(View.INVISIBLE);
        questionView.setVisibility(View.INVISIBLE);
        answer1Btn.setVisibility(View.INVISIBLE);
        answer2Btn.setVisibility(View.INVISIBLE);
        answer3Btn.setVisibility(View.INVISIBLE);
        answer4Btn.setVisibility(View.INVISIBLE);
        nextQuestionButton.setVisibility(View.VISIBLE);
    }

    private void displayAllQuizRelatedItems(){
        quizTitleView.setVisibility(View.VISIBLE);
        questionImage.setVisibility(View.VISIBLE);
        questionView.setVisibility(View.VISIBLE);
        answer1Btn.setVisibility(View.VISIBLE);
        answer2Btn.setVisibility(View.VISIBLE);
        answer3Btn.setVisibility(View.VISIBLE);
        answer4Btn.setVisibility(View.VISIBLE);
        nextQuestionButton.setVisibility(View.INVISIBLE);
    }

}
