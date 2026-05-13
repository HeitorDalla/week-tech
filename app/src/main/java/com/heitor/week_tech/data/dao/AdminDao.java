package com.heitor.week_tech.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.heitor.week_tech.data.model.Admin;

@Dao
public interface AdminDao {
    @Insert
    long insert(Admin admin);

    @Query("SELECT * FROM admins WHERE username = :username AND password = :password LIMIT 1")
    Admin login(String username, String password);
}