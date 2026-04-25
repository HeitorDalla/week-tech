package com.heitor.week_tech.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.heitor.week_tech.data.model.FAQ;

import java.util.List;

@Dao
public interface FAQDao {
    @Insert
    long insert(FAQ faq);

    @Update
    void update(FAQ faq);

    @Delete
    void delete(FAQ faq);

    @Query("SELECT * FROM faqs")
    List<FAQ> getAll();
}