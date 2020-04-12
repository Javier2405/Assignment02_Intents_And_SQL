package com.example.intentsandsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Information extends AppCompatActivity {

    DataManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        data = new DataManager(getApplicationContext());

        TextView name = findViewById(R.id.complete_name);
        TextView semester = findViewById(R.id.semester);
        TextView nickname = findViewById(R.id.name);
        TextView role = findViewById(R.id.role);
        TextView id = findViewById(R.id.id);
        TextView dateofbirth = findViewById(R.id.dateofbirth);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String nicknameStr = bundle.getString("nickname");
        Log.e("debug", "cursor information: "+nicknameStr);

        Cursor infoUser = data.getData(nicknameStr);
        infoUser.moveToFirst();

        name.setText("Name: "+infoUser.getString(infoUser.getColumnIndex("name"))+infoUser.getString(infoUser.getColumnIndex("lastname")));
        semester.setText("Semester: "+infoUser.getString(infoUser.getColumnIndex("semester")));
        nickname.setText("Nickname: "+infoUser.getString(infoUser.getColumnIndex("nickname")));
        role.setText("Role: "+infoUser.getString(infoUser.getColumnIndex("role")));
        id.setText("Id: "+infoUser.getString(infoUser.getColumnIndex("id")));
        dateofbirth.setText("Date of birth: "+infoUser.getString(infoUser.getColumnIndex("dateofbirth")));



    }
}
