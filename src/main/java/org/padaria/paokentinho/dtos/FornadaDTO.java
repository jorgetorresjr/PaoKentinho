package org.padaria.paokentinho.dtos;

import org.padaria.paokentinho.models.entities.Fornada;

public class FornadaDTO {
    private Fornada fornada;
    private boolean pronto;
    private long minRestantes;

    public FornadaDTO(Fornada fornada, boolean pronto, long minRestantes) {
        this.fornada = fornada;
        this.pronto = pronto;
        this.minRestantes = minRestantes;
    }

    public Fornada getFornada() {
        return fornada;
    }

    public boolean isPronto() {
        return pronto;
    }

    public long getMinRestantes() {
        return minRestantes;
    }
}
