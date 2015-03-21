package com.example.atab7_000.flashboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import static android.app.AlertDialog.Builder;


public class tab1 extends Fragment { //extends Fragment originally
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "tab1";

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1, container, false);
        Button button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();



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
                }            }
        });
        return v;
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
