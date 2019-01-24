package com.newlife.myapplication;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ListView;
import com.newlife.myapplication.dals.DalBook;
import com.newlife.myapplication.dals.DalCategory;
import com.newlife.myapplication.models.Book;
import com.newlife.myapplication.models.Category;

import java.util.ArrayList;
import java.util.List;

public class InsertBook extends AppCompatActivity {

    DalBook dalBook;
    DalCategory dalCategory;
    List<Book> bookList;
    List<Category> categoryList;
    Book book;
    Category category;
    BookAdapter bookAdapter;
    CategoryAdapter categoryAdapter;
    long bookID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_book);
        Spinner spinner = (Spinner)findViewById(R.id.spinner1);
        categoryList = new ArrayList<Category>();
        Category cat1 = new Category();
        cat1.set_id("_");
        cat1.setCategory_code("Show All");
        cat1.setCategory_name("_");
        categoryList.add(cat1);
        dalCategory = new DalCategory(this);
        if(dalCategory!=null)
        {
            Cursor cursor = dalCategory.readAllCategory();
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                Category cat2 = new Category();
                cat2.set_id(cursor.getInt(0));
                cat2.setCategory_code(cursor.getString(1));
                cat2.setCategory_name(cursor.getString(2));
                categoryList.add(cat2);
                cursor.moveToNext();
            }
            cursor.close();
        }
        categoryAdapter = new CategoryAdapter(InsertBook.this, R.layout.listview_show_data, categoryList);
        categoryAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(categoryAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    category=null;
                    loadAllListBook();
                }
                else
                {
                    category = categoryList.get(position);
                    loadBookByCategory(category.get_id().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                category=null;
            }
        });

        Button btnInsertBook = (Button)findViewById(R.id.buttonInsertBook);
        btnInsertBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(category == null)
                {
                    {
                        Toast.makeText(InsertBook.this, "Please choose a category to insert", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                EditText txtTitle=(EditText) findViewById(R.id.editTextTitle);
                String name = txtTitle.getText().toString();
                System.out.println(name);
                int test = Integer.parseInt(category.get_id().toString());
                System.out.println(test);
                dalBook = new DalBook(InsertBook.this);
                bookID = dalBook.createBook(txtTitle.getText().toString(),Integer.parseInt(category.get_id().toString()));

                if(bookID>0)
                {
                    Toast.makeText(InsertBook.this, "Insert Book OK", Toast.LENGTH_LONG).show();
                    loadBookByCategory(category.get_id().toString());
                }
                else
                {
                    Toast.makeText(InsertBook.this, "Insert Book Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void loadAllListBook()
    {
        bookList = new ArrayList<Book>();
        dalBook = new DalBook(InsertBook.this);
        Cursor cur= dalBook.readAllBook();
        Book header = new Book();
        header.set_id("STT");
        header.setBook_name("Ten sach");
        header.setCatID("Ma danh muc");
        bookList.add(header);
        cur.moveToFirst();
        while(cur.isAfterLast()==false)
        {
            Book b = new Book();
            b.set_id(cur.getInt(0));
            b.setBook_name(cur.getString(1));
            b.setCatID(cur.getString(2));
            bookList.add(b);
            cur.moveToNext();
        }
        cur.close();
        bookAdapter = new BookAdapter(InsertBook.this, R.layout.listview_show_data, bookList);
        ListView lv=(ListView) findViewById(R.id.listViewBook);
        lv.setAdapter(bookAdapter);
    }

    public void loadBookByCategory(String catID)
    {
        bookList = new ArrayList<Book>();
        dalBook = new DalBook(InsertBook.this);
        Cursor cur= dalBook.getBookByCategory(Integer.parseInt(catID));
        Book header = new Book();
        header.set_id("STT");
        header.setBook_name("Ten sach");
        header.setCatID("Ma danh muc");
        bookList.add(header);
        cur.moveToFirst();
        while(cur.isAfterLast()==false)
        {
            Book b = new Book();
            b.set_id(cur.getInt(0));
            b.setBook_name(cur.getString(1));
            b.setCatID(cur.getString(2));
            bookList.add(b);
            cur.moveToNext();
        }
        cur.close();
        bookAdapter = new BookAdapter(InsertBook.this, R.layout.listview_show_data, bookList);
        ListView lv=(ListView) findViewById(R.id.listViewBook);
        //bookAdapter.notifyDataSetChanged();
        lv.setAdapter(bookAdapter);
    }

}
