package com.heitor.week_tech.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "palestras")
public class Palestra {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String titulo;
    private String descricao;
    private String horario;
    private String local;
    private Long palestranteId;

    public Palestra() {}

    // Construtor usado pela UI
    public Palestra(String titulo, String horario, String local) {
        this.titulo = titulo;
        this.horario = horario;
        this.local = local;
    }

    public Palestra(Long id, String titulo, String descricao, String horario, String local, Long palestranteId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.horario = horario;
        this.local = local;
        this.palestranteId = palestranteId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }
    public Long getPalestranteId() { return palestranteId; }
    public void setPalestranteId(Long palestranteId) { this.palestranteId = palestranteId; }
}