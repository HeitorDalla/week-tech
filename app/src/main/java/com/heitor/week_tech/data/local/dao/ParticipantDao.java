package com.heitor.week_tech.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.heitor.week_tech.data.local.entity.Participant;
import java.util.List;

@Dao
public interface ParticipantDao {
    @Insert
    void insert(Participant participant);

    @Query("SELECT * FROM participants ORDER BY name ASC")
    List<Participant> getAllParticipants();
}