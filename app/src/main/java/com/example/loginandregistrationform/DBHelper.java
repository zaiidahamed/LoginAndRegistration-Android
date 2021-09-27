package com.example.loginandregistrationform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "login.db";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE TABLE Users(userId INTEGER PRIMARY KEY, username TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("DROP TABLE IF EXISTS Users");
    }

    public boolean insertData(String un, String pw){
        SQLiteDatabase myDB = this.getReadableDatabase();
        ContentValues val = new ContentValues();
        val.put("username", un);
        val.put("password", pw);

        long result = myDB.insert("Users", null, val);
        if (result == -1)
            return false;
        else
            return true;
    }

    //check username already exists
    public boolean checkUsername(String un){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM Users WHERE username = ?", new String[]{un});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //validate logins
    public boolean validateLogin(String un, String pw){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM Users WHERE username = ? AND password = ?", new String[]{un, pw});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

}
