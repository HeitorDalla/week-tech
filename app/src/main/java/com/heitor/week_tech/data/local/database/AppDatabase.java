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

/**
 * Banco de dados local do aplicativo usando Room.
 * Centraliza os DAOs e garante uma única instância compartilhada.
 */
@Database(entities = {Participant.class, Project.class, Speaker.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    // Instância única do banco para evitar múltiplas aberturas desnecessárias.
    private static AppDatabase instance;

    // DAOs que expõem as operações de leitura e escrita de cada entidade.
    public abstract ParticipantDao participantDao();
    public abstract ProjectDao projectDao();
    public abstract SpeakerDao speakerDao();

    // Retorna a instância única do banco, criando-a quando necessário.
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