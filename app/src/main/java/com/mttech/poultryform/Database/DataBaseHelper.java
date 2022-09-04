package com.mttech.poultryform.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DataBase_Name = "poultry_form";
//    private static final String Breader = "breader";
//    private static final String Flocks = "flocks";
//    private static final String Eggs = "eggs";
//    private static final String Medicine = "medicine";


    public DataBaseHelper(Context context){
        super(context,DataBase_Name,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create DataBase Table


        String breader = " CREATE TABLE Breader (id INTEGER PRIMARY KEY, name TEXT, number INTEGER)";
        String flocks = " CREATE TABLE Flocks (id INTEGER PRIMARY KEY, name TEXT, cage_no INTEGER, purchase_Date STRING, hatch_date TEXT, clean_date TEXT, vacc_date TEXT)";
        String eggs = " CREATE TABLE Eggs (id INTEGER PRIMARY KEY, date TEXT, egg_no INTEGER, condition TEXT, id_breader INTEGER )";
        String medicine = " CREATE TABLE Medicine (id INTEGER PRIMARY KEY, name TEXT, formula TEXT, quantity INTEGER,level INTEGER)";
        String flock_medicine =  " CREATE TABLE Flock_Med (id INTEGER PRIMARY KEY, quantity TEXT, id_flocks INTEGER, id_medicine INTEGER)";

        sqLiteDatabase.execSQL(breader);
        sqLiteDatabase.execSQL(flocks);
        sqLiteDatabase.execSQL(eggs);
        sqLiteDatabase.execSQL(medicine);
        sqLiteDatabase.execSQL(flock_medicine);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
           //Drop Existing Table
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Breader");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Flocks");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Eggs");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Medicine");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Flock_Med");
        onCreate(sqLiteDatabase);

    }

    //Create Insert Method
    public boolean insert_breader(String name, int number){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("number",number);
      long result =  sqLiteDatabase.insert("Breader",null,values);
        return result != -1;


    }
    public  boolean insert_floks(String name,int cage_no , String purchase_date, String hatch_date, String clean_date, String vacc_date){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("cage_no",cage_no);
        values.put("purchase_Date",purchase_date);
        values.put("hatch_date",hatch_date);
        values.put("clean_date",clean_date);
        values.put("vacc_date",vacc_date);
        long result =  sqLiteDatabase.insert("Flocks",null,values);
        return result != -1;
    }


    public  boolean insert_eggs( String date , int egg_no , String conditions , int breader_id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date",date);
        values.put("egg_no",egg_no);
        values.put("condition",conditions);
        values.put("id_breader", breader_id);
        long result =  sqLiteDatabase.insert("Eggs",null,values);
        return result != -1;

    }
     public  boolean insert_medicine(String name, String formula , int quantity, int level ){
         SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put("name",name);
         values.put("formula",formula);
         values.put("quantity",quantity);
         values.put("level", level);
         long result =  sqLiteDatabase.insert("Medicine",null,values);
         return result != -1;

     }

     public  boolean insert_flocksMedcine( String quantity , int flocks_id, int medicine_id ){
         SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put("quantity",quantity);
         values.put("id_flocks",flocks_id);
         values.put("id_medicine",medicine_id);
         long result =  sqLiteDatabase.insert("Flock_Med",null,values);
         return result != -1;


     }


    public Cursor getAllBreader() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Breader",null);
        return cursor;
    }
    public Cursor getAllFlocks() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Flocks",null);
        return cursor;
    }

}
