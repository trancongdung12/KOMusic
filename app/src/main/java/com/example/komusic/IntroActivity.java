package com.example.komusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Button login = (Button) findViewById(R.id.login);
        Intent loginScreen = new Intent(IntroActivity.this, Login.class);
        login.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivityForResult(loginScreen, 0);
            }
        });

        Button register = (Button) findViewById(R.id.register);
        Intent registerScreen = new Intent(IntroActivity.this, Register.class);
        register.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivityForResult(registerScreen, 0);
            }
        });
    }
}