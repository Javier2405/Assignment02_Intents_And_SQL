package com.example.intentsandsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class Register extends AppCompatActivity {

    Toast notification;
    DataManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        data = new DataManager(getApplicationContext());

        TextView nickname = findViewById(R.id.nickname);
        TextView password = findViewById(R.id.password);
        TextView confirmpassword = findViewById(R.id.confirmpassword);
        TextView name = findViewById(R.id.name);
        TextView lastname = findViewById(R.id.lastname);
        TextView dateofbirth = findViewById(R.id.id);
        TextView role = findViewById(R.id.role);
        TextView semester = findViewById(R.id.semester);

        Button button2 = findViewById(R.id.reg);

        button2.setOnClickListener((v)->{

            Log.e("debug", "password: "+password.getText().toString()+" confirm: "+confirmpassword.getText().toString());

            if(confirmpassword.getText().toString().equals(password.getText().toString())) {

                if(!data.checkUser(nickname.getText().toString())) {

                    String[] info = {name.getText().toString(), lastname.getText().toString(), nickname.getText().toString(), password.getText().toString(), dateofbirth.getText().toString(), role.getText().toString()};
                    data.createUser(info, Integer.parseInt(semester.getText().toString()));

                    this.notification = Toast.makeText(getApplicationContext(),
                            "El usuario fue agregado con exito", Toast.LENGTH_LONG);

                    Intent changeActivity = new Intent(this, MainActivity.class);
                    startActivity(changeActivity);
                }else{
                    this.notification = Toast.makeText(getApplicationContext(),
                            "Este usuario ya fue registrado", Toast.LENGTH_LONG);
                }
            }else{
                this.notification = Toast.makeText(getApplicationContext(),
                        "La contraseña no coincide", Toast.LENGTH_LONG);
            }
            this.notification.show();

        });

    }
}
