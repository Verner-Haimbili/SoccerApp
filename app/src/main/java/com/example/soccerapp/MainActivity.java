package com.example.soccerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4, ed5;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.club);
        ed3 = findViewById(R.id.age);
        ed4 = findViewById(R.id.dob);
        ed5 = findViewById(R.id.experience);

        b1 = findViewById(R.id.save);
        b2 = findViewById(R.id.view_list);
        b3 = findViewById(R.id.news);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ViewActivity.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FindPlayersActivity.class);
                startActivity(i);
            }
        });
    }
    public void insert() {
        SQLiteDatabase db = null;
        try {
            String name = ed1.getText().toString().trim();
            String club = ed2.getText().toString().trim();
            String age = ed3.getText().toString().trim();
            String dob = ed4.getText().toString().trim();
            String experience = ed5.getText().toString().trim();

            // Check if any of the fields is empty
            if (name.isEmpty() || club.isEmpty() || age.isEmpty() || dob.isEmpty() || experience.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                return; // Return without inserting the record
            }

            db = openOrCreateDatabase("SqliteDb", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, club VARCHAR, age VARCHAR, dob VARCHAR, experience VARCHAR)");

            // Get the maximum player ID
            int playerId;
            Cursor cursor = db.rawQuery("SELECT MAX(id) FROM records", null);
            if (cursor.moveToFirst() && !cursor.isNull(0)) {
                playerId = cursor.getInt(0) + 1;
            } else {
                playerId = 100; // Start from 10 if the table is empty
            }
            cursor.close();

            // Prepare the SQL statement
            String sql = "INSERT INTO records(id, name, club, age, dob, experience) VALUES(?, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindLong(1, playerId);
            statement.bindString(2, name);
            statement.bindString(3, club);
            statement.bindString(4, age);
            statement.bindString(5, dob);
            statement.bindString(6, experience);

            // Execute the statement
            statement.execute();
            Toast.makeText(this, "Record added", Toast.LENGTH_LONG).show();

            // Clear the EditText fields
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed1.requestFocus();

        } catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        } finally {
            // Ensure that the database is properly closed
            if (db != null) {
                db.close();
            }
        }
    }



}
