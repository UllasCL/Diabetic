package com.artelus.swasthya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Serializable;



class Patient_Details extends SQLiteOpenHelper implements Serializable
{
    public static final String DATABASE_NAME = "patient_details.db";
    public static final String TABLE_NAME = "patient_table";
    public static final String COL_1 = "pName";
    public static final String COL_2 = "pAge";
    public static final String COL_3 = "pSugar";
    public static final String COL_4 = "pDoctor";



    public Patient_Details(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME +" (pName TEXT primary key,pAge INTEGER,pSugar INTEGER,pDoctor TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String pname,String page,String psugar,String pdoctor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,pname);
        contentValues.put(COL_2,page);
        contentValues.put(COL_3,psugar);
        contentValues.put(COL_4,pdoctor);

        long result = db.insert(TABLE_NAME,null,contentValues);
        Log.i("details",pname+page+psugar+pdoctor);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        db.update(TABLE_NAME, contentValues,COL_1+ "=?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_1+"=?",new String[] {id});
    }

}



