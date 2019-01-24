package com.newlife.myapplication.models;

public class Category {
    Object _id;
    Object category_code;
    Object category_name;

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public Object getCategory_code() {
        return category_code;
    }

    public void setCategory_code(Object category_code) {
        this.category_code = category_code;
    }

    public Object getCategory_name() {
        return category_name;
    }

    public void setCategory_name(Object category_name) {
        this.category_name = category_name;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this._id +" - " +this.category_code +" - "+this.category_name;
    }
}
