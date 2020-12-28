package com.example.bmicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class

DatabaseHelper extends SQLiteOpenHelper {


    public static final String TABLE_NAME="person_table";
    public static final String COL_1="ID";
    public static final String COL_2="BMI_RESULT";

    private static final String Tag="DatabaseHelper";


    public DatabaseHelper(@Nullable Context context) {
        super(context,TABLE_NAME,null,1);
        // SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable=" CREATE TABLE "+ TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_2 +" TEXT) ";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean addData(String item)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,item);
        Log.d(Tag,"addData: Adding "+ item +" to "+TABLE_NAME);

        long res=db.insert(TABLE_NAME,null,contentValues);
        if(res==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query=" SELECT * FROM " + TABLE_NAME;
        Cursor data=db.rawQuery(query,null);
        return data;
    }

    public Cursor getItemId(String bmi_result)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query=" SELECT " + COL_1 + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " = '" + bmi_result + "'";
        Cursor data=db.rawQuery(query,null);
        return data;
    }

    public void deleteBmi_result(int id,String bmi_result)

    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query=" DELETE FROM " + TABLE_NAME + " WHERE " + COL_1 +" = '" + id + "'" + " AND " + COL_2 + " = '" + bmi_result + "'";
        Log.d(Tag,"deleteBmi_result: query: "+query);
        Log.d(Tag,"deleteBmi_result: Deleting "+ bmi_result + " from Database.");
        db.execSQL(query);
    }
}