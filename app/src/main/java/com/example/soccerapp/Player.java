package com.example.soccerapp;

public class Player {
    public String id;

    public String name;
    public String age;
    public String dob;
    public String club;
    public String experience;

    // Constructor
    public Player(String id, String name, String age, String dob, String club, String experience) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.club = club;
        this.experience = experience;
    }

    // Getter methods
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getDob() {
        return dob;
    }

    public String getClub() {
        return club;
    }

    public String getExperience() {
        return experience;
    }
}


