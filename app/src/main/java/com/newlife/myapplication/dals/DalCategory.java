package com.newlife.myapplication.dals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.newlife.myapplication.DBHelper;
import com.newlife.myapplication.models.Category;

import java.util.ArrayList;
import java.util.List;

public class DalCategory {
    SQLiteDatabase db;
    Context context;
    DBHelper dbHelper;

    private static final String DATABASE_TABLE = "category";

    public DalCategory(Context context)
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
    public long createCategory(String category_code, String category_name)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("category_code",category_code);
        contentValues.put("category_name",category_name);
        return db.insert(DATABASE_TABLE,null, contentValues);
    }

    //read all category
    public Cursor readAllCategory()
    {
        String selectQuery = "Select * from " + DATABASE_TABLE;
        Cursor cursor =  db.rawQuery(selectQuery,null);
        return cursor;
    }


    public Cursor getAllCategory()
    {
        String selectQuery = "Select * from " + DATABASE_TABLE;
        return db.rawQuery(selectQuery,null);
    }
    public int getIdCategory(String cat_id)
    {
        int result = 0;
        String selectQuery = "Select _id from " + DATABASE_TABLE + " where _id = " + "'" + cat_id + "'";
        Cursor cursor =  db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            result = cursor.getInt(0);
            cursor.moveToNext();
        }
        return result;
}


    //update category
    public boolean updateCategory(String catId, String category_code, String category_name)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("category_code",category_code);
        contentValues.put("category_name",category_name);
        return db.update(DATABASE_TABLE,contentValues,"_id" + "=" + catId, null) >0;
    }

    //delete category
    public  boolean deleteCategory(long catId)
    {
        return db.delete(DATABASE_TABLE,"_id" + "=" + catId, null) >0;
    }


    public boolean delete()
    {
        return db.delete(DATABASE_TABLE,null,null) >0;
    }
}
