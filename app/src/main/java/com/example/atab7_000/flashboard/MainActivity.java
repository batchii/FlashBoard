package com.example.atab7_000.flashboard;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends TabActivity {

    //Where i got this implementation
//http://stackoverflow.com/questions/21474623/creating-an-android-app-using-tabhost-and-multiple-fragments

    private static final String TAG = "TabHostActivity";

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = getTabHost();

        TabHost.TabSpec firstSpec = tabHost.newTabSpec("first");
        firstSpec.setIndicator("Review", null);
        Intent firstIntent = new Intent(this, tab1.class);
        firstSpec.setContent(firstIntent);

        TabHost.TabSpec secondSpec = tabHost.newTabSpec("second");
        secondSpec.setIndicator("Insert cards", null);
        Intent secondIntent = new Intent(this, tab2.class);
        secondSpec.setContent(secondIntent);

        tabHost.addTab(firstSpec);
        tabHost.addTab(secondSpec);


        //tabHost = (TabHost) findViewById(android.R.id.tabhost);
        //tabHost.setOnTabChangedListener(this);
        //tabHost.setCurrentTab(0);
        //setupTabs();
    }

    private void setupTabs()
    {
        tabHost.setup();
        setupTab(new TextView(this), "tab1");
        setupTab(new TextView(this), "tab2");

    }

    private void setupTab(final View view, final String tag)
    {
        View tabview = createTabView(tabHost.getContext(), tag);

        TabHost.TabSpec setContent = tabHost.newTabSpec(tag)
                .setIndicator(tabview)
                .setContent(new TabHost.TabContentFactory()
                {
                    public View createTabContent(String tag)
                    {
                        return view;
                    }
                });
        tabHost.addTab(setContent);
    }

    private static View createTabView(final Context context, final String tabId)
    {
        int resourceId;
        if (tabId.equals("tab1"))
        {
            resourceId = R.layout.tab1;
        }
        else
        {
            resourceId = R.layout.tab2;
        }

        return LayoutInflater.from(context).inflate(resourceId, null);
    }

/**
    public void onTabChanged(String tabId)
    {
        Log.d(TAG, "onTabChanged(): tabId=" + tabId);

        if (tabId.equalsIgnoreCase("tab1"))
        {
            updateTab(android.R.id.tabcontent, new tab1(), tabId);
        }
        else
        {
            updateTab(android.R.id.tabcontent, new tab2(), tabId);
        }
    }
**/
    public void updateTab(int placeholder, Fragment fragment, String tabId)
    {
        //getSupportFragmentManager().beginTransaction()
                //.replace(placeholder, fragment, tabId)
                //.commit();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
