package com.heitor.week_tech.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.heitor.week_tech.data.model.Palestrante;

import java.util.List;

@Dao
public interface PalestranteDao {
    @Insert
    long insert(Palestrante palestrante);

    @Update
    void update(Palestrante palestrante);

    @Delete
    void delete(Palestrante palestrante);

    @Query("SELECT * FROM palestrantes")
    List<Palestrante> getAll();

    @Query("SELECT * FROM palestrantes WHERE aprovado = 1")
    List<Palestrante> getAprovados();

    @Query("SELECT * FROM palestrantes WHERE id = :id")
    Palestrante getById(long id);
}