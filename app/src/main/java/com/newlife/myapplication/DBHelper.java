package com.newlife.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "managebook";
    public static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE_CATEGORY =
            "create table category (_id integer primary key autoincrement, "
                    + "category_code text not null, category_name text not null);";
    private static final String DATABASE_CREATE_BOOK =
            "create table book (_id integer primary key autoincrement, "
                    + "book_name text not null, catId integer not null);";

    private Context mContext;
    public static final String TAG = "DBHelper";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_CATEGORY);
        db.execSQL(DATABASE_CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("drop table if exists category");
        db.execSQL("drop table if exists book");
        onCreate(db);
    }

    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

}
