package com.example.zerox.labtop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.zerox.labtop.ContentProvider.LaptopContract;
import com.example.zerox.labtop.ContentProvider.LaptopDBHelper;

/**
 * Created by Zerox on 11/11/2016.
 */

public class Utility {


    public static int isFavorited(Context context, int id) {
        Cursor cursor = context.getContentResolver().query(
                LaptopContract.LaptopEntry.CONTENT_URI,
                null,   // projection
                LaptopContract.LaptopEntry.COLUMN_Laptop_ID + " = ?", // selection
                new String[]{Integer.toString(id)},   // selectionArgs
                null    // sort order
        );
        int numRows = cursor.getCount();
        cursor.close();
        return numRows;
    }

    public static void insert(LaptopDBHelper obj, String id, String Url, String price, String description, String Title) {

        SQLiteDatabase database = obj.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.clear();
        contentValues.put(LaptopContract.LaptopEntry.COLUMN_Laptop_ID, id);
        contentValues.put(LaptopContract.LaptopEntry.COLUMN_Laptop_Title, Title);
        contentValues.put(LaptopContract.LaptopEntry.COLUMN_Laptop_Url, Url);
        contentValues.put(LaptopContract.LaptopEntry.COLUMN_Laptop_description, description);
        contentValues.put(LaptopContract.LaptopEntry.COLUMN_Laptop_price, price);
        database.insert(LaptopContract.LaptopEntry.TABLE_NAME, null, contentValues);
        database.close();
    }

    public static void delete(LaptopDBHelper obj, String id) {
        SQLiteDatabase database = obj.getWritableDatabase();

        database.delete(LaptopContract.LaptopEntry.TABLE_NAME,
                LaptopContract.LaptopEntry.COLUMN_Laptop_ID + " = ?",
                new String[]{id});
        database.close();

    }

    //
    public static boolean isConnected(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager == null)
            return false;
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo == null)
            return false;
        return networkInfo.getState() == NetworkInfo.State.CONNECTED;
    }


}
