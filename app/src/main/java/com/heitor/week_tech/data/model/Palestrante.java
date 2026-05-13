package com.heitor.week_tech.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * RF03 – Cadastro Palestrante
 */
@Entity(tableName = "palestrantes")
public class Palestrante {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String temaPalestra;
    private String curriculo;
    private String briefing;
    private int tempoNecessarioMinutos;
    private boolean aprovado;

    public Palestrante() {}
    public Palestrante(Long id,
                       String nome,
                       String telefone,
                       String email,
                       String temaPalestra,
                       String curriculo,
                       String briefing,
                       int tempoNecessarioMinutos,
                       boolean aprovado) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.temaPalestra = temaPalestra;
        this.curriculo = curriculo;
        this.briefing = briefing;
        this.tempoNecessarioMinutos = tempoNecessarioMinutos;
        this.aprovado = aprovado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTemaPalestra() { return temaPalestra; }
    public void setTemaPalestra(String temaPalestra) { this.temaPalestra = temaPalestra; }
    public String getCurriculo() { return curriculo; }
    public void setCurriculo(String curriculo) { this.curriculo = curriculo; }
    public String getBriefing() { return briefing; }
    public void setBriefing(String briefing) { this.briefing = briefing; }
    public int getTempoNecessarioMinutos() { return tempoNecessarioMinutos; }
    public void setTempoNecessarioMinutos(int tempoNecessarioMinutos) { this.tempoNecessarioMinutos = tempoNecessarioMinutos; }
    public boolean isAprovado() { return aprovado; }
    public void setAprovado(boolean aprovado) { this.aprovado = aprovado; }
}