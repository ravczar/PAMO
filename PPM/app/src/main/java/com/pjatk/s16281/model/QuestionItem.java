package com.pjatk.s16281.model;

import java.util.ArrayList;

public class QuestionItem {
    private String question;
    private String photo;
    private ArrayList <String> answers;

    public QuestionItem(
            String question,
            String photoLink,
            String correctAn,
            String wrongAn1,
            String wrongAn2,
            String wrongAn3)
    {
        this.question = question;
        this.photo = photoLink;
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
    public String getPhoto() { return photo; }
    public String getWrongAnswer(int id){ // 2,3,4
        return this.answers.get(id - 1);
    }


}
