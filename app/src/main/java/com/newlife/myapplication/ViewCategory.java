package com.newlife.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.newlife.myapplication.dals.DalCategory;
import com.newlife.myapplication.models.Category;

public class ViewCategory extends AppCompatActivity {

    private ListView listView;
    private DalCategory category;

    private final List<Category> categoryList = new ArrayList<Category>();
    private Category cat;
    CategoryAdapter adapter;
    private boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category);

        category = new DalCategory(this);

        Button btnBack = (Button)findViewById(R.id.buttonBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewCategory.this.finish();
            }
        });
        updateUI();
    }
    public void updateUI()
    {
        category = new DalCategory(this);
        if(category!=null)
        {
            Cursor cursor = category.getAllCategory();
            startManagingCursor(cursor);
            Category header = new Category();
            header.set_id("STT");
            header.setCategory_code("Ma danh muc");
            header.setCategory_name("Ten danh muc");
            categoryList.add(header);
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                Category data = new Category();
                data.set_id(cursor.getInt(0));
                data.setCategory_code(cursor.getString(1));
                data.setCategory_name(cursor.getString(2));
                categoryList.add(data);
                cursor.moveToNext();
            }
            cursor.close();
        }
        adapter = new CategoryAdapter(ViewCategory.this,R.layout.listview_show_data, categoryList);
        listView = (ListView)findViewById(R.id.listViewShowData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ViewCategory.this,"View -->"+ categoryList.get(position).toString(), Toast.LENGTH_LONG).show();
                Intent intent=new Intent(ViewCategory.this, CreateCategory.class);
                Bundle bundle=new Bundle();
                bundle.putInt("KEY", 1);
                bundle.putString("get_id", categoryList.get(position).get_id().toString());
                bundle.putString("category_code", categoryList.get(position).getCategory_code().toString());
                bundle.putString("category_name", categoryList.get(position).getCategory_name().toString());
                intent.putExtra("DATA", bundle);
                cat = categoryList.get(position);
                startActivityForResult(intent, MainActivity.OPEN_CATEGORY_DIALOG);
            }
        });
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Category data = categoryList.get(position);
                final int pos = position;
                Toast.makeText(ViewCategory.this, "Edit-->"+data.toString(), Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new Builder(ViewCategory.this);
                builder.setTitle("Remove");
                builder.setMessage("Bạn muốn xóa danh mục ["+data.getCategory_code() +" - "+data.getCategory_name() +"] này không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       boolean result =  category.deleteCategory(pos);
                       if(result)
                       {
                           Toast.makeText(ViewCategory.this, "Remove ok", Toast.LENGTH_LONG).show();
                           categoryList.remove(pos);
                           adapter.notifyDataSetChanged();
                       }
                       else
                       {
                           Toast.makeText(ViewCategory.this, "Remove not ok", Toast.LENGTH_LONG).show();
                       }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }
                });
                builder.show();
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==MainActivity.SEND_DATA_FROM_CATEGORY_ACTIVITY)
        {
            Bundle bundle=data.getBundleExtra("DATA_CATEGORY");
            String f2=bundle.getString("category_code");
            String f3=bundle.getString("category_name");
            String f1= cat.get_id().toString();
            ContentValues values=new ContentValues();
            values.put("category_code", f2);
            values.put("category_name", f3);
            if(category!=null)
            {
                result = category.updateCategory(f1,f2,f3);
                if(result)
                {
                    Toast.makeText(ViewCategory.this, "update ok", Toast.LENGTH_LONG).show();
                    cat.setCategory_code(f2);
                    cat.setCategory_name(f3);
                    if(adapter!=null)
                        adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
