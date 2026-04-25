package com.heitor.week_tech.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * RF09 - Cadastro Projetos
 */
@Entity(tableName = "projetos")
public class Projeto {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String nomeAutor;
    private String raAutor;
    private String nomeProjeto;
    private String descricaoProjeto;

    public Projeto() {}

    public Projeto(Long id, String nomeAutor, String raAutor, String nomeProjeto, String descricaoProjeto) {
        this.id = id;
        this.nomeAutor = nomeAutor;
        this.raAutor = raAutor;
        this.nomeProjeto = nomeProjeto;
        this.descricaoProjeto = descricaoProjeto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomeAutor() { return nomeAutor; }
    public void setNomeAutor(String nomeAutor) { this.nomeAutor = nomeAutor; }
    public String getRaAutor() { return raAutor; }
    public void setRaAutor(String raAutor) { this.raAutor = raAutor; }
    public String getNomeProjeto() { return nomeProjeto; }
    public void setNomeProjeto(String nomeProjeto) { this.nomeProjeto = nomeProjeto; }
    public String getDescricaoProjeto() { return descricaoProjeto; }
    public void setDescricaoProjeto(String descricaoProjeto) { this.descricaoProjeto = descricaoProjeto; }
}