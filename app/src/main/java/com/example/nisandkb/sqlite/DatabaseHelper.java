package com.example.nisandkb.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nisand KB on 12/6/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "Student_table";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "PHONE";
    public static final String COL_3 = "PASSWORD";
    public DatabaseHelper(Context context) {

            super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table'" + TABLE_NAME +"'( NAME VARCHAR,PHONE VARCHAR(10),PASSWORD VARCHAR)" );
        }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertdata(String name,String phone,String password){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       // int phone1 = Integer.parseInt(phone);
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,phone);
        contentValues.put(COL_3,password);
       long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(result== -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res =sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
