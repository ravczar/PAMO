package com.pjatk.s16281.model;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class QuestionItem {
    private String question;
    private int quizPicture;
    private ArrayList <String> answers;

    public QuestionItem(
            String question,
            int picture,
            String correctAn,
            String wrongAn1,
            String wrongAn2,
            String wrongAn3)
    {
        this.answers = new ArrayList<>();
        this.question = question;
        this.quizPicture = picture;
        this.answers.add(correctAn);
        this.answers.add(wrongAn1);
        this.answers.add(wrongAn2);
        this.answers.add(wrongAn3);
    }

    public String getQuestion() {
        return question;
    }
    public String getCorrectAnswer() {
        return answers.get(0);
    }
    public int getPhoto() { return quizPicture; }
    public String getWrongAnswer(int id){ return this.answers.get(id); }


    public int getIdOfQuestionSting(String answerChecked){
        if(answers.contains(answerChecked)){
            for(String answer : answers){
                if(answer.equals(answerChecked))
                    return answers.indexOf(answer);
            }
        }
        return -1;
    }


}
