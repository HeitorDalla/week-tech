package com.heitor.week_tech.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * RF04 - Localização e Mapa
 * RF05 - Área de Contato/Suporte
 */
@Entity(tableName = "informacoes_evento")
public class InformacaoEvento {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    
    private String enderecoUniversidade;
    private String linkMapa;
    private String emailSuporte;
    private String telefoneSuporte;

    public InformacaoEvento() {}

    public InformacaoEvento(Long id,
                            String enderecoUniversidade,
                            String linkMapa,
                            String emailSuporte,
                            String telefoneSuporte) {
        this.id = id;
        this.enderecoUniversidade = enderecoUniversidade;
        this.linkMapa = linkMapa;
        this.emailSuporte = emailSuporte;
        this.telefoneSuporte = telefoneSuporte;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEnderecoUniversidade() { return enderecoUniversidade; }
    public void setEnderecoUniversidade(String enderecoUniversidade) { this.enderecoUniversidade = enderecoUniversidade; }
    public String getLinkMapa() { return linkMapa; }
    public void setLinkMapa(String linkMapa) { this.linkMapa = linkMapa; }
    public String getEmailSuporte() { return emailSuporte; }
    public void setEmailSuporte(String emailSuporte) { this.emailSuporte = emailSuporte; }
    public String getTelefoneSuporte() { return telefoneSuporte; }
    public void setTelefoneSuporte(String telefoneSuporte) { this.telefoneSuporte = telefoneSuporte; }
}