package com.heitor.week_tech.data.local.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "speakers")
public class Speaker {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String bio;
    private String company;
    private String email;
    private String phone;
    private String link;
    private String topic;
    private String duration;
    private String photoUrl;

    // No-arg constructor required by Room
    public Speaker() {}

    // Convenience constructor (ignored by Room)
    @Ignore
    public Speaker(String name, String bio, String company) {
        this.name = name;
        this.bio = bio;
        this.company = company;
    }

    // Full constructor for use in code
    @Ignore
    public Speaker(String name, String bio, String company, String email, String phone, String link, String topic, String duration, String photoUrl) {
        this.name = name;
        this.bio = bio;
        this.company = company;
        this.email = email;
        this.phone = phone;
        this.link = link;
        this.topic = topic;
        this.duration = duration;
        this.photoUrl = photoUrl;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
}

