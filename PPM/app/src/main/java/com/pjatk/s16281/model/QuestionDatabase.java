package com.pjatk.s16281.model;

import com.pjatk.s16281.R;
import java.util.ArrayList;

public class QuestionDatabase {
    private ArrayList<QuestionItem> questions;

    public QuestionDatabase (){
        this.questions = new ArrayList<>();
        addQuestionToDatabase();
    }

    public int getQuestionCount() { return questions.size()-1; } // one fake question
    public QuestionItem getQuestionById(int id){ return questions.get(id-1); }
    public QuestionItem getQuestionCloneById(int id) throws CloneNotSupportedException {
        return questions.get(id-1).clone();
    }

    public int getDatabaseSize(){
        return questions.size();
    }

    private void addQuestionToDatabase(){
        // Q1
        questions.add(new QuestionItem(
                "Dlaczego w nazwie wirusa występuje słowo 'korona'? ",
                R.drawable.korona4,
                "wirus ma cząsteczki podobne do korony",
                "zarażenie nim powoduje wstąpnie na tron Anglii",
                "pierwszym zakażonym na świecie był król Chin",
                "wirus kolokwialnie koronuje zdrowe komórki"
        ));
        // Q2
        questions.add(new QuestionItem(
                "Jak brzmi pełna nazwa koronawirusa? ",
                R.drawable.korona7,
                "SARS-CoV-2",
                "nCov-2020",
                "nCov-2019",
                "Covid-19"
        ));
        // Q3
        questions.add(new QuestionItem(
                "Jaką chorobę powoduje nowy koronawirus? ",
                R.drawable.korona5,
                "zapalenie płuc",
                "raka prostaty",
                "niechęć do Javy",
                "konwersję na LGBT"
        ));
        // Q4
        questions.add(new QuestionItem(
                "Jaką chronić się przed wirusem? ",
                R.drawable.korona6,
                "myć ręce, nie dotykać twarzy, omijać chorych",
                "brać leki przeciwzapalne",
                "przestać wychodzić w domu",
                "jeść dużo nabiału"
        ));
        // Q5
        questions.add(new QuestionItem(
                "Ile czasu powinno trwać właściwe mycie rąk? ",
                R.drawable.korona2,
                "20 sekund",
                "2 minuty",
                "10 sekund",
                "tyle co Zdrowaś Mario"
        ));
        // Q6
        questions.add(new QuestionItem(
                "Co należy zrobić kiedy podejrzewamy zakażenie wirusem? ",
                R.drawable.korona8,
                "zadzwonić na specjalną infolinię",
                "zadzwonić na 997",
                "pojechać autobusem do przychodni",
                "zadzwonić po AS Bytom"
        ));
        //Q7
        questions.add(new QuestionItem(
                "Jakie objawy daje zakażenie koronawirusem? ",
                R.drawable.korona3,
                "kaszel, duszności, ból mięśni, gorączka",
                "katar, biegunka, ból głowy",
                "wysypka, biegunka, ból brzucha",
                "przekrwione oczy, wypadanie włosów"
        ));
        //Q8
        questions.add(new QuestionItem(
                "Co chroni przed zakażeniem koronawirusem? ",
                R.drawable.korona1,
                "absolutnie nic",
                "maseczka antybakteryjna/antywirusowa",
                "prezerywatywy",
                "szczepienia przeciw grypie"
        ));
        //Q9 - empty
        questions.add(new QuestionItem(
                "Empty ",
                R.drawable.ic_launcher_background,
                "Empty",
                "Empty",
                "Empty",
                "Empty"
        ));


    }


}
