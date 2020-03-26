package com.pjatk.s16281.model;

import com.pjatk.s16281.R;
import java.util.ArrayList;

public class QuestionDatabase {
    private ArrayList<QuestionItem> questions;

    public QuestionDatabase (){
        this.questions = new ArrayList<>();
        addQuestionToDatabase();
    }

    public QuestionItem getQuestionById(int id){ return questions.get(id-1); }
    public QuestionItem getQuestionCloneById(int id) throws CloneNotSupportedException {
        return questions.get(id-1).clone();
    }

    public int getDatabaseSize(){
        return questions.size();
    }

    private void addQuestionToDatabase(){
        questions.add(new QuestionItem(
                "Dlaczego w nazwie wirusa występuje słowo 'korona'? ",
                R.drawable.korona4,
                "cząsteczki wirusa otoczone są strukturą w formie korony",
                "wyodrębniono go z rośliny 'wilczomlecza', czyli korony cierniowej",
                "pierwszym zakażonym na świecie był król Chin",
                "wirus powoduje efekt koronwania w zdrowych komórkach"
        ));
        questions.add(new QuestionItem(
                "Jak brzmi pełna nazwa koronawirusa? ",
                R.drawable.korona7,
                "SARS-CoV-2",
                "nCov-2020",
                "nCov-2019",
                "Covid-19"
        ));
        questions.add(new QuestionItem(
                "Jaką chorobę powoduje nowy koronawirus? ",
                R.drawable.korona5,
                "zapalenie płuc",
                "długotrwały wzwód",
                "niechęć do nauki",
                "popracie dla LGBT"
        ));
        questions.add(new QuestionItem(
                "Jaką chronić się przed wirusem? ",
                R.drawable.korona6,
                "myć ręce, nie dotykać twarzy, omijać chorych",
                "brać leki przeciwzapalne",
                "przestać wychodzić w domu",
                "jeść dużo nabiału"
        ));
        questions.add(new QuestionItem(
                "Ile czasu powinno trwać właściwe mycie rąk? ",
                R.drawable.korona2,
                "20 sekund",
                "2 minuty",
                "10 sekund",
                "tyle co Zdrowaś Mario"
        ));
        questions.add(new QuestionItem(
                "Co należy zrobić kiedy podejrzewamy zakażenie wirusem? ",
                R.drawable.korona8,
                "zadzwonić na specjalną infolinię",
                "zadzwonić na 997",
                "pojechać autobusem do przychodni",
                "zadzwonić po AS Bytom"
        ));
        questions.add(new QuestionItem(
                "Jakie obiawy daje zakażenie koronawirusem? ",
                R.drawable.korona3,
                "kaszel, duszności, ból mięśni, gorączka",
                "katar, biegunka, ból głowy",
                "wysypka, biegunka, ból brzucha",
                "przekrwione oczy, wypadanie włosów"
        ));
        questions.add(new QuestionItem(
                "Co chroni przed zakażeniem koronawirusem? ",
                R.drawable.korona1,
                "absolutnie nic",
                "maseczka antybakteryjna/antywirusowa",
                "prezerywatywy",
                "szczepienia przeciw grypie"
        ));


    }


}
