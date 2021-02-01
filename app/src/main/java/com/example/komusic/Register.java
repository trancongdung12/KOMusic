package com.example.komusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    DB helper;
    EditText txtEmail;
    EditText txtPassword;
    EditText txtLastName;
    EditText txtFirstName;
    EditText txtPhone;
    EditText txtNickName;
    EditText txtConfirm;
    Button btnMoveToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button signUp = (Button) findViewById(R.id.btnSignup);
        btnMoveToLogin = (Button) findViewById(R.id.btnMoveToLogin);
        txtEmail =  (EditText) findViewById(R.id.txtEmail);
        txtPassword =(EditText) findViewById(R.id.txtPassword);
        txtFirstName =  (EditText) findViewById(R.id.txtFName);
        txtLastName =(EditText) findViewById(R.id.txtLName);
        txtPhone =  (EditText) findViewById(R.id.txtPhone);
        txtNickName =(EditText) findViewById(R.id.txtUser);
        txtConfirm =(EditText) findViewById(R.id.txtConfirm);
        helper = new DB(getApplicationContext());

        btnMoveToLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveScreen = new Intent(Register.this, Login.class);
                startActivityForResult(moveScreen, 0);
            }
        });
        Intent homeScreen = new Intent(Register.this, MainActivity.class);
        signUp.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String firstName = txtFirstName.getText().toString();
                String lastName = txtLastName.getText().toString();
                String phone = txtPhone.getText().toString();
                String nickname = txtNickName.getText().toString();
                String confirm = txtConfirm.getText().toString();
                String regexStr = "^[+]?[0-9]{10,13}$";

                //kiem tra co rong ko
                if(password.trim().length() == 0 || firstName.trim().length() == 0
                || lastName.trim().length() == 0 || nickname.trim().length() == 0
                || phone.trim().length() == 0 || email.trim().length() == 0){
                    Toast.makeText(getApplicationContext(), "Input all data, please!", Toast.LENGTH_SHORT).show();
                // check valid phone
                }else if(phone.matches(regexStr)==false  ) {
                    Toast.makeText(Register.this,"Please enter a valid phone number",Toast.LENGTH_SHORT).show();
                    // am_checked=0;
                }
                else if(password.trim().length() <6  ) {
                    Toast.makeText(Register.this,"Password more than 6 characters, please",Toast.LENGTH_SHORT).show();
                    // am_checked=0;
                }
                else if(!password.equals(confirm)){
                    Toast.makeText(getApplicationContext(), "Check confirm password, please!", Toast.LENGTH_SHORT).show();
                }
                else if(!isValidEmailId(email.trim())){
                    Toast.makeText(getApplicationContext(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
                }else if(!helper.checkEmailExist(email)){
                   helper.insertAccount(firstName,lastName,phone,email,nickname,password);
                    Intent loginScreen = new Intent(Register.this, Login.class);
                    startActivityForResult(loginScreen, 0);
                }else{
                    Toast.makeText(getApplicationContext(), "Already exist email!",
                            Toast.LENGTH_LONG).show();
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