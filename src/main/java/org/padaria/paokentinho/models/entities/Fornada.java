package org.padaria.paokentinho.models.entities;

import java.time.LocalTime;

public class Fornada {
    private Integer id;
    private final Pao pao;
    private final LocalTime horarioSaida;

    public Fornada(Pao pao) {
        this.pao = pao;
        this.horarioSaida = LocalTime.now().plusMinutes(pao.getTempoForno());
    }

    public Fornada(Pao pao, LocalTime horarioSaida) {
        this.pao = pao;
        this.horarioSaida = horarioSaida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pao getPao() {
        return pao;
    }

    public LocalTime getHorarioSaida() {
        return horarioSaida;
    }
}
