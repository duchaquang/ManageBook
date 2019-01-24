package com.newlife.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.newlife.myapplication.models.Book;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private Activity context;
    private int layout;
    private List<Book> list;
    public BookAdapter(Context context, int textViewResourceId,
                           List<Book> objects) {
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
        Book data=list.get(position);
        txt1.setText(data.get_id()==null?"":data.get_id().toString());
        txt2.setText(data.getBook_name()==null?"":data.getBook_name().toString());
        txt3.setText(data.getCatID()==null?"":data.getCatID().toString());
        if(position==0)
        {
            row.setBackgroundColor(Color.RED);
        }
        return row;
    }
}
