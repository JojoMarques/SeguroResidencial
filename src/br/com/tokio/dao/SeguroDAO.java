package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tokio.model.Corretora;
import br.com.tokio.model.PacoteAssistencia;
import br.com.tokio.model.PacoteCobertura;
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
    public List<Seguro> buscarAtivos() {
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
    public List<Seguro> buscarPorCliente(int idCliente) {
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
    public List<Seguro> buscarPorCorretora(int idCorretora) {
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
    public List<Seguro> buscarVencidos() {
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
    public List<Seguro> buscarCobertura(int idCobertura) {
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
    public List<Seguro> buscarPorPeriodo(java.sql.Date dataInicio, java.sql.Date dataFim) {
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
    public List<Seguro> buscarPorAssistencia(int idAssistencia) {
        String sql = "SELECT cd_seguro, vl_premio, dt_inicio, dt_fim, cd_cliente, cd_cobertura, cd_corretora, cd_assistencia " +
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
    public List<Seguro> buscarPremios() {
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
    
 // Select pacote de cobertura por cliente
    public PacoteCobertura selectCoberturaPorCliente(int idCliente) {
        String sql = "SELECT c.cd_cobertura, c.ds_cobertura, c.tp_cobertura, c.vl_pct_cobertura FROM T_SEGURO s  JOIN T_PCT_COBERTURA c ON s.cd_cobertura = c.cd_cobertura  WHERE s.cd_cliente = ?";
        
        PacoteCobertura cobertura = null;  // Inicializando como null, para retornar null se não encontrar

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCliente);  // Passa o ID do cliente como parâmetro

            ResultSet rs = stmt.executeQuery();

            // Usar if ao invés de while, para pegar apenas o primeiro resultado
            if (rs.next()) {  // Se houver pelo menos um resultado
                cobertura = new PacoteCobertura();  // Instanciando o objeto apenas se houver resultado

                cobertura.setIdCobertura(rs.getInt("cd_cobertura"));
                cobertura.setTipo(rs.getString("tp_cobertura"));
                cobertura.setDescricao(rs.getString("ds_cobertura"));  // Corrigido para o campo correto
                cobertura.setPreco(rs.getDouble("vl_pct_cobertura"));
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cobertura;  // Retorna a cobertura encontrada ou null se não houver
    }
   
    
    public PacoteAssistencia selectPacotePorCliente(int idCliente) {
        String sql = "SELECT a.cd_assistencia, a.tp_assistencia, a.ds_assistencia, a.vl_pct_assistencia " +
                     "FROM T_SEGURO s " +
                     "JOIN T_PCT_ASSISTENCIA a ON s.cd_assistencia = a.cd_assistencia " +
                     "WHERE s.cd_cliente = ?";
        
        PacoteAssistencia pacote = null;  // Inicializando a variável como null, caso nenhum pacote seja encontrado

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCliente);  // Definindo o ID do cliente como parâmetro da consulta
            ResultSet rs = stmt.executeQuery();

            // Garantir que apenas o primeiro pacote encontrado seja retornado
            if (rs.next()) {  // Usando "if" para pegar apenas o primeiro registro
                pacote = new PacoteAssistencia();
                pacote.setIdAssistencia(rs.getInt("cd_assistencia"));
                pacote.setTipo(rs.getString("tp_assistencia"));
                pacote.setDescricao(rs.getString("ds_assistencia"));
                pacote.setPreco(rs.getDouble("vl_pct_assistencia"));
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacote;  // Retorna o pacote de assistência, ou null se não encontrar nenhum
    }

    
    public Corretora selectCorretoraByIdSeguro(int idSeguro) {
        // SQL para buscar a corretora associada ao seguro, baseado no ID do seguro
        String sql = "SELECT c.* FROM T_SEGURO s "
                   + "JOIN T_CORRETORA c ON s.cd_corretora = c.cd_corretora "
                   + "WHERE s.cd_seguro = ?";

        Corretora corretora = null;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idSeguro);  // Definindo o ID do seguro como parâmetro da consulta

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Preenche o objeto corretora com os dados retornados
                corretora = new Corretora();
                corretora.setIdCorretora(rs.getInt("cd_corretora"));
                corretora.setNomeCorretora(rs.getString("nm_corretora"));
                corretora.setEndereco(rs.getString("ds_endereco_corretora"));
                // Preenchendo outros campos da corretora, se necessário...
            }

            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return corretora;  // Retorna a corretora associada ao seguro
    }
}

