package org.padaria.paokentinho.models.entities;

public class Pao {
    private Integer id;
    private String tipo;
    private String descricao;
    private int tempoForno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTempoForno() {
        return tempoForno;
    }

    public void setTempoForno(int tempoForno) {
        this.tempoForno = tempoForno;
    }
}
