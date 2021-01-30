package com.example.komusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = (Button) findViewById(R.id.btnLogin);
        Intent homeScreen = new Intent(Login.this, MainActivity.class);
        login.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivityForResult(homeScreen, 0);
            }
        });
    }
}