package com.example.atab7_000.flashboard;

/**
 * Created by Nikhil on 3/18/2015.
 */
public class Card {
    private String question;
    private String answer;

    public Card(String q, String a) {
        question = q;
        answer = a;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
