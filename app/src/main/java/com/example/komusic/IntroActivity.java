package com.example.komusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class IntroActivity extends AppCompatActivity {
    DB helperAcc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Button login = (Button) findViewById(R.id.login);

        helperAcc = new DB(getApplicationContext());

        if(getListAccount().size()<=0){
            renderAccount();
        }

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
    private void renderAccount (){
        helperAcc.insertAccount ("hung", "nguyen", "0987065054",
                "hungnguyen@gmail.com", "hung","hunghung");
        helperAcc.insertAccount ("long", "nguyen", "0987065054",
                "hungnguyen1@gmail.com", "long","longlong");
    }
    private List<Account> getListAccount() {
        List<Account> list = helperAcc.getAll();
        return list;
    }


}