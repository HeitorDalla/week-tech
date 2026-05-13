package com.heitor.week_tech.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.heitor.week_tech.data.model.InformacaoEvento;

@Dao
public interface InformacaoEventoDao {
    @Insert
    long insert(InformacaoEvento info);

    @Update
    void update(InformacaoEvento info);

    @Query("SELECT * FROM informacoes_evento LIMIT 1")
    InformacaoEvento getInfo();
}