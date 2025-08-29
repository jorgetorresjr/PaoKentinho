package org.padaria.paokentinho.models.repositories;

import org.padaria.paokentinho.models.entities.Pao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaoRepository implements GenericRepository<org.padaria.paokentinho.models.entities.Pao, Integer> {
    @Override
    public List<Pao> readAll() throws SQLException{
        String sql = "SELECT * FROM pao";

        List<Pao> paes = new ArrayList<>();

        try {
            PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            Pao pao = null;

            while (rs.next()) {
                pao = new Pao();
                pao.setId(rs.getInt(1));
                pao.setTipo(rs.getString("tipo"));
                pao.setDescricao(rs.getString("descricao"));
                pao.setTempoForno(rs.getInt("tempo_forno"));

                paes.add(pao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paes;
    }

    @Override
    public Pao read(Integer id) throws SQLException {
        String sql = "SELECT * FROM pao WHERE id = ?";

        try {
            PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            Pao pao = null;

            if (rs.next()) {
                pao = new Pao();
                pao.setId(rs.getInt(1));
                pao.setTipo(rs.getString("tipo"));
                pao.setDescricao(rs.getString("descricao"));
                pao.setTempoForno(rs.getInt("tempo_forno"));
            }
            return pao;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void create(Pao pao) throws SQLException {
        String sql = "INSERT INTO pao (tipo, descricao, tempo_forno) VALUES (?, ?, ?)";

        try {
            PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

            pstm.setString(1, pao.getTipo());
            pstm.setString(2, pao.getDescricao());
            pstm.setInt(3, pao.getTempoForno());

            pstm.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
