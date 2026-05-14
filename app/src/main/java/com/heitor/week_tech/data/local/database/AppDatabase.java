package com.heitor.week_tech.data.local.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.heitor.week_tech.data.local.dao.ParticipantDao;
import com.heitor.week_tech.data.local.entity.Participant;
import com.heitor.week_tech.data.local.dao.ProjectDao;
import com.heitor.week_tech.data.local.entity.Project;
import com.heitor.week_tech.data.local.dao.SpeakerDao;
import com.heitor.week_tech.data.local.entity.Speaker;

@Database(entities = {Participant.class, Project.class, Speaker.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract ParticipantDao participantDao();
    public abstract ProjectDao projectDao();
    public abstract SpeakerDao speakerDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "week_tech_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}