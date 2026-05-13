package com.heitor.week_tech.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.heitor.week_tech.data.dao.AdminDao;
import com.heitor.week_tech.data.dao.FAQDao;
import com.heitor.week_tech.data.dao.InformacaoEventoDao;
import com.heitor.week_tech.data.dao.PalestraDao;
import com.heitor.week_tech.data.dao.PalestranteDao;
import com.heitor.week_tech.data.dao.ParticipanteDao;
import com.heitor.week_tech.data.dao.PatrocinadorDao;
import com.heitor.week_tech.data.dao.PresencaDao;
import com.heitor.week_tech.data.dao.ProjetoDao;
import com.heitor.week_tech.data.model.Admin;
import com.heitor.week_tech.data.model.FAQ;
import com.heitor.week_tech.data.model.InformacaoEvento;
import com.heitor.week_tech.data.model.Palestra;
import com.heitor.week_tech.data.model.Palestrante;
import com.heitor.week_tech.data.model.Participante;
import com.heitor.week_tech.data.model.Patrocinador;
import com.heitor.week_tech.data.model.Presenca;
import com.heitor.week_tech.data.model.Projeto;

@Database(entities = {
        Participante.class,
        Palestrante.class,
        Palestra.class,
        Presenca.class,
        Projeto.class,
        Patrocinador.class,
        FAQ.class,
        Admin.class,
        InformacaoEvento.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;

    public abstract ParticipanteDao participanteDao();
    public abstract PalestranteDao palestranteDao();
    public abstract PalestraDao palestraDao();
    public abstract ProjetoDao projetoDao();
    public abstract PresencaDao presencaDao();
    public abstract PatrocinadorDao patrocinadorDao();
    public abstract FAQDao faqDao();
    public abstract AdminDao adminDao();
    public abstract InformacaoEventoDao informacaoEventoDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "week_tech_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }
}