package com.heitor.week_tech.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.heitor.week_tech.data.model.Presenca;

import java.util.List;

@Dao
public interface PresencaDao {
    @Insert
    long insert(Presenca presenca);

    @Update
    void update(Presenca presenca);

    @Delete
    void delete(Presenca presenca);

    @Query("SELECT * FROM presencas")
    List<Presenca> getAll();

    @Query("SELECT * FROM presencas WHERE participanteId = :participanteId")
    List<Presenca> getByParticipante(long participanteId);

    @Query("SELECT * FROM presencas WHERE palestraId = :palestraId")
    List<Presenca> getByPalestra(long palestraId);
}