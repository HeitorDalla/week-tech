package com.heitor.week_tech.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * RF05 - Área de Contato/Suporte (FAQ)
 */
@Entity(tableName = "faqs")
public class FAQ {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String pergunta;
    private String resposta;

    public FAQ() {}

    public FAQ(String pergunta, String resposta) {
        this.pergunta = pergunta;
        this.resposta = resposta;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPergunta() { return pergunta; }
    public void setPergunta(String pergunta) { this.pergunta = pergunta; }
    public String getResposta() { return resposta; }
    public void setResposta(String resposta) { this.resposta = resposta; }
}