package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tokio.model.Seguro;

public class SeguroDAO {

    private Connection connection;

    public SeguroDAO(Connection connection) {
        this.connection = connection;
    }

    // insert
    public void insert(Seguro seguro) {
        String sql = "INSERT INTO t_seguro (vl_premio, dt_inicio, dt_fim, cd_cliente, cd_cobertura, cd_assistencia, cd_corretora) VALUES (?, ?, ?, ?, ?, ?, ?)";
        	
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, seguro.getValorPremio());
            stmt.setDate(2, seguro.getDataInicio());
            stmt.setDate(3, seguro.getDataFim());
            stmt.setInt(4, seguro.getIdCliente());
            stmt.setInt(5, seguro.getIdCobertura());
            stmt.setInt(6, seguro.getIdAssistencia());
            stmt.setInt(7, seguro.getIdCorretora());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete
    public void delete(int idSeguro, int idCliente) {
        String sql = "delete from T_SEGURO where cd_seguro = ? and cd_cliente = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idSeguro);
            stmt.setInt(2, idCliente);

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // update
    public void update(Seguro seguro) {
        String sql = "update T_SEGURO set vl_premio = ?, dt_inicio = ?, dt_fim = ?, cd_cobertura = ?, cd_assistencia = ? where cd_seguro = ? and cd_cliente = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setDouble(1, seguro.getValorPremio());
            stmt.setDate(2, seguro.getDataInicio());
            stmt.setDate(3, seguro.getDataFim());
            stmt.setInt(4, seguro.getIdCobertura());
            stmt.setInt(5, seguro.getIdAssistencia());
            stmt.setInt(6, seguro.getIdSeguro());
            stmt.setInt(7, seguro.getIdCliente());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // select all
    public List<Seguro> selectAll() {
        String sql = "select * from T_SEGURO order by cd_seguro";
        List<Seguro> listSeguros = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Seguro seguro = new Seguro();

                seguro.setIdSeguro(rs.getInt("cd_seguro"));
                seguro.setValorPremio(rs.getDouble("vl_premio"));
                seguro.setDataInicio(rs.getDate("dt_inicio"));
                seguro.setDataFim(rs.getDate("dt_fim"));
                seguro.setIdCobertura(rs.getInt("cd_cobertura"));
                seguro.setIdAssistencia(rs.getInt("cd_assistencia"));
                seguro.setIdCliente(rs.getInt("cd_cliente"));
                seguro.setIdCorretora(rs.getInt("cd_corretora"));

                listSeguros.add(seguro);
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSeguros;
    }

    // select by id
    public Seguro selectById(int idSeguro, int idCliente) {
        String sql = "select * from T_SEGURO where cd_seguro = ? and cd_cliente = ?";
        Seguro seguro = new Seguro();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idSeguro);
            stmt.setInt(2, idCliente);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                seguro.setIdSeguro(rs.getInt("cd_seguro"));
                seguro.setValorPremio(rs.getDouble("vl_premio"));
                seguro.setDataInicio(rs.getDate("dt_inicio"));
                seguro.setDataFim(rs.getDate("dt_fim"));
                seguro.setIdCobertura(rs.getInt("cd_cobertura"));
                seguro.setIdAssistencia(rs.getInt("cd_assistencia"));
                seguro.setIdCliente(rs.getInt("cd_cliente"));
                seguro.setIdCorretora(rs.getInt("cd_corretora"));
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seguro;
    }
}
