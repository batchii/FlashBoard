package com.example.atab7_000.flashboard;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class FlashCardTester extends ActionBarActivity {

    SharedPreferences myPrefs;
    Context context;
    String subject;
    //String question;
    //String answer;
    ArrayList<String> questions;
    ArrayList<String> answers;
    TextView text;
    boolean random;
    int index;
    boolean qDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_tester);
        setTitle("Flashboard");

        //Initialize Shared Preferences
        context = getApplicationContext();
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        //Get the subject
        subject = myPrefs.getString("subject", "Math");

        //Determine whether to randomize or not
        random = myPrefs.getBoolean("random", false);

        //Get the questions
        questions = tab1.dbAdapt.getAllQuestions(subject);

        //Get the answers
        answers = tab1.dbAdapt.getAllAnswers(subject);

        //Initialize starting position of arraylist, refers to both q and a
        if (random) {
            index = (int)(Math.random() * questions.size());
        } else {
            index = 0;
        }

        //Gain access to TextView object
        text = (TextView)findViewById(R.id.prompt);

        //Sets the initial text to the first question of the deck
        text.setText(questions.get(index));
        qDisplayed = true;




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flash_card_tester, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_discard:
                discard();
                return true;


        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    //Flip card by tapping on screen
    public void flip(View view){
        if (qDisplayed) {
            text.setText(answers.get(index));
            qDisplayed = false;
        } else {
            text.setText(questions.get(index));
            qDisplayed = true;
        }
        //Toast.makeText(this, "Flip", Toast.LENGTH_LONG).show();
    }

    //Go to previous card
    public void previous(View view){
        int prev = index;
        if (random) {
            while (index == prev) {
                index = (int) (Math.random() * questions.size());
            }
        } else {
            index--;
        }
        //index--;
        if (index < 0) {
            index = questions.size() - 1;
        }
        text.setText(questions.get(index));
        //Toast.makeText(this, "previous", Toast.LENGTH_LONG).show();

    }

    //Got to next card
    public void next(View view){
        int prev = index;
        if (random) {
            while (index == prev) {
                index = (int) (Math.random() * questions.size());
            }
        } else {
            index++;
        }
        //index++;
        if (index  == questions.size()) {
            index = 0;
        }
        text.setText(questions.get(index));
        //Toast.makeText(this, "next", Toast.LENGTH_LONG).show();

    }

    //Discard card
    public void discard(){
        //Store the question to use to remove the corresponding row in the db.
        String q = questions.get(index);

        //Set the appropriate text
        if (questions.size() - 1 == 0) {
            text.setText("Empty Deck.");
            Toast.makeText(this, "No more cards for this subject.", Toast.LENGTH_LONG).show();
            questions.remove(index);
            answers.remove(index);
            index = 0;
        } else if ((index + 1) == questions.size()) {
            text.setText(questions.get(0));
            questions.remove(index);
            answers.remove(index);
            index = 0;
        } else {
            text.setText(questions.get(index + 1));
            questions.remove(index);
            answers.remove(index);
        }

        //Find the appropriate row to delete in the db.
        long row = tab1.dbAdapt.getRowToDelete(q);

        //Delete from db.
        tab1.dbAdapt.removeCard(row);



        //Toast.makeText(this, "delete", Toast.LENGTH_LONG).show();

    }


}
