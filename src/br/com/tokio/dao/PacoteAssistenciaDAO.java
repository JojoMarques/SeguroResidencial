package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.tokio.model.PacoteAssistencia;

public class PacoteAssistenciaDAO {
	private Connection connection;

	public PacoteAssistenciaDAO(Connection connection) {
		this.connection = connection;
	}

	public void insert(PacoteAssistencia pacote) {
		String sql = "INSERT INTO  t_pct_assistencia (tp_assistencia, ds_assistencia, vl_pct_assistencia) VALUES (?, ?, ?)";

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

	// Deletar um serviço por ID
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

	// Atualizar um serviço existente
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

	// Listar todos os serviços
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

	// Buscar um serviço por ID
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
}
