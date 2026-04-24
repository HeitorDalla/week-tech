package com.heitor.week_tech.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.heitor.week_tech.data.model.Patrocinador;

import java.util.List;

@Dao
public interface PatrocinadorDao {
    @Insert
    long insert(Patrocinador patrocinador);

    @Query("SELECT * FROM patrocinadores")
    List<Patrocinador> getAll();

    @Delete
    void delete(Patrocinador patrocinador);
}