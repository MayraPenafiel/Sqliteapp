package com.example.sqlite_aplicacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Personas.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Personas(name TEXT primary key, lastname TEXT, contact TEXT,correo TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table  Personas");
    }
    public Boolean insertuserdata(String name,String lastname, String contact, String correo)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("lastname",lastname);
        contentValues.put("contact", contact);
        contentValues.put("correo",correo);
        long result=DB.insert("Personas", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateuserdata(String name,String lastname, String contact,String correo)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("lastname",lastname);
        contentValues.put("contact", contact);
        contentValues.put("correo",correo);
        Cursor cursor = DB.rawQuery("Select * from Personas where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Personas", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletedata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Personas where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Personas", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Personas", null);
        return cursor;
    }
}