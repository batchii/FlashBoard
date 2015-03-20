package com.example.atab7_000.flashboard;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class tab1 extends Activity { //extends Fragment originally
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "tab1";

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.tab1);
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
