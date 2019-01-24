package com.newlife.myapplication.dals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.newlife.myapplication.DBHelper;

public class DalBook {
    SQLiteDatabase db;
    Context context;
    DBHelper dbHelper;

    private static final String DATABASE_TABLE = "book";

    public DalBook(Context context)
    {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public void close()
    {
        dbHelper.close();
    }

    public boolean isOpen()
    {
        return db.isOpen();
    }

    //insert db
    public long createBook(String book_name, int catID)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("book_name",book_name);
        contentValues.put("catID",catID);
        return db.insert(DATABASE_TABLE,null, contentValues);
    }

    //read all
    public Cursor readAllBook()
    {
        String selectQuery = "Select * from " + DATABASE_TABLE;
        Cursor cursor =  db.rawQuery(selectQuery,null);
        return cursor;
    }

    public Cursor getBookByCategory(int catID)
    {
        String selectQuery = "Select * from " + DATABASE_TABLE + " where catID = " + "'" + catID + "'";
        return db.rawQuery(selectQuery,null);
    }
    public int getIdBook(String book_id)
    {
        int result = 0;
        String selectQuery = "Select _id from " + DATABASE_TABLE + " where _id = " + "'" + book_id + "'";
        Cursor cursor =  db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            result = cursor.getInt(0);
            cursor.moveToNext();
        }
        return result;
    }

    //update
    public boolean updateBook(String bookId, String book_name, int catID)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("category_code",book_name);
        contentValues.put("category_name",catID);
        return db.update(DATABASE_TABLE,contentValues,"_id" + "=" + bookId, null) >0;
    }

    //delete category
    public  boolean deleteBook(long bookId)
    {
        return db.delete(DATABASE_TABLE,"_id" + "=" + bookId, null) >0;
    }


    public boolean delete()
    {
        return db.delete(DATABASE_TABLE,null,null) >0;
    }
}
