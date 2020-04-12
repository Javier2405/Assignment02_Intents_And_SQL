package com.example.intentsandsqlite;

import android.content.Context;
import android.database.Cursor;

public class DataManager {

    public DBHelper database;
    public Context context;

    public DataManager(Context context){
        this.context = context;
        this.database = new DBHelper(this.context);
    }

    public void createUser(String[] info, int semester){
        User userAux = new User(info,semester);
        database.insertUser(userAux);
    }

    public boolean checkUser(String nickname, String password){
        return database.authentification(nickname, password);
    }

    public boolean checkUser(String nickname){
        return database.authentification(nickname);
    }

    public Cursor getData(String nickname){
        return database.getUserInformation(nickname);
    }
}
