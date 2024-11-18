package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tokio.model.Corretora;

public class CorretoraDAO {

    private Connection connection;

    public CorretoraDAO(Connection connection) {
        this.connection = connection;
    }

    // select all
    public List<Corretora> selectAll() {
        String sql = "select * from t_corretora";
        List<Corretora> listCorretoras = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Corretora corretora = new Corretora();

                corretora.setIdCorretora(rs.getInt("cd_corretora"));
                corretora.setNomeCorretora(rs.getString("nm_corretora"));
                corretora.setEndereco(rs.getString("endereco"));

                listCorretoras.add(corretora);
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCorretoras;
    }

    // select by id
    public Corretora selectById(int idCorretora) {
        String sql = "select * from t_corretora where cd_corretora = ?";
        Corretora corretora = new Corretora();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCorretora);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                corretora.setIdCorretora(rs.getInt("cd_corretora"));
                corretora.setNomeCorretora(rs.getString("nm_corretora"));
                corretora.setEndereco(rs.getString("endereco"));
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return corretora;
    }
}