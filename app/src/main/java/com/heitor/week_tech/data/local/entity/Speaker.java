package com.heitor.week_tech.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "speakers")
public class Speaker {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String bio;
    private String company;

    public Speaker(String name, String bio, String company) {
        this.name = name;
        this.bio = bio;
        this.company = company;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
}

