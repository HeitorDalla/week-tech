package com.heitor.week_tech.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * RF02 – Cadastro Participante/Visitante
 * RF08 – Confirmação Coffee Break
 */
@Entity(tableName = "participantes")
public class Participante {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String ra;
    private String curso;
    private String serie;
    private boolean querCoffeeBreak;

    public Participante() {}

    public Participante(String nome, String email, String cpf, String telefone) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getRa() { return ra; }
    public void setRa(String ra) { this.ra = ra; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }
    public boolean isQuerCoffeeBreak() { return querCoffeeBreak; }
    public void setQuerCoffeeBreak(boolean querCoffeeBreak) { this.querCoffeeBreak = querCoffeeBreak; }
}
