package org.padaria.paokentinho.models.repositories;

import org.padaria.paokentinho.models.entities.Fornada;
import org.padaria.paokentinho.models.entities.Pao;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class FacadeRepository {
    private GenericRepository<Pao, Integer> paoRepository = null;
    private GenericRepository<Fornada, Integer> fornadaRepository = null;

    public FacadeRepository() {
        this.paoRepository = new PaoRepository();
        this.fornadaRepository = new FornadaRepository();
    }

    public void create(Pao pao) throws SQLException {
        this.paoRepository.create(pao);
    }

    public void create(Fornada fornada) throws SQLException {
        this.fornadaRepository.create(fornada);
    }

    public List<Pao> readAllPao() throws SQLException {
        return this.paoRepository.readAll();
    }

    public List<Fornada> readAllFornada() throws SQLException {
        return this.fornadaRepository.readAll();
    }

    public Pao readPao(int id) throws SQLException {
        return this.paoRepository.read(id);
    }

}
