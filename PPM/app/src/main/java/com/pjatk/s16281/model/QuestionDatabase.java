package com.pjatk.s16281.model;

import java.util.ArrayList;

public class QuestionDatabase {
    private ArrayList<QuestionItem> questions = new ArrayList<>();

    public QuestionDatabase(){ addQuestionToDatabase(); }

    public QuestionItem getQuestionById(int id){
        return questions.get(id);
    }

    private void addQuestionToDatabase(){
        questions.add(new QuestionItem(
                "Dlaczego w nazwie wirusa występuje słowo 'korona'? ",
                "@drawable/ic_launcher_foreground",
                "cząsteczki wirusa otoczone są strukturą w formie korony",
                "wyodrębniono go z rośliny 'wilczomlecza', czyli korony cierniowej",
                "pierwszym zakażonym na świecie był król Chin",
                "wirus powoduje efekt koronwania w zdrowych komórkach"
        ));
        questions.add(new QuestionItem(
                "Jak brzmi pełna nazwa koronawirusa? ",
                "@drawable/ic_launcher_foreground",
                "SARS-CoV-2",
                "nCov-2020",
                "nCov-2019",
                "Covid-19"
        ));
        questions.add(new QuestionItem(
                "Jaką chorobę powoduje nowy koronawirus? ",
                "@drawable/ic_launcher_foreground",
                "zapalenie płuc",
                "długotrwały wzwód",
                "niechęć do nauki",
                "popracie dla LGBT"
        ));
        questions.add(new QuestionItem(
                "Jaką chronić się przed wirusem? ",
                "@drawable/ic_launcher_foreground",
                "myć ręce, nie dotykać twarzy, omijać chorych",
                "brać leki przeciwzapalne",
                "przestać wychodzić w domu",
                "jeść dużo nabiału"
        ));
        questions.add(new QuestionItem(
                "Ile czasu powinno trwać właściwe mycie rąk? ",
                "@drawable/ic_launcher_foreground",
                "20 sekund",
                "2 minuty",
                "10 sekund",
                "tyle co Zdrowaś Mario"
        ));
        questions.add(new QuestionItem(
                "Co należy zrobić kiedy podejrzewamy zakażenie wirusem? ",
                "@drawable/ic_launcher_foreground",
                "zadzwonić na 997",
                "zadzwonić na specjalną infolinię",
                "pojechać autobusem do przychodni",
                "zadzwonić po AS Bytom"
        ));
        questions.add(new QuestionItem(
                "Jakie obiawy daje zakażenie koronawirusem? ",
                "@drawable/ic_launcher_foreground",
                "kaszel, duszności, ból mięśni, gorączka",
                "katar, biegunka, ból głowy",
                "wysypka, biegunka, ból brzucha",
                "przekrwione oczy, wypadanie włosów"
        ));
        questions.add(new QuestionItem(
                "Co chroni przed zakażeniem koronawirusem? ",
                "@drawable/ic_launcher_foreground",
                "absolutnie nic",
                "maseczka antybakteryjna/antywirusowa",
                "prezerywatywy",
                "szczepienia przeciw grypie"
        ));


    }


}
