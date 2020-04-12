package com.example.intentsandsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASENAME = "users.db";

    public static final String DB_PATH = "/data/data/com.example.users/databases";
    public static final int VERSION = 10;

    //USER TABLE
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_LASTNAME = "lastname";
    public static final String COLUMN_USER_DATE_OF_BIRTH = "dateofbirth";
    public static final String COLUMN_USER_ROLE = "role";
    public static final String COLUMN_USER_SEMESTER = "semester";
    public static final String COLUMN_USER_NICKNAME = "nickname";
    public static final String COLUMN_USER_PASSWORD = "password";



    public DBHelper(Context context) {
        super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table user
        db.execSQL("create table "+TABLE_USERS+"("
                +COLUMN_USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLUMN_USER_NAME +" TEXT,"
                +COLUMN_USER_LASTNAME +" TEXT,"
                +COLUMN_USER_DATE_OF_BIRTH +" TEXT,"
                +COLUMN_USER_ROLE +" TEXT,"
                +COLUMN_USER_SEMESTER +" INTEGER,"
                +COLUMN_USER_NICKNAME +" TEXT,"
                +COLUMN_USER_PASSWORD+" TEXT )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public Cursor getUserInformation(String nickname){
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_NICKNAME + " = ?";
        String[] selectionArgs = { nickname };

        Cursor cursor = db.query(
                TABLE_USERS,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null              // The sort order
        );

        cursor.moveToFirst();
        Log.e("debug", "getUserInformation: "+cursor.getString(cursor.getColumnIndex("nickname")));

        return cursor;

    }

    public void insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Log.e("hola", "insertUser: "+user.getName());

        values.put("name", user.getName());
        values.put("lastname", user.getLastname());
        values.put("dateofbirth", user.getDateofbirth());
        values.put("role", user.getRole());
        values.put("semester", user.getSemester());
        values.put("nickname", user.getNickname());
        values.put("password", user.getPassword());
        Log.e("hola", "insertUser2: "+values.getAsString("name"));

        db.insert("users", null, values);

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(
                "users",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null              // The sort order
        );

        Log.d("debug", "insertUser3: " + cursor.moveToFirst()+""+cursor.getColumnName(cursor.getColumnIndex("name")) + " "+ cursor.getString(cursor.getColumnIndex("name")));

        //Log.d("debug", "insertUser3: " + cursor.moveToFirst()+" "+ cursor.moveToNext() +" "+cursor.getColumnName(cursor.getColumnIndex("lastname")) + " "+ cursor.getString(cursor.getColumnIndex("lastname")));
    }


    public boolean authentification(String nickname,String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "nickname = ? and password = ?";
        //Log.d("debug", "authentification2: " + selection);
        String[] selectionArgs = {nickname, password};
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE nickname=? and password=?", new String[]{nickname, password});

        if (cursor.moveToNext()) {
            Log.d("debug", "authentification check " + cursor.moveToFirst() + " " + cursor.getColumnName(cursor.getColumnIndex("name")) + " " + cursor.getString(cursor.getColumnIndex("name")));
            Log.d("debug", "authentification check " + cursor.getColumnName(cursor.getColumnIndex("nickname")) + " " + cursor.getString(cursor.getColumnIndex("nickname")));
            Log.d("debug", "authentification check " + cursor.getColumnName(cursor.getColumnIndex("password")) + " " + cursor.getString(cursor.getColumnIndex("password")));
            //Log.d("debug", "authentification4 " + cursor.moveToFirst()+""+cursor.getColumnName(cursor.getColumnIndex("name")) + " "+ cursor.getString(cursor.getColumnIndex("name")));
            return true;
        } else {
            return false;
        }
    }

    public boolean authentification(String nickname) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "nickname = ? ";
        //Log.d("debug", "authentification2: " + selection);
        String[] selectionArgs = {nickname};
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE nickname=?", new String[]{nickname});

        if (cursor.moveToNext()) {
            Log.d("debug", "authentification check " + cursor.moveToFirst() + " " + cursor.getColumnName(cursor.getColumnIndex("name")) + " " + cursor.getString(cursor.getColumnIndex("name")));
            Log.d("debug", "authentification check " + cursor.getColumnName(cursor.getColumnIndex("nickname")) + " " + cursor.getString(cursor.getColumnIndex("nickname")));
            Log.d("debug", "authentification check " + cursor.getColumnName(cursor.getColumnIndex("password")) + " " + cursor.getString(cursor.getColumnIndex("password")));
            //Log.d("debug", "authentification4 " + cursor.moveToFirst()+""+cursor.getColumnName(cursor.getColumnIndex("name")) + " "+ cursor.getString(cursor.getColumnIndex("name")));
            return true;
        } else {
            return false;
        }
    }




}
