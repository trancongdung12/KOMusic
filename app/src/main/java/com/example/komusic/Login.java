package com.example.komusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import com.example.komusic.Navigation.HandleNavigation;

public class Login extends AppCompatActivity {

    DB helper;
    EditText txtEmail;
    EditText txtPassword;
    Button btnMoveToSignUp;
    Account acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = (Button) findViewById(R.id.btnLogin);
        btnMoveToSignUp = (Button) findViewById(R.id.btnSignup3);
        txtEmail =  (EditText) findViewById(R.id.txtUsername);
        txtPassword =(EditText) findViewById(R.id.txtPass);
        helper = new DB(getApplicationContext());

        btnMoveToSignUp.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent moveScreen = new Intent(Login.this, Register.class);
                startActivityForResult(moveScreen, 0);
            }
        });
        login.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent homeScreen = new Intent(Login.this, HandleNavigation.class);
                startActivity(homeScreen);
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                if(password.trim().length() == 0 || email.trim().length() == 0){
                    Toast.makeText(getApplicationContext(), "Input all fields, please!", Toast.LENGTH_SHORT).show();
                }else if(!isValidEmailId(email.trim())){
                    Toast.makeText(getApplicationContext(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
                }else if(helper.loginAccount(email, password)==-1){
                    Toast.makeText(getApplicationContext(), "Wrong your email or password!",
                            Toast.LENGTH_LONG).show();
                }else{
                    acc = new Account(helper.loginAccount(email, password));
                    startActivityForResult(homeScreen, 0);
                }
            }
        });
    }
    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}