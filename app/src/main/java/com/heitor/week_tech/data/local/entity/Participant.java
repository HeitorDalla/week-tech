package com.heitor.week_tech.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entidade do Room que representa um participante cadastrado no evento.
 */
@Entity(tableName = "participants")
public class Participant {
    // Chave primária gerada automaticamente pelo Room.
    @PrimaryKey(autoGenerate = true)
    private int id;
    // Dados básicos usados no cadastro e na listagem administrativa.
    private String name;
    private String email;
    private String cpf;
    private String phone;
    private String course;
    private String period;
    // Indica se o participante deseja Coffee Break.
    private boolean coffeeBreak;

    public Participant(String name, String email, String cpf, String phone, String course, String period, boolean coffeeBreak) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.course = course;
        this.period = period;
        this.coffeeBreak = coffeeBreak;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }
    public boolean isCoffeeBreak() { return coffeeBreak; }
    public void setCoffeeBreak(boolean coffeeBreak) { this.coffeeBreak = coffeeBreak; }
}