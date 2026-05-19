package com.heitor.week_tech.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.heitor.week_tech.data.local.entity.Project;
import java.util.List;

/**
 * DAO responsável pelas operações de banco da tabela de projetos.
 */
@Dao
public interface ProjectDao {
    // Insere um novo projeto no banco local.
    @Insert
    void insert(Project project);

    // Lista os projetos ordenando por horário e depois por título.
    @Query("SELECT * FROM projects ORDER BY horario ASC, title ASC")
    List<Project> getAllProjects();
}
