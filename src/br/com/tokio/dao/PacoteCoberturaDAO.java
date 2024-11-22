package br.com.tokio.dao;

import br.com.tokio.model.Evento;
import br.com.tokio.model.PacoteCobertura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacoteCoberturaDAO {
	private Connection connection;

	// Construtor que inicializa a conexão
	public PacoteCoberturaDAO(Connection connection) {
		this.connection = connection;
	}

	public void insert(PacoteCobertura pacoteCobertura) {
		PacoteCoberturaEventoDAO pacoteCoberturaEventoDAO = new PacoteCoberturaEventoDAO(connection);

		String sql = "INSERT INTO t_pct_cobertura (tp_cobertura, ds_cobertura, vl_pct_cobertura) VALUES (?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, pacoteCobertura.getTipo());
			stmt.setString(2, pacoteCobertura.getDescricao());
			stmt.setDouble(3, pacoteCobertura.getPreco());

			stmt.execute();

			insertEventos(pacoteCobertura.getCoberturas());

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertEventos(List<Evento> eventos) {
		PacoteCoberturaDAO pacoteCoberturaDAO = new PacoteCoberturaDAO(connection);
		PacoteCoberturaEventoDAO pacoteCoberturaEventoDAO = new PacoteCoberturaEventoDAO(connection);

		PacoteCobertura pacoteCobertura = pacoteCoberturaDAO.getLastPacoteCobertura();

		for (Evento evento : eventos) {
			pacoteCoberturaEventoDAO.insert(pacoteCobertura, evento);
		}
	}

	public PacoteCobertura getLastPacoteCobertura() {
		String sql = "SELECT * FROM t_pct_cobertura WHERE cd_cobertura = (SELECT MAX(cd_cobertura) FROM t_pct_cobertura)";
		PacoteCobertura pacote = new PacoteCobertura();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				pacote.setIdCobertura(rs.getInt("cd_cobertura"));
				pacote.setTipo(rs.getString("tp_cobertura"));
				pacote.setDescricao(rs.getString("ds_cobertura"));
				pacote.setPreco(rs.getDouble("vl_pct_cobertura"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pacote;
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
				pacote.setIdCobertura(rs.getInt("cd_cobertura"));
				pacote.setTipo(rs.getString("tp_cobertura"));
				pacote.setDescricao(rs.getString("ds_cobertura"));
				pacote.setPreco(rs.getDouble("vl_pct_cobertura"));
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pacote;
	}

	public String selectEventos(int idCobertura){
		String eventos = "";
		String sql = "SELECT LISTAGG(e.nm_evento, ', ') WITHIN GROUP (ORDER BY e.nm_evento) AS eventos FROM t_pct_cobertura c JOIN t_evento_cobertura ec ON c.cd_cobertura = ec.cd_cobertura JOIN t_evento e ON ec.cd_evento = e.cd_evento WHERE c.cd_cobertura = ? GROUP BY c.cd_cobertura";
    	try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idCobertura);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				eventos = rs.getString("eventos");
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventos;
    
	}

}
