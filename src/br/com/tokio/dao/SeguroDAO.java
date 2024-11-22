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
    	String sql = "INSERT INTO t_seguro (vl_premio, dt_inicio, dt_fim, cd_cliente, cd_cobertura, cd_assistencia, cd_corretora) VALUES (?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?)";
        	
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, seguro.getValorPremio());
            stmt.setString(2, seguro.getDataInicio().toString());
            stmt.setString(3, seguro.getDataFim().toString());
            stmt.setInt(4, seguro.getIdCliente());
            stmt.setInt(5, seguro.getIdCobertura());
            stmt.setInt(6, seguro.getIdAssistencia());
            stmt.setInt(7, seguro.getIdCorretora());
            
            System.out.println("TESTE");
            stmt.execute();
            System.out.println("TESTE");
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete
    public void delete(int idSeguro, int idCliente) {
        String sql = "delete from T_SEGURO where cd_seguro =?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idSeguro);

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // update
    public void update(Seguro seguro) {
        String sql = "update T_SEGURO set vl_premio = ?, cd_cobertura = ?, cd_assistencia = ? where cd_seguro = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setDouble(1, seguro.getValorPremio());
            stmt.setInt(2, seguro.getIdCobertura());
            stmt.setInt(3, seguro.getIdAssistencia());
            stmt.setInt(4, seguro.getIdSeguro());
            

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // select all
    public List<Seguro> selectAll() {
        String sql = "select * from T_SEGURO";
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
        String sql = "select * from T_SEGURO where cd_seguro = ? ";
        Seguro seguro = new Seguro();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idSeguro);

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
    
 // Relatório de Seguros Ativos
    public List<Seguro> relatorioSegurosAtivos() {
        String sql = "SELECT cd_seguro, vl_premio, dt_inicio, dt_fim, cd_cliente, cd_cobertura, cd_assistencia, cd_corretora " +
                     "FROM T_SEGURO " +
                     "WHERE dt_inicio <= CURRENT_DATE AND dt_fim >= CURRENT_DATE";
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
                seguro.setIdCliente(rs.getInt("cd_cliente"));
                seguro.setIdCobertura(rs.getInt("cd_cobertura"));
                seguro.setIdAssistencia(rs.getInt("cd_assistencia"));
                seguro.setIdCorretora(rs.getInt("cd_corretora"));

                listSeguros.add(seguro);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSeguros;
    }

    // Relatório de Seguros por Cliente
    public List<Seguro> relatorioSegurosPorCliente(int idCliente) {
        String sql = "SELECT cd_seguro, vl_premio, dt_inicio, dt_fim, cd_cliente, cd_cobertura, cd_assistencia, cd_corretora " +
                     "FROM T_SEGURO " +
                     "WHERE cd_cliente = ?";
        List<Seguro> listSeguros = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Seguro seguro = new Seguro();
                seguro.setIdSeguro(rs.getInt("cd_seguro"));
                seguro.setValorPremio(rs.getDouble("vl_premio"));
                seguro.setDataInicio(rs.getDate("dt_inicio"));
                seguro.setDataFim(rs.getDate("dt_fim"));
                seguro.setIdCliente(rs.getInt("cd_cliente"));
                seguro.setIdCobertura(rs.getInt("cd_cobertura"));
                seguro.setIdAssistencia(rs.getInt("cd_assistencia"));
                seguro.setIdCorretora(rs.getInt("cd_corretora"));

                listSeguros.add(seguro);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSeguros;
    }

    // Relatório de Seguros por Corretora
    public List<Seguro> relatorioSegurosPorCorretora(int idCorretora) {
        String sql = "SELECT cd_seguro, vl_premio, dt_inicio, dt_fim, cd_cliente, cd_cobertura, cd_assistencia " +
                     "FROM T_SEGURO " +
                     "WHERE cd_corretora = ?";
        List<Seguro> listSeguros = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCorretora);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Seguro seguro = new Seguro();
                seguro.setIdSeguro(rs.getInt("cd_seguro"));
                seguro.setValorPremio(rs.getDouble("vl_premio"));
                seguro.setDataInicio(rs.getDate("dt_inicio"));
                seguro.setDataFim(rs.getDate("dt_fim"));
                seguro.setIdCliente(rs.getInt("cd_cliente"));
                seguro.setIdCobertura(rs.getInt("cd_cobertura"));
                seguro.setIdAssistencia(rs.getInt("cd_assistencia"));

                listSeguros.add(seguro);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSeguros;
    }

    // Relatório de Seguros Vencidos
    public List<Seguro> relatorioSegurosVencidos() {
        String sql = "SELECT cd_seguro, vl_premio, dt_inicio, dt_fim, cd_cliente, cd_cobertura, cd_assistencia, cd_corretora " +
                     "FROM T_SEGURO " +
                     "WHERE dt_fim < CURRENT_DATE";
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
                seguro.setIdCliente(rs.getInt("cd_cliente"));
                seguro.setIdCobertura(rs.getInt("cd_cobertura"));
                seguro.setIdAssistencia(rs.getInt("cd_assistencia"));
                seguro.setIdCorretora(rs.getInt("cd_corretora"));

                listSeguros.add(seguro);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSeguros;
    }

    // Relatório de Seguros por Cobertura
    public List<Seguro> relatorioSegurosPorCobertura(int idCobertura) {
        String sql = "SELECT cd_seguro, vl_premio, dt_inicio, dt_fim, cd_cliente, cd_assistencia, cd_corretora " +
                     "FROM T_SEGURO " +
                     "WHERE cd_cobertura = ?";
        List<Seguro> listSeguros = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCobertura);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Seguro seguro = new Seguro();
                seguro.setIdSeguro(rs.getInt("cd_seguro"));
                seguro.setValorPremio(rs.getDouble("vl_premio"));
                seguro.setDataInicio(rs.getDate("dt_inicio"));
                seguro.setDataFim(rs.getDate("dt_fim"));
                seguro.setIdCliente(rs.getInt("cd_cliente"));
                seguro.setIdCobertura(rs.getInt("cd_cobertura"));
                seguro.setIdAssistencia(rs.getInt("cd_assistencia"));
                seguro.setIdCorretora(rs.getInt("cd_corretora"));

                listSeguros.add(seguro);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSeguros;
    }

    // Relatório de Seguros por Período
    public List<Seguro> relatorioSegurosPorPeriodo(java.sql.Date dataInicio, java.sql.Date dataFim) {
        String sql = "SELECT cd_seguro, vl_premio, dt_inicio, dt_fim, cd_cliente, cd_cobertura, cd_assistencia, cd_corretora " +
                     "FROM T_SEGURO " +
                     "WHERE dt_inicio >= ? AND dt_fim <= ?";
        List<Seguro> listSeguros = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, dataInicio);
            stmt.setDate(2, dataFim);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Seguro seguro = new Seguro();
                seguro.setIdSeguro(rs.getInt("cd_seguro"));
                seguro.setValorPremio(rs.getDouble("vl_premio"));
                seguro.setDataInicio(rs.getDate("dt_inicio"));
                seguro.setDataFim(rs.getDate("dt_fim"));
                seguro.setIdCliente(rs.getInt("cd_cliente"));
                seguro.setIdCobertura(rs.getInt("cd_cobertura"));
                seguro.setIdAssistencia(rs.getInt("cd_assistencia"));
                seguro.setIdCorretora(rs.getInt("cd_corretora"));

                listSeguros.add(seguro);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSeguros;
    }

    // Relatório de Seguros com Assistência Específica
    public List<Seguro> relatorioSegurosComAssistenciaEspecifica(int idAssistencia) {
        String sql = "SELECT cd_seguro, vl_premio, dt_inicio, dt_fim, cd_cliente, cd_cobertura, cd_corretora " +
                     "FROM T_SEGURO " +
                     "WHERE cd_assistencia = ?";
        List<Seguro> listSeguros = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idAssistencia);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Seguro seguro = new Seguro();
                seguro.setIdSeguro(rs.getInt("cd_seguro"));
                seguro.setValorPremio(rs.getDouble("vl_premio"));
                seguro.setDataInicio(rs.getDate("dt_inicio"));
                seguro.setDataFim(rs.getDate("dt_fim"));
                seguro.setIdCliente(rs.getInt("cd_cliente"));
                seguro.setIdCobertura(rs.getInt("cd_cobertura"));
                seguro.setIdAssistencia(rs.getInt("cd_assistencia"));
                seguro.setIdCorretora(rs.getInt("cd_corretora"));

                listSeguros.add(seguro);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSeguros;
    }

    // Relatório de Total de Prêmios por Corretora
    public List<Seguro> relatorioTotalPremioPorCorretora() {
        String sql = "SELECT cd_corretora, SUM(vl_premio) AS totalPremio " +
                     "FROM T_SEGURO " +
                     "GROUP BY cd_corretora";
        List<Seguro> listSeguros = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Seguro seguro = new Seguro();
                seguro.setIdCorretora(rs.getInt("cd_corretora"));
                seguro.setValorPremio(rs.getDouble("totalPremio"));
                
                listSeguros.add(seguro);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSeguros;
    }
    
 // Select seguros por cliente (considerando a associação com imóveis)
    public Seguro selectByCliente(int idCliente) {
        String sql = "SELECT s.cd_seguro, s.vl_premio, s.dt_inicio, s.dt_fim, s.cd_cliente, s.cd_cobertura, s.cd_assistencia, s.cd_corretora " +
                     "FROM T_SEGURO s " +
                     "JOIN T_IMOVEL i ON s.cd_cliente = i.cd_cliente " +  // Join entre seguro e imóvel pela chave cliente
                     "WHERE s.cd_cliente = ?"; // Filtro de cliente

        Seguro seguro = new Seguro();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCliente);  // Passa o ID do cliente

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                

                seguro.setIdSeguro(rs.getInt("cd_seguro"));
                seguro.setValorPremio(rs.getDouble("vl_premio"));
                seguro.setDataInicio(rs.getDate("dt_inicio"));
                seguro.setDataFim(rs.getDate("dt_fim"));
                seguro.setIdCliente(rs.getInt("cd_cliente"));
                seguro.setIdCobertura(rs.getInt("cd_cobertura"));
                seguro.setIdAssistencia(rs.getInt("cd_assistencia"));
                seguro.setIdCorretora(rs.getInt("cd_corretora"));

            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seguro;
    }



}
