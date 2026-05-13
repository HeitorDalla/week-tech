package com.heitor.week_tech.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.heitor.week_tech.data.model.Projeto;

import java.util.List;

@Dao
public interface ProjetoDao {
    @Insert
    long insert(Projeto projeto);

    @Update
    void update(Projeto projeto);

    @Delete
    void delete(Projeto projeto);

    @Query("SELECT * FROM projetos")
    List<Projeto> getAll();

    @Query("SELECT * FROM projetos WHERE id = :id")
    Projeto getById(long id);
}