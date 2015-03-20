package com.example.atab7_000.flashboard;

/**
 * Created by Nikhil on 3/18/2015.
 */
public class Card {
    private String subject; //temporarily putting this here.
    private String question;
    private String answer;

    public Card(String s, String q, String a) {
        subject = s;
        question = q;
        answer = a;
    }

    public String getSubject() {
        return subject;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
