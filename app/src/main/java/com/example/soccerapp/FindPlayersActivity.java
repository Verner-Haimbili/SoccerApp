package com.example.soccerapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class FindPlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_players);

        // Find card views in the layout
        CardView cardCoaches = findViewById(R.id.cardFDCoaches);
        CardView cardGoalkeepers = findViewById(R.id.cardFDGoalkeeper);
        CardView cardMidfielders = findViewById(R.id.cardFDMidfielder);
        CardView cardDefenders = findViewById(R.id.cardFDDefender);
        CardView cardForwards = findViewById(R.id.cardFDForwards);
        CardView cardBack = findViewById(R.id.cardFDBack);

        // Set click listeners for each card

        cardCoaches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.90min.com/posts/the-25-best-managers-mens-world-football-ranked"));
                startActivity(browserIntent);
            }
        });

        cardGoalkeepers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.footballcritic.com/top-players/goalkeepers"));
                startActivity(browserIntent);
            }
        });

        cardMidfielders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.givemesport.com/ranking-best-midfielders-in-world-football/"));
                startActivity(browserIntent);
            }
        });

        cardDefenders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.footballcritic.com/top-players/defenders"));
                startActivity(browserIntent);
            }
        });

        cardForwards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.90min.com/posts/the-25-best-strikers-in-world-football-ranked"));
                startActivity(browserIntent);
            }
        });
        cardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindPlayersActivity.this, MainActivity.class));
            }
        });
    }
}
