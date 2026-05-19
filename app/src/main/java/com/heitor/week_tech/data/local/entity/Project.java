package com.heitor.week_tech.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entidade do Room que representa um projeto cadastrado para a programação.
 */
@Entity(tableName = "projects")
public class Project {
    // Identificador gerado automaticamente.
    @PrimaryKey(autoGenerate = true)
    private int id;
    // Campos exibidos no evento e no painel administrativo.
    private String title;
    private String description;
    private String local;
    private String horario;
    private String author;

    public Project(String title, String description, String local, String horario, String author) {
        this.title = title;
        this.description = description;
        this.local = local;
        this.horario = horario;
        this.author = author;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
}
