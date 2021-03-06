package com.example.atab7_000.flashboard;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity  {

    //Where i got this implementation
//http://stackoverflow.com/questions/21474623/creating-an-android-app-using-tabhost-and-multiple-fragments

    private static final String TAG = "TabHostActivity";

    private FragmentTabHost mTabHost;
    SharedPreferences sharedpreferences;
    private static String PREF_NAME="MyPREFERENCES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Flashboard");
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Review", null),
                tab1.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Insert Card", null),
                tab2.class, null);
        sharedpreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        //tabHost = (TabHost) findViewById(android.R.id.tabhost);
        //tabHost.setOnTabChangedListener(this);
        //tabHost.setCurrentTab(0);
        //setupTabs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
