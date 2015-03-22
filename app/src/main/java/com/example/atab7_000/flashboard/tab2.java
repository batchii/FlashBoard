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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


public class tab2 extends Fragment implements View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "tab2";
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

        return v;
    }

    //On clicking a button do this
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.addset :
                // addset button clicked
                PromptDialog dlg = new PromptDialog(getActivity(), R.string.create_subject, R.string.subject_name) {
                    @Override
                    public boolean onOkClicked(String input) {
                        // do something
                        return true; // true = close dialog
                    }
                };
                dlg.show();
                break;
            case R.id.insert :
                // insert button clicked :
                Toast.makeText(getActivity(), "Added card!", Toast.LENGTH_LONG).show();
                break;
            // similarly for other buttons
        }
    }


}
