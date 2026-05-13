package com.heitor.week_tech.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.heitor.week_tech.data.local.entity.Speaker;
import java.util.List;

@Dao
public interface SpeakerDao {
    @Insert
    void insert(Speaker speaker);

    @Query("SELECT * FROM speakers ORDER BY name ASC")
    List<Speaker> getAllSpeakers();
}

