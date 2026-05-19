package com.heitor.week_tech.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.heitor.week_tech.data.local.entity.Participant;
import java.util.List;

/**
 * DAO responsável pelas operações de banco da tabela de participantes.
 */
@Dao
public interface ParticipantDao {
    // Insere um novo participante no banco local.
    @Insert
    void insert(Participant participant);

    // Retorna todos os participantes ordenados alfabeticamente.
    @Query("SELECT * FROM participants ORDER BY name ASC")
    List<Participant> getAllParticipants();
}