package com.heitor.week_tech.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.heitor.week_tech.data.local.entity.Project;
import java.util.List;

@Dao
public interface ProjectDao {
    @Insert
    void insert(Project project);

    @Query("SELECT * FROM projects ORDER BY title ASC")
    List<Project> getAllProjects();
}

