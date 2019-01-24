package com.newlife.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.content.ContentValues;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import com.newlife.myapplication.dals.DalCategory;

public class MainActivity extends AppCompatActivity {

    Button btnInsertCategory;
    Button btnShowCategoryList;
    Button btnInsertBook;
    public static final int OPEN_CATEGORY_DIALOG=1;
    public static final int SEND_DATA_FROM_CATEGORY_ACTIVITY=2;
    //SQLiteDatabase database=null;
    private DalCategory category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsertCategory = (Button)findViewById(R.id.btnInsertCategory);
        btnInsertCategory.setOnClickListener(new MyEvent());
        btnShowCategoryList = (Button)findViewById(R.id.buttonShowCategoryList);
        btnShowCategoryList.setOnClickListener(new MyEvent());
        btnInsertBook = (Button)findViewById(R.id.buttonInsertBook);
        btnInsertBook.setOnClickListener(new MyEvent());
    }

    public void showCategoryList()
    {
        Intent intent=new Intent(MainActivity.this, ViewCategory.class);
        startActivity(intent);
    }

    public void showInsertCategoryDialog()
    {
        Intent intent=new Intent(MainActivity.this, CreateCategory.class);
        startActivityForResult(intent, OPEN_CATEGORY_DIALOG);
    }

    private class MyEvent implements OnClickListener
    {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btnInsertCategory)
            {
                showInsertCategoryDialog();
            }
            else if(v.getId()==R.id.buttonShowCategoryList)
            {
                showCategoryList();
            }

            else if(v.getId()==R.id.buttonInsertBook)
            {
                Intent intent=new Intent(MainActivity.this, InsertBook.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==SEND_DATA_FROM_CATEGORY_ACTIVITY)
        {
            Bundle bundle= data.getBundleExtra("DATA_CATEGORY");
            String cat_code=bundle.getString("category_code");
            String cat_name=bundle.getString("category_name");
            System.out.println(cat_name);
            ContentValues content=new ContentValues();
            content.put("category_code", cat_code);
            content.put("category_name", cat_name);
            category = new DalCategory(this);
            if(category!=null)
            {
                long category_id= category.createCategory(cat_code,cat_name);
                System.out.println(category_id);
                if(category_id==-1)
                {
                    Toast.makeText(MainActivity.this,category_id+" - "+ cat_code +" - "+cat_name +" ==> insert error!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, category_id+" - "+cat_code +" - "+cat_name +" ==>insert OK!", Toast.LENGTH_LONG).show();
                }
            }

        }
    }
}
