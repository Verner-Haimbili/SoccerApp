package com.example.soccerapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "CREATE TABLE USERS(username TEXT, email TEXT, password TEXT)";
        db.execSQL(qry1);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register(String username, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        SQLiteDatabase database = getWritableDatabase();

        database.insert("USERS", null, cv);
        database.close();
    }

    public int login(String username, String password){
        int result = 0;
        String stringArray[] = new String[2];
        stringArray[0] = username;
        stringArray[1] = password;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM USERS WHERE username=? AND password=?", stringArray);
        if(c.moveToFirst()) {
            result = 1;
        }
        return result;
    }
}

