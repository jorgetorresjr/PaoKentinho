package org.padaria.paokentinho.models.repositories;

import org.padaria.paokentinho.models.entities.Fornada;
import org.padaria.paokentinho.models.entities.Pao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public final class FornadaRepository implements GenericRepository<org.padaria.paokentinho.models.entities.Fornada, Integer> {
    protected FornadaRepository() {
    }

    @Override
    public void create(Fornada fornada) throws SQLException {
        String sql = "INSERT INTO fornada(pao_id, horario_saida) VALUES (?, ?)";
        try {
            PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

            pstm.setInt(1, fornada.getPao().getId());
            pstm.setTime(2, Time.valueOf(fornada.getHorarioSaida()));

            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Fornada read(Integer id) throws SQLException {
        String sql = "SELECT * FROM fornada WHERE id = ?";

        try {
            PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            Fornada fornada = null;

            if (rs.next()) {
                PaoRepository paoRep = new PaoRepository();
                Pao pao = paoRep.read(rs.getInt("pao_id"));

                LocalTime horarioSaida = rs.getTime("horario_saida").toLocalTime();

                fornada = new Fornada(pao, horarioSaida);
                fornada.setId(rs.getInt(1));
            }
            return fornada;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Fornada> readAll() {
        String sql = "SELECT * FROM fornada";

        List<Fornada> fornadas = new ArrayList<>();

        try {
            PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            Fornada fornada = null;

            while (rs.next()) {
                Pao pao = new Pao();
                pao.setId(rs.getInt("pao_id"));

                LocalTime horarioSaida = rs.getTime("horario_saida").toLocalTime();

                fornada = new Fornada(pao, horarioSaida);
                fornada.setId(rs.getInt(1));

                fornadas.add(fornada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fornadas;
    }
}
