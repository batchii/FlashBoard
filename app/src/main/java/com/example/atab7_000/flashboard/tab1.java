package com.example.atab7_000.flashboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;

import static android.app.AlertDialog.Builder;


public class tab1 extends Fragment { //extends Fragment originally
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "tab1";
    protected static FlashCarddbAdapter dbAdapt;
    Context context;
    Spinner spinner;
    CheckBox random;
    ArrayAdapter<String> spinnerAdapter;
    SharedPreferences myPrefs;
    
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1, container, false);

        //tab1.dbAdapt.getCard();
        //Set up DB
        context = v.getContext();
        dbAdapt = new FlashCarddbAdapter(context);
        dbAdapt.open();

        //dbAdapt.deleteAllCards();

        //Initialize Shared Preferences
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);


        //Access spinner and set up adapter
        spinner = (Spinner) v.findViewById(R.id.choose_subject_pg1);
        spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        populateSpinner();

        //Access checkbox
        random = (CheckBox) v.findViewById(R.id.randomize);

        //For the buttons

        Button button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Otherwise, we will go to the tester activity.

                //Set up prefs editor
                SharedPreferences.Editor peditor = myPrefs.edit();

                //Get the value of the selected subject
                String subj = (String) spinner.getSelectedItem();


                //If there are no subjects in the db, then display the alert below.
                if (subj == null) {
                    Builder alert = new Builder(getActivity());
                    alert.setTitle("Error");
                    alert.setMessage("Please add and choose a deck before trying to test");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                } else {
                    //Pass the selected subject through shared prefs to the tester.
                    //Pass the value of the randomize checkbox through shared prefs to the tester.
                    peditor.putString("subject", subj);
                    if (random.isChecked()) {
                        peditor.putBoolean("random", true);
                    } else {
                        peditor.putBoolean("random", false);
                    }
                    peditor.commit();
                    Intent intent = new Intent(v.getContext(), FlashCardTester.class);
                    startActivity(intent);
                }

/*
                //HIS STUFF ORIGINALLY
                SharedPreferences preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();




                //This isn't working
                String deck = String.valueOf(((Spinner)getView().findViewById(R.id.choose_subject_pg1)).getSelectedItem());
                if(deck == null){
                    Builder alert = new Builder(getActivity());
                    alert.setTitle("Error");
                    alert.setMessage("Please add and choose a deck before trying to test");
                    alert.setPositiveButton("OK",null);
                    alert.show();
                } else {
                    editor.putString("deck", deck);
                    String randomize = String.valueOf(((CheckBox) getView().findViewById(R.id.randomize)).isChecked());
                    editor.putString("randomize", randomize);
                    Intent intent = new Intent(v.getContext(), FlashCardTester.class);
                    startActivity(intent);
                }            }*/

                //return v;
            }
        });
        return v;
    }

    public void populateSpinner(){
        ArrayList<String> subjects = dbAdapt.getAllSubjects();
        for (int i = 0; i < subjects.size(); i++) {
            spinnerAdapter.add(subjects.get(i));
        }
        spinnerAdapter.notifyDataSetChanged();
    }

/**
    @Override
    public void onActivityCreated(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onActivityCreated");
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState)
    {
        Log.i(TAG, "onCreateView");

        return inflater.inflate(R.layout.tab1, container, false);
    }
*/
}
