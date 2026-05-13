package com.heitor.week_tech.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.heitor.week_tech.data.model.Palestra;

import java.util.List;

@Dao
public interface PalestraDao {
    @Insert
    long insert(Palestra palestra);

    @Update
    void update(Palestra palestra);

    @Delete
    void delete(Palestra palestra);

    @Query("SELECT * FROM palestras")
    List<Palestra> getAll();

    @Query("SELECT * FROM palestras WHERE id = :id")
    Palestra getById(long id);
}