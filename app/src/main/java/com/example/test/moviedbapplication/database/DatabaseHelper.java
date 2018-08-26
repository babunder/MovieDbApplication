package com.example.test.moviedbapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by babunder.prajapati on 25/08/2018.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Information
    static final String DB_NAME = "MOVIE.DB";
    // database version
    static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseTable.create_favorite_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DatabaseTable.TABLE_FAVORITE_MOVIE);
        onCreate(db);
    }
}
