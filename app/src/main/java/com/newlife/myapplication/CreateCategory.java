package com.newlife.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

import com.newlife.myapplication.dals.DalCategory;

public class CreateCategory extends Activity {

    private EditText editCatcode;
    private EditText editCatname;
    private Button btnInsertCat;
    private Button btnClear;


    private DalCategory category;
    private long mRowId;
    private long rowId;
    private boolean update_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        category = new DalCategory(this);

        editCatcode = (EditText)findViewById(R.id.editCatcode);
        editCatname = (EditText)findViewById(R.id.editCatname);
        final  Intent intent= getIntent();
        btnInsertCat = (Button)findViewById(R.id.buttonInsertCat);
        btnClear = (Button)findViewById(R.id.buttonClear);
        btnInsertCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("category_code", editCatcode.getText().toString());
                bundle.putString("category_name", editCatname.getText().toString());
                intent.putExtra("DATA_CATEGORY", bundle);
                //rowId = category.createCategory(editCatcode.getText().toString(),editCatname.getText().toString());
                setResult(MainActivity.SEND_DATA_FROM_CATEGORY_ACTIVITY, intent);
                CreateCategory.this.finish();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCatcode.setText("");
                editCatname.setText("");
            }
        });
        Bundle bundle= intent.getBundleExtra("DATA");
        if(bundle!=null && bundle.getInt("KEY")==1)
        {
            String f2=bundle.getString("category_code");
            String f3=bundle.getString("category_name");
            System.out.println(f2);
            editCatcode.setText(f2);
            editCatname.setText(f3);
            btnInsertCat.setText("Update");
            this.setTitle("View Detail");
        }
    }
}
