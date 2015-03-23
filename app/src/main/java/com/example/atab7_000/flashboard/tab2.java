package com.example.atab7_000.flashboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


public class tab2 extends Fragment implements View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "tab2";
    //private String subj;
    EditText question;
    EditText answer;
    Spinner spinner;
    ArrayAdapter<String> spinnerAdapter;
    Context context;

    @Override
    public void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab2, container, false);

        //Adding buttons
        if (v != null) {
            ImageButton addButton = (ImageButton) v.findViewById(R.id.addset);
            Log.d(TAG, "View is not null");

            Button insertButton = (Button) v.findViewById(R.id.insert);

            if (addButton != null) {
                addButton.setOnClickListener(this);
                Log.d(TAG, "mButton is not null");
            }
            if (insertButton != null){
                insertButton.setOnClickListener(this);
                Log.d(TAG, "insertButton is not null");
            }
        }


        context = v.getContext();
        question = (EditText)v.findViewById(R.id.questionEditText);
        answer = (EditText)v.findViewById(R.id.answerEditText);
        spinner = (Spinner)v.findViewById(R.id.choose_subject_pg2);
        spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        //Testing whether database is working.
        spinnerAdapter.add(tab1.dbAdapt.getCard(1).getSubject());
        spinnerAdapter.notifyDataSetChanged();

        return v;
    }

    //On clicking a button do this
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            //String subj; //not sure why this is an error.  look up annotations.
            case R.id.addset :
                // addset button clicked
                PromptDialog dlg = new PromptDialog(getActivity(), R.string.create_subject, R.string.subject_name) {
                    @Override
                    public boolean onOkClicked(String input) {
                        // do something

                        //The subject needs to be saved globally so that when insert is selected, it can
                        //be referred to when creating the Card object to insert.


                        if (input.equals("")) {
                            //subj = "";
                            Toast.makeText(getActivity(), "No subject added.", Toast.LENGTH_LONG).show();
                            return false; //trying to not close the dialog until a subject is added or cancel is pressed.
                        } else {
                            spinnerAdapter.add(input);
                            spinnerAdapter.notifyDataSetChanged();
                            int len = spinnerAdapter.getCount();
                            spinner.setSelection(len-1);
                            //subj = input;
                        }


                        //The input needs to be added to the spinner.  It will also need to be added to
                        //corresponding shared preferences value (db might take care of this.  simply load
                        //values each time page opens.)


                        return true; // true = close dialog
                    }
                };
                dlg.show();
                break;
            case R.id.insert :
                // insert button clicked :

                //Find the values of the question input and answer input and selected subject.


                String subj = (String) spinner.getSelectedItem();
                String q = question.getText().toString();
                String a = answer.getText().toString();

                //Check to see if information is inputted.
                if (q.equals("") && a.equals("")) {
                    Toast.makeText(getActivity(), "No question and answer inputted.", Toast.LENGTH_LONG).show();
                    //end the method without changing anything.
                    break;
                } else if (q.equals("")) {
                    Toast.makeText(getActivity(), "No question inputted.", Toast.LENGTH_LONG).show();
                    //end the method without changing anything.
                    break;
                } else if (a.equals("")) {
                    Toast.makeText(getActivity(), "No answer inputted.", Toast.LENGTH_LONG).show();
                    //end the method without changing anything.
                    break;
                }

                //Create a Card object with the corresponding subject, question, and answer.
                Card c = new Card(subj, q, a);


                //Add the card to the database.
                tab1.dbAdapt.insertCard(c);


                Toast.makeText(getActivity(), "Added card!", Toast.LENGTH_LONG).show();

                //Clear the edit text fields.
                question.setText("");
                answer.setText("");

                break;
            // similarly for other buttons
        }
    }


}
