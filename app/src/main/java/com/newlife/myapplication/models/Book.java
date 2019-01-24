package com.newlife.myapplication.models;

public class Book {
    Object _id;
    Object book_name;
    Object catID;

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public Object getBook_name() {
        return book_name;
    }

    public void setBook_name(Object book_name) {
        this.book_name = book_name;
    }

    public Object getCatID() {
        return catID;
    }

    public void setCatID(Object catID) {
        this.catID = catID;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this._id +" - " +this.book_name +" - "+this.catID;
    }
}
