package com.example.test.moviedbapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.test.moviedbapplication.model.Categories;
import com.example.test.moviedbapplication.viewmodel.CategoryViewModel;

import java.util.ArrayList;

/**
 * Created by babunder.prajapati on 25/08/2018.
 */

public class DatabaseAdapter {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase db;

    public DatabaseAdapter(Context c) {
        context = c;
    }

    public DatabaseAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        return this;
    }

    public void close() {
        dbHelper.close();
    }


    /**
     * Method to create ContentValue for Favorite Table
     *
     * @return ContentValues
     */
    public ContentValues createTransactionValues(CategoryViewModel details) {

        ContentValues values = new ContentValues();
        values.put(DatabaseTable.MOVIE_ID, details.id);
        values.put(DatabaseTable.TITLE, details.title);
        values.put(DatabaseTable.DESCRIPTION, details.desc);
        values.put(DatabaseTable.IMAGEPATH, details.imagepath);
        return values;
    }


    /**
     * Method to insert Record to Favorite Movie details
     */
    public synchronized void insertFavoriteMovie(CategoryViewModel mDetails) {

        SQLiteDatabase db = null;
        db = dbHelper.getWritableDatabase();
        long rowId = 0;

        try {
            // Try to start Database
            if (db != null) {

                db.beginTransaction();
                ContentValues values = new ContentValues();
                values = createTransactionValues(mDetails);
                rowId = db.insert(DatabaseTable.TABLE_FAVORITE_MOVIE, null, values);
                db.setTransactionSuccessful();
                db.endTransaction();
                db.close();
            }

        } catch (Exception ex) {
            Log.e(" DB", ex.getMessage().toString());
        }
    }


    /**
     * Method to fetch Selected List of Entries
     *
     * @return
     */
    public synchronized ArrayList<CategoryViewModel> fetchFavoriteMovie() {

        ArrayList<CategoryViewModel> lstDetails = new ArrayList<CategoryViewModel>();
        SQLiteDatabase db = null;
        Cursor fetchCursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            if (db != null) {
                fetchCursor = db.query(true, DatabaseTable.TABLE_FAVORITE_MOVIE, null,
                        null, null,null, null, null, null);

                if (fetchCursor != null) {
                    if (fetchCursor.moveToFirst()) {
                        do {
                            Categories details = new Categories();
                            int Id = fetchCursor.getInt(fetchCursor.getColumnIndexOrThrow(DatabaseTable.MOVIE_ID));
                            details.id = String.valueOf(Id);
                            String strTitle = fetchCursor.getString(fetchCursor.getColumnIndexOrThrow(DatabaseTable.TITLE));
                            details.title = strTitle;
                            String strDesc = fetchCursor.getString(fetchCursor.getColumnIndexOrThrow(DatabaseTable.DESCRIPTION));
                            details.desc = strDesc;
                            String strImagePath = fetchCursor.getString(fetchCursor.getColumnIndexOrThrow(DatabaseTable.IMAGEPATH));
                            details.imagepath = strImagePath;

                            CategoryViewModel viewModel = new CategoryViewModel(details);
                            lstDetails.add(viewModel);

                        } while (fetchCursor.moveToNext());
                    }
                }
            }

            fetchCursor.close();
            db.close();
        } catch (Exception e) {
            Log.e("Opening DB", e.getMessage());
        }

        return lstDetails;
    }


    /**
     * Method to delete the favourite from database
     * @param strMovieId
     * @return
     */
    public synchronized boolean deleteFavorite(String strMovieId) {

        SQLiteDatabase db = null;
        db = dbHelper.getWritableDatabase();
        boolean isDeleted = false;

        try {
            // Try to start Database
            if (db != null) {
                db.beginTransaction();
                isDeleted = db.delete(DatabaseTable.TABLE_FAVORITE_MOVIE, DatabaseTable.MOVIE_ID + "=" + strMovieId, null) > 0;
                db.setTransactionSuccessful();
                db.endTransaction();
            }
            db.close();

        } catch (Exception ex) {
            Log.e("Opening DB", ex.getMessage().toString());
        }

        return isDeleted;
    }


    /**
     * Method to check if record exist in database
     * @return
     */
    public synchronized boolean isRecordExist(String strMovieId) {

        boolean isRecordFound = false;
        SQLiteDatabase db = null;
        Cursor fetchCursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            if (db != null) {
                fetchCursor = db.query(true, DatabaseTable.TABLE_FAVORITE_MOVIE, null,
                        DatabaseTable.MOVIE_ID + "= ?", new String[]{strMovieId}, null, null, null, null);

                if (fetchCursor != null) {
                   if(fetchCursor.getCount()>0){
                       isRecordFound = true;
                   }
                }
            }

            fetchCursor.close();
            db.close();
        } catch (Exception e) {
            Log.e(" DB", e.getMessage());
        }

        return isRecordFound;
    }

    /**
     * Method to check if record exist in database
     * @return
     */
    public synchronized CategoryViewModel getFavoriteById(String strMovieId) {

        boolean isRecordFound = false;
        SQLiteDatabase db = null;
        Cursor fetchCursor = null;
        CategoryViewModel viewModel = new CategoryViewModel();
        try {
            db = dbHelper.getReadableDatabase();
            if (db != null) {
                fetchCursor = db.query(true, DatabaseTable.TABLE_FAVORITE_MOVIE, null,
                        DatabaseTable.MOVIE_ID + "= ?", new String[]{strMovieId}, null, null, null, null);

                if (fetchCursor != null) {
                    if (fetchCursor.moveToFirst()) {
                        do {
                            Categories details = new Categories();
                            int Id = fetchCursor.getInt(fetchCursor.getColumnIndexOrThrow(DatabaseTable.MOVIE_ID));
                            details.id = String.valueOf(Id);
                            String strTitle = fetchCursor.getString(fetchCursor.getColumnIndexOrThrow(DatabaseTable.TITLE));
                            details.title = strTitle;
                            String strDesc = fetchCursor.getString(fetchCursor.getColumnIndexOrThrow(DatabaseTable.DESCRIPTION));
                            details.desc = strDesc;
                            String strImagePath = fetchCursor.getString(fetchCursor.getColumnIndexOrThrow(DatabaseTable.IMAGEPATH));
                            details.imagepath = strImagePath;

                             viewModel = new CategoryViewModel(details);

                        } while (fetchCursor.moveToNext());
                    }
                }
            }

            fetchCursor.close();
            db.close();
        } catch (Exception e) {
            Log.e(" DB", e.getMessage());
        }

        return viewModel;
    }
}
