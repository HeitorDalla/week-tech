package com.heitor.week_tech.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.heitor.week_tech.data.local.entity.Speaker;
import java.util.List;

/**
 * DAO responsável pelas operações de banco da tabela de palestrantes.
 */
@Dao
public interface SpeakerDao {
    // Insere um novo palestrante no banco local.
    @Insert
    void insert(Speaker speaker);

    // Retorna todos os palestrantes em ordem alfabética.
    @Query("SELECT * FROM speakers ORDER BY name ASC")
    List<Speaker> getAllSpeakers();
}

