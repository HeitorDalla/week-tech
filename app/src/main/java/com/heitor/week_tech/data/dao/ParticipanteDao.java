package com.heitor.week_tech.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.heitor.week_tech.data.model.Participante;

import java.util.List;

@Dao
public interface ParticipanteDao {
    @Insert
    long insert(Participante participante);

    @Update
    void update(Participante participante);

    @Delete
    void delete(Participante participante);

    @Query("SELECT * FROM participantes")
    List<Participante> getAll();

    @Query("SELECT * FROM participantes WHERE id = :id")
    Participante getById(long id);

    @Query("SELECT * FROM participantes WHERE ra = :ra")
    Participante getByRa(String ra);
}