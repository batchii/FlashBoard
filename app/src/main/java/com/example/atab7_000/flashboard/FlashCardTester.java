package com.example.atab7_000.flashboard;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class FlashCardTester extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_tester);
        setTitle("Flashboard");
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

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    //Flip card by tapping on screen
    public void flip(View view){
        Toast.makeText(this, "Flip", Toast.LENGTH_LONG).show();
    }

    //Go to previous card
    public void previous(View view){
        Toast.makeText(this, "previous", Toast.LENGTH_LONG).show();

    }

    //Got to next card
    public void next(View view){
        Toast.makeText(this, "next", Toast.LENGTH_LONG).show();

    }

    //Discard card
    public void discard(View view){
        Toast.makeText(this, "delete", Toast.LENGTH_LONG).show();

    }

}
