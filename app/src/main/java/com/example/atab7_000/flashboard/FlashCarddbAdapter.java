package com.example.atab7_000.flashboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nikhil on 3/18/2015.
 */
public class FlashCarddbAdapter {
    private SQLiteDatabase db;
    private FlashCarddbHelper dbHelper;
    private final Context context;

    private static final String DB_NAME = "flashboard.db";
    private static final int DB_VERSION = 3;  // when you add or delete fields, you must update the version number!


    //Not necessary yet.
    //private static final String SUBJECT_TABLE = "subjects";
    //public static final String SBJ_ID = "sbj_id";
    //public static final String SBJ_WHAT = "sbj_what";
    //public static final String SBJ_COLS = {SBJ_ID, SBJ_WHAT};


    private static final String DECK_TABLE = "decks";
    public static final String DECK_ID = "deck_id";
    public static final String DECK_SUBJ = "deck_subj";
    public static final String DECK_Q = "deck_q";
    public static final String DECK_A = "deck_a";
    public static final String[] DECK_COLS = {DECK_ID, DECK_SUBJ, DECK_Q, DECK_A};

    public FlashCarddbAdapter(Context ctx) {
        context = ctx;
        dbHelper = new FlashCarddbHelper(context, DB_NAME, null, DB_VERSION);
    }

    public void open() throws SQLiteException {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    public void close() {
        db.close();
    }

    //database update methods

    public long insertCard(Card c) {
        //adds a physical deck to database
        ContentValues cvalues = new ContentValues();
        cvalues.put(DECK_SUBJ, c.getSubject());
        cvalues.put(DECK_Q, c.getQuestion());
        cvalues.put(DECK_A, c.getAnswer());

        return db.insert(DECK_TABLE, null, cvalues);
    }

    public boolean removeCard(long row) {
        return db.delete(DECK_TABLE, DECK_ID+"="+row, null) > 0;
    }

    //Not required I don't think.
    //public void clearDB() {
    //    db.delete(DECK_TABLE, null, null);
    //}

    public Card getCard(long row) throws SQLException {
        Cursor cursor = db.query(true, DECK_TABLE, DECK_COLS, DECK_ID+"="+row, null, null, null, null, null);
        if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
            throw new SQLException("No cards found for row: " + row);
        }
        int subjIndex = cursor.getColumnIndex(DECK_SUBJ);
        Card result = new Card(cursor.getString(subjIndex), cursor.getString(2), cursor.getString(3));
        return result;
    }

    public Cursor getAllCards(){
        return db.query(DECK_TABLE, DECK_COLS, null, null, null, null, null);
    }

    public ArrayList<String> getAllSubjects() {
        ArrayList<String> subjects = new ArrayList<String>();
        String subj;
        Cursor cursor = getAllCards();
        int rows = cursor.getCount();
        for (int i = 1; i <= rows; i++) {
            subj = this.getCard(i).getSubject();
            if (! subjects.contains(subj)) {
                subjects.add(subj);
            }
        }
        return subjects;
    }

    public ArrayList<String> getAllQuestions(String subj) {
        ArrayList<String> questions = new ArrayList<String>();
        Cursor cursor = getAllCards();
        int rows = cursor.getCount();
        Card c;
        for (int i = 1; i <= rows; i++) {
            c = this.getCard(i);
            if (c.getSubject().equals(subj)) {
                questions.add(c.getQuestion());
            }
        }
        return questions;
    }

    public ArrayList<String> getAllAnswers(String subj) {
        ArrayList<String> answers = new ArrayList<String>();
        Cursor cursor = getAllCards();
        int rows = cursor.getCount();
        Card c;
        for (int i = 1; i <= rows; i++) {
            c = this.getCard(i);
            if (c.getSubject().equals(subj)) {
                answers.add(c.getAnswer());
            }
        }
        return answers;
    }

    public long getRowToDelete(String question) {
        long row = 0;
        Cursor cursor = getAllCards();
        int rows = cursor.getCount();
        Card c;
        for (int i = 1; i <= rows; i++) {
            c = this.getCard(i);
            if (c.getQuestion().equals(question)) {
                row = (long)i;
            }
        }
        return row;
    }


    //public void insertCard(String subject, Card c) {
        //goes through database and finds the appropriate deck using
        //the subject to add the card to the arraylist in the deck object
    //}

    private static class FlashCarddbHelper extends SQLiteOpenHelper {

        // SQL statement to create a new database table
        private static final String DB_CREATE = "CREATE TABLE " + DECK_TABLE
                + " (" + DECK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DECK_SUBJ + " TEXT,"
                + DECK_Q + " TEXT, " + DECK_A + " TEXT);";

        public FlashCarddbHelper(Context context, String name, CursorFactory fct, int version) {
            super(context, name, fct, version);
        }

        @Override
        public void onCreate(SQLiteDatabase adb) {
            adb.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase adb, int oldVersion, int newVersion) {
            Log.w("FlashCarddb", "upgrading from version " + oldVersion + " to "
                    + newVersion + ", destroying old data");
            // drop old table if it exists, create new one
            // better to migrate existing data into new table
            adb.execSQL("DROP TABLE IF EXISTS " + DECK_TABLE);
            onCreate(adb);
        }
    } // GPAdbHelper class

}
