package com.example.atab7_000.flashboard;

import java.util.ArrayList;

/**
 * Created by Nikhil on 3/18/2015.
 */
public class Deck {
    private String subject;
    private ArrayList<Card> flashcards;

    public Deck(String subj, ArrayList<Card> cards) {
        subject = subj;
        flashcards = cards;
    }

    public String getSubject() {
        return subject;
    }

    public ArrayList<Card> getCards() {
        return flashcards;
    }
}
