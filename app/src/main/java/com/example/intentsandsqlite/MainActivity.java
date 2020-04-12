package com.example.intentsandsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toast notification;
    DataManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new DataManager(getApplicationContext());

        Button button1 = findViewById(R.id.login);
        Button button2 = findViewById(R.id.register);

        TextView nickname = findViewById(R.id.editText);
        TextView password = findViewById(R.id.editText2);

        button1.setOnClickListener((v)->{
            boolean user = data.checkUser(nickname.getText().toString(), password.getText().toString());
            if(user){
                Intent changeActivity = new Intent(this, Information.class);
                changeActivity.putExtra("nickname", nickname.getText().toString());
                startActivity(changeActivity);
                this.notification = Toast.makeText(getApplicationContext(),
                        "Ingresando como "+nickname.getText().toString(), Toast.LENGTH_LONG);

            }else {
                this.notification = Toast.makeText(getApplicationContext(),
                        "El usuario no existe o no esta registrado", Toast.LENGTH_LONG);
            }

            this.notification.show();
        });

        button2.setOnClickListener((v)->{
            Intent changeActivity = new Intent(this, Register.class);
            startActivity(changeActivity);

        });

    }
}
