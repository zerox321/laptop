package com.example.zerox.labtop.ContentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.zerox.labtop.ContentProvider.LaptopContract.LaptopEntry;

/**
 * Created by Zerox on 11/11/2016.
 */

public class LaptopDBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Movies.db";
    private static final int DATABASE_VERSION = 2;

    public LaptopDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_Movies_TABLE = "CREATE TABLE " + LaptopEntry.TABLE_NAME + " (" +
                LaptopEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LaptopEntry.COLUMN_Laptop_ID + " INTEGER," +
                LaptopEntry.COLUMN_Laptop_Title + " TEXT, " +
                LaptopEntry.COLUMN_Laptop_Url + " TEXT, " +
                LaptopEntry.COLUMN_Laptop_description + " TEXT, " +
                LaptopEntry.COLUMN_Laptop_price + " TEXT" +
                " );";
        sqLiteDatabase.execSQL(SQL_CREATE_Movies_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LaptopEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

