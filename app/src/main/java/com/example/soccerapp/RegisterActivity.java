package com.example.soccerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edEmail, edPassword, edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextRegUsername);
        edPassword = findViewById(R.id.editTextRegPassword);
        edEmail = findViewById(R.id.editTextRegEmail);
        edConfirm = findViewById(R.id.editTextRegConfirmPassword);
        btn = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();

                Database database = new Database(getApplicationContext(), "SqliteDb", null, 1);

                if(username.length() == 0 ||  email.length() == 0 || confirm.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please Fill All Credentials!", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.compareTo(confirm) == 0){

                        if(isValid(password)){
                            database.register(username, email, password);
                            Toast.makeText(getApplicationContext(), "Registration Successful...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Passwords must contain at least 8 characters (letter, digit and special character!!.", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Passwords don't match!! Retry...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValid(String password){
        int f1 = 0, f2 = 0, f3 =0;

        if(password.length() < 5){
            return false;
        }else{
            for(int p = 0; p < password.length(); p++){
                if(Character.isLetter(password.charAt(p))){
                    f1 = 1;
                }
            }

            for(int r = 0; r < password.length(); r++){
                if(Character.isDigit(password.charAt(r))){
                    f2 = 1;
                }
            }

            for(int s = 0; s < password.length(); s++){
                char c = password.charAt(s);

                if(c >= 33 || c <= 46 || c  == 64){
                    f3 = 1;
                }
            }

            if(f1 == 1 && f2 == 1 && f3 == 1){
                return  true;
            }
            return false;
        }
    }
}