package com.heitor.week_tech.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * RF06 – Confirmar Participação
 */
@Entity(tableName = "presencas")
public class Presenca {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private Long participanteId;
    private Long palestraId;
    private boolean confirmada;

    public Presenca() {}

    public Presenca(Long id, Long participanteId, Long palestraId, boolean confirmada) {
        this.id = id;
        this.participanteId = participanteId;
        this.palestraId = palestraId;
        this.confirmada = confirmada;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getParticipanteId() { return participanteId; }
    public void setParticipanteId(Long participanteId) { this.participanteId = participanteId; }
    public Long getPalestraId() { return palestraId; }
    public void setPalestraId(Long palestraId) { this.palestraId = palestraId; }
    public boolean isConfirmada() { return confirmada; }
    public void setConfirmada(boolean confirmada) { this.confirmada = confirmada; }
}