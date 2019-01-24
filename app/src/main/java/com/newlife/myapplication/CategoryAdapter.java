package com.newlife.myapplication;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.content.Context;
import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.graphics.Color;

import com.newlife.myapplication.models.Category;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private Activity context;
    private int layout;
    private List<Category>list;
    public CategoryAdapter(Context context, int textViewResourceId,
                                List<Category> objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        this.context=(Activity) context;
        this.layout=textViewResourceId;
        this.list=objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater flater=context.getLayoutInflater();
        View row=flater.inflate(layout, parent,false);
        TextView txt1=(TextView) row.findViewById(R.id.textView1);
        TextView txt2=(TextView) row.findViewById(R.id.textView2);
        TextView txt3=(TextView) row.findViewById(R.id.textView3);
        Category data=list.get(position);
        txt1.setText(data.get_id()==null?"":data.get_id().toString());
        txt2.setText(data.getCategory_code()==null?"":data.getCategory_code().toString());
        txt3.setText(data.getCategory_name()==null?"":data.getCategory_name().toString());
        if(position==0)
        {
            row.setBackgroundColor(Color.RED);
        }
        return row;
    }
}
