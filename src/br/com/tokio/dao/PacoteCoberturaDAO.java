package br.com.tokio.dao;

import br.com.tokio.model.PacoteCobertura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacoteCoberturaDAO {
    private Connection connection;

    // Construtor que inicializa a conexão
    public PacoteCoberturaDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir
    public void insert(PacoteCobertura pacoteCobertura) {
        String sql = "INSERT INTO t_pct_cobertura (tp_cobertura, ds_cobertura, vl_pct_cobertura) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pacoteCobertura.getTipo());
            stmt.setString(2, pacoteCobertura.getDescricao());
            stmt.setDouble(3, pacoteCobertura.getPreco());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar
    public void delete(int idCobertura) {
        String sql = "DELETE FROM t_pct_cobertura WHERE cd_cobertura = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCobertura);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar
    public void update(PacoteCobertura pacoteCobertura) {
        String sql = "UPDATE t_pct_cobertura SET tp_cobertura = ?, ds_cobertura = ?, vl_pct_cobertura = ? WHERE cd_cobertura = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pacoteCobertura.getTipo());
            stmt.setString(2, pacoteCobertura.getDescricao());
            stmt.setDouble(3, pacoteCobertura.getPreco());
            stmt.setInt(4, pacoteCobertura.getIdCobertura());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para selecionar todos os registros
    public List<PacoteCobertura> selectAll() {
        List<PacoteCobertura> pacotes = new ArrayList<>();
        String sql = "SELECT * FROM t_pct_cobertura ORDER BY tp_cobertura";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PacoteCobertura pacote = new PacoteCobertura();
                pacote.setIdCobertura(rs.getInt("cd_cobertura"));
                pacote.setTipo(rs.getString("tp_cobertura"));
                pacote.setDescricao(rs.getString("ds_cobertura"));
                pacote.setPreco(rs.getDouble("vl_pct_cobertura"));
                pacotes.add(pacote);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacotes;
    }

    // Método para selecionar um registro por ID
    public PacoteCobertura selectById(int idCobertura) {
        PacoteCobertura pacote = null;
        String sql = "SELECT * FROM t_pct_cobertura WHERE cd_cobertura = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCobertura);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pacote = new PacoteCobertura();
                pacote.setIdCobertura(rs.getInt("cd_obertura"));
                pacote.setTipo(rs.getString("tp_cobertura"));
                pacote.setDescricao(rs.getString("ds_cobertura"));
                pacote.setPreco(rs.getDouble("vl_preco_cobertura"));
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacote;
    }
}

