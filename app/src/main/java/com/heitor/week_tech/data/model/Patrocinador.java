package com.heitor.week_tech.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patrocinadores")
public class Patrocinador {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String nome;
    private String logoUrl; // Para exibir a imagem do patrocinador
    private String linkSite;

    public Patrocinador() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public String getLinkSite() { return linkSite; }
    public void setLinkSite(String linkSite) { this.linkSite = linkSite; }
}