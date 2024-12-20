package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tokio.model.Evento;
import br.com.tokio.model.PacoteAssistencia;
import br.com.tokio.model.Servico;

public class PacoteAssistenciaDAO {
	private Connection connection;

	/** construtor de PacoteAssistenciaDAO
	 * @param Connection - connection
	 * */
	public PacoteAssistenciaDAO(Connection connection) {
		this.connection = connection;
	}

	/** insere um pacote de assistencia
	 * @param PacoteAssistencia - pacoteAssistencia
	 * */
	public void insert(PacoteAssistencia pacoteAssistencia) {
        PacoteAssistenciaServicoDAO pacoteAssistenciaServicoDAO = new PacoteAssistenciaServicoDAO(connection);

        String sql = "INSERT INTO t_pct_assistencia (tp_assistencia, ds_assistencia, vl_pct_assistencia) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pacoteAssistencia.getTipo());
            stmt.setString(2, pacoteAssistencia.getDescricao());
            stmt.setDouble(3, pacoteAssistencia.getPreco());

            stmt.execute();
            
            insertServicos(pacoteAssistencia.getServicos());

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	/** insere uma lista de servico no pacote assistencia
	 * */
    public void insertServicos(List<Servico> servicos) {
        PacoteAssistenciaDAO pacoteAssistenciaDAO = new PacoteAssistenciaDAO(connection);
        PacoteAssistenciaServicoDAO pacoteAssistenciaServicoDAO = new PacoteAssistenciaServicoDAO(connection);
        
        PacoteAssistencia pacoteAssistencia = pacoteAssistenciaDAO.getLastPacoteAssistencia();
        
        for (Servico servico : servicos) {
            pacoteAssistenciaServicoDAO.insert(pacoteAssistencia, servico);
        }
    }

    /** busca o ultimo pacote assistencia adicionado
	 * @param PacoteAssistencia - pacoteAssistencia
	 * */
    public PacoteAssistencia getLastPacoteAssistencia() {
        String sql = "SELECT * FROM t_pct_assistencia WHERE cd_assistencia = (SELECT MAX(cd_assistencia) FROM t_pct_assistencia)";
        PacoteAssistencia pacote = new PacoteAssistencia();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pacote.setIdAssistencia(rs.getInt("cd_assistencia"));
                pacote.setTipo(rs.getString("tp_assistencia"));
                pacote.setDescricao(rs.getString("ds_assistencia"));
                pacote.setPreco(rs.getDouble("vl_pct_assistencia"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacote;
    }

    /** deleta um pacote de assistencia pelo id
     * @param int - id do pacote de assistencia
	 * */
	public void delete(int idAssistencia) {
		String sql = "DELETE FROM  t_pct_assistencia WHERE cd_assistencia = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idAssistencia);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** atualiza um pacote de assistencia 
     * @param PacoteAssistencia - pacoteAssistencia
	 * */
	public void update(PacoteAssistencia pacote) {
		String sql = "UPDATE t_pct_assistencia SET   tp_assistencia  = ?,  ds_assistencia = ?, vl_pct_assistencia = ? WHERE cd_assistencia = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, pacote.getTipo());
			stmt.setString(2, pacote.getDescricao());
			stmt.setDouble(3, pacote.getPreco());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** busca todos os pacotes
     * @return List<PacoteAssistencia> - lista de pacotes
	 * */
	public List<PacoteAssistencia> selectAll() {
		List<PacoteAssistencia> listaPacotes = new ArrayList<>();
		String sql = "SELECT * FROM  t_pct_assistencia ";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PacoteAssistencia pacote = new PacoteAssistencia();
				pacote.setIdAssistencia(rs.getInt("cd_assistencia"));
				pacote.setTipo(rs.getString("tp_assistencia"));
				pacote.setPreco((rs.getDouble("vl_pct_assistencia")));
				pacote.setDescricao(rs.getString("ds_assistencia"));
				listaPacotes.add(pacote);
			}

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaPacotes;
	}

	/** busca um pacote por id
	 * @param int - id do pacote assistencia
     * @return PacoteAssistencia - pacote de assistencia
	 * */
	public PacoteAssistencia selectById(int idAssistencia) {
		PacoteAssistencia pacote = new PacoteAssistencia();
		String sql = "SELECT * FROM  t_pct_assistencia where cd_assistencia = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idAssistencia);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				pacote.setIdAssistencia(rs.getInt("cd_assistencia"));
				pacote.setTipo(rs.getString("tp_assistencia"));
				pacote.setPreco((rs.getDouble("vl_pct_assistencia")));
				pacote.setDescricao(rs.getString("ds_assistencia"));

			}

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pacote;
	}
	
	/** busca todos os servicos do pacote
	 * @param int - id do pacote assistencia
     * @return String - servicos separados por virgula
	 * */
	public String selectServicos(int idAssistencia){
		String servicos = "";
		String sql = "SELECT LISTAGG(s.nm_servico, ', ') WITHIN GROUP (ORDER BY s.nm_servico) AS servicos FROM t_pct_assistencia pa JOIN t_servico_assistencia sa ON pa.cd_assistencia = sa.cd_assistencia JOIN t_servico s ON sa.cd_servico = s.cd_servico WHERE pa.cd_assistencia = ? GROUP BY pa.cd_assistencia";
    	try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idAssistencia);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				servicos = rs.getString("servicos");
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return servicos;
    
	}
	
	/** busca o id do pacote pelo nome dele
	 * @param String - nome do pacote assistencia
     * @return int - id do pacote
	 * */
	public int selectIdByNome(String nomeAssistencia) {
	    int idAssistencia = 0;
	    String sql = "SELECT cd_assistencia FROM t_pct_assistencia WHERE tp_assistencia = ?";
	    
	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setString(1, nomeAssistencia);  // Passando o nome do pacote de assistência como parâmetro
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            idAssistencia = rs.getInt("cd_assistencia");  // Obtendo o ID do pacote de assistência
	        }
	        
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return idAssistencia;  // Retorna o ID do pacote de assistência encontrado ou 0 caso não exista
	}

}
