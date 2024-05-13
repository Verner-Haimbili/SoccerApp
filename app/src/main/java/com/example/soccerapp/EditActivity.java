package com.example.soccerapp;

import androidx.appcompat.app.AppCompatActivity;

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

public class EditActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4, ed5, ed6;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ed1 = findViewById(R.id.id);
        ed2 = findViewById(R.id.name);
        ed3 = findViewById(R.id.club);
        ed4 = findViewById(R.id.age);
        ed5 = findViewById(R.id.dob);
        ed6 = findViewById(R.id.experience);

        b1 = findViewById(R.id.edit);
        b2 = findViewById(R.id.delete);
        b3 = findViewById(R.id.back);

        Intent i = getIntent();

        String t1 = i.getStringExtra("id");
        String t2 = i.getStringExtra("name");
        String t3 = i.getStringExtra("club");
        String t4 = i.getStringExtra("age");
        String t5 = i.getStringExtra("dob");
        String t6 = i.getStringExtra("experience");

        ed1.setText(t1);
        ed2.setText(t2);
        ed3.setText(t3);
        ed4.setText(t4);
        ed5.setText(t5);
        ed6.setText(t6);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ViewActivity.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });
    }

    public void delete() {
        try {
            String id = ed1.getText().toString().trim();

            // Check if ID is empty
            if (id.isEmpty()) {
                Toast.makeText(this, "Please enter an ID", Toast.LENGTH_LONG).show();
                return; // Return without attempting deletion
            }

            SQLiteDatabase db = openOrCreateDatabase("SqliteDb", Context.MODE_PRIVATE, null);

            // Check if record with the specified ID exists
            Cursor cursor = db.rawQuery("SELECT id FROM records WHERE id = ?", new String[]{id});
            if (cursor != null && cursor.moveToFirst()) {
                // Record with specified ID exists, proceed with deletion
                String sql = "DELETE FROM records WHERE id = ?";
                SQLiteStatement statement = db.compileStatement(sql);
                statement.bindString(1, id);
                statement.execute();
                Toast.makeText(this, "Record Deleted", Toast.LENGTH_LONG).show();
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
                ed5.setText("");
                ed6.setText("");
                ed1.requestFocus();
            } else {
                // Record with specified ID does not exist
                Toast.makeText(this, "Record with ID " + id + " does not exist", Toast.LENGTH_LONG).show();
            }

            // Close the cursor
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }
    }

    public void edit() {
        try {
            String id = ed1.getText().toString().trim();
            String name = ed2.getText().toString().trim();
            String club = ed3.getText().toString().trim();
            String age = ed4.getText().toString().trim();
            String dob = ed5.getText().toString().trim();
            String experience = ed6.getText().toString().trim();

            // Create a new Player instance
            Player player = new Player(id, name, age, dob, club, experience);

            SQLiteDatabase db = openOrCreateDatabase("SqliteDb", Context.MODE_PRIVATE, null);

            String sql = "UPDATE records SET name = ?, club = ?, age = ?, dob = ?, experience = ? WHERE id = ?";

            SQLiteStatement statement = db.compileStatement(sql);


            statement.bindString(1, player.getName());
            statement.bindString(2, player.getClub());
            statement.bindString(3, player.getAge());
            statement.bindString(4, player.getDob());
            statement.bindString(5, player.getExperience());
            statement.bindString(6, player.getId());
            statement.execute();
            Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show();

            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");
            ed1.requestFocus();
        } catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }
    }

}
