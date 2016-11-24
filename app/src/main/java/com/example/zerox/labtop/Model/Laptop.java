package com.example.zerox.labtop.Model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.zerox.labtop.MainActivity;

/**
 * Created by Zerox on 11/7/2016.
 */

public class Laptop implements Parcelable {

    public static final Creator<Laptop> CREATOR = new Creator<Laptop>() {
        @Override
        public Laptop createFromParcel(Parcel in) {
            return new Laptop(in);
        }

        @Override
        public Laptop[] newArray(int size) {
            return new Laptop[size];
        }
    };
    public String id;
    public String title;
    public String price;
    public String image;
    public String description;


    public Laptop() {
        // Default constructor required for calls to DataSnapshot.getValue(Laptop.class)
    }

    protected Laptop(Parcel in) {
        id = in.readString();
        title = in.readString();
        price = in.readString();
        image = in.readString();
        description = in.readString();
    }

    public Laptop(Cursor cursor) {
        this.id = String.valueOf(cursor.getInt(MainActivity.COL_Laptop_ID));
        this.title = cursor.getString(MainActivity.COL_TITLE);

        this.image = cursor.getString(MainActivity.COL_URL);
        this.description = cursor.getString(MainActivity.COL_description);
        this.price = cursor.getString(MainActivity.COL_price);

    }

    public String getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPrice() {
        return this.price;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(price);
        dest.writeString(image);
        dest.writeString(description);
    }
}
