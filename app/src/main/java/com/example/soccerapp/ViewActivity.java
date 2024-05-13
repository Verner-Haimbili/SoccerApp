package com.example.soccerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;
    private ListView lst1;
    private ArrayList<String> titles = new ArrayList<String>();
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        lst1 = findViewById(R.id.lst1);

        // Fetch player data from SQLite database
        List<Player> playerList = getPlayersFromDatabase();

        adapter = new CustomAdapter(this, R.layout.list_item_layout, playerList);
        lst1.setAdapter(adapter);

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Player selectedPlayer = (Player) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("id", selectedPlayer.getId());
                intent.putExtra("name", selectedPlayer.getName());
                intent.putExtra("age", selectedPlayer.getAge());
                intent.putExtra("dob", selectedPlayer.getDob());
                intent.putExtra("club", selectedPlayer.getClub());
                intent.putExtra("experience", selectedPlayer.getExperience());
                startActivity(intent);
            }
        });
    }

    // Method to fetch player data from SQLite database
    private List<Player> getPlayersFromDatabase() {
        List<Player> playerList = new ArrayList<>();
        SQLiteDatabase db = openOrCreateDatabase("SqliteDb", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM records", null);

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("id");
            int nameIndex = cursor.getColumnIndex("name");
            int clubIndex = cursor.getColumnIndex("club");
            int ageIndex = cursor.getColumnIndex("age");
            int dobIndex = cursor.getColumnIndex("dob");
            int experienceIndex = cursor.getColumnIndex("experience");

            do {
                String id = cursor.getString(idIndex);
                String name = cursor.getString(nameIndex);
                String age = cursor.getString(ageIndex);
                String dob = cursor.getString(dobIndex);
                String club = cursor.getString(clubIndex);
                String experience = cursor.getString(experienceIndex);

                Player player = new Player(id, name, age, dob, club, experience);
                playerList.add(player);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();

        return playerList;
    }

}
