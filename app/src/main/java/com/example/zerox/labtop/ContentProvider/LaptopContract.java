package com.example.zerox.labtop.ContentProvider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Zerox on 11/11/2016.
 */

public class LaptopContract {
    public static final String CONTENT_AUTHORITY = "com.example.zerox.labtop.app";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_Laptop = "laptop";

    /* Inner class that defines the table contents of the weather table */
    public static final class LaptopEntry implements BaseColumns {

        public static final String TABLE_NAME = "laptop";

        public static final String COLUMN_Laptop_ID = "Laptop_ID";
        public static final String COLUMN_Laptop_Title = "Laptop_Title";
        public static final String COLUMN_Laptop_Url = "Laptop_image";
        public static final String COLUMN_Laptop_description = "Laptop_description";
        public static final String COLUMN_Laptop_price = "Laptop_price";


        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_Laptop).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_Laptop;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_Laptop;

        public static Uri buildLaptopUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


    }

}
