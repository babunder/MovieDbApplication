package com.example.test.moviedbapplication.database;

/**
 * Created by babunder.prajapati on 25/08/2018.
 */

public class DatabaseTable {

    /*=====================TABLE NAME=========================================================*/
    public static String TABLE_FAVORITE_MOVIE = "favourite_movie";
    /*=======================================================================================*/
    // CONSTANT COLUMN NAME
    public static String _ID = "_id";
    public static String MOVIE_ID = "movie_id";
    public static String TITLE = "title";
    public static String DESCRIPTION = "desc";
    public static String IMAGEPATH = "imagepath";
    /*=========================CREATE TABLE===============================================*/
    // 1) TABLE_FAVORITE_MOVIE
    public static final String create_favorite_table = "create table " + TABLE_FAVORITE_MOVIE +
            "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MOVIE_ID + " TEXT NOT NULL, "
            + TITLE + " TEXT NOT NULL, "
            + DESCRIPTION + " TEXT NOT NULL, "
            + IMAGEPATH + "  TEXT NOT NULL);";
}
