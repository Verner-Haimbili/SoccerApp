package com.example.soccerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.soccerapp.MainActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername , edPassword;
    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textNewUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();

                if(username.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please Fill All Credentials!", Toast.LENGTH_SHORT).show();
                }else{
                        Toast.makeText(getApplicationContext(), "LOGIN SUCCESS", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);

                        //Save our dta with key data
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}