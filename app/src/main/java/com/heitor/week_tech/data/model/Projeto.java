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

    // Campos simplificados para a UI/Adapter atual
    private String nome;
    private String autor;

    public Projeto() {}

    // Construtor usado pela UI
    public Projeto(String nome, String autor) {
        this.nome = nome;
        this.autor = autor;
    }

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

    public String getNome() { return nome != null ? nome : nomeProjeto; }
    public void setNome(String nome) { this.nome = nome; }
    public String getAutor() { return autor != null ? autor : nomeAutor; }
    public void setAutor(String autor) { this.autor = autor; }
}