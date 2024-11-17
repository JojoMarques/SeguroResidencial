package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tokio.model.Servico;

public class ServicoDAO {

	private Connection connection;

	public ServicoDAO(Connection connection) {
		this.connection = connection;
	}

	// Inserir um novo serviço
	public void insert(Servico servico) {
		String sql = "INSERT INTO t_servico (nome, descricao) VALUES (?, ?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, servico.getNome());
			stmt.setString(2, servico.getDescricao());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Deletar um serviço por ID
	public void delete(int idServico) {
		String sql = "DELETE FROM t_servico WHERE cd_servico = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idServico);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Atualizar um serviço existente
	public void update(Servico servico) {
		String sql = "UPDATE t_servico SET nome = ?, descricao = ? WHERE cd_servico = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, servico.getNome());
			stmt.setString(2, servico.getDescricao());
			stmt.setInt(3, servico.getIdServico());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Listar todos os serviços
	public List<Servico> selectAll() {
		List<Servico> listaServicos = new ArrayList<>();
		String sql = "SELECT * FROM t_servico ORDER BY nome";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Servico servico = new Servico();
				servico.setIdServico(rs.getInt("cd_servico"));
				servico.setNome(rs.getString("nome"));
				servico.setDescricao(rs.getString("descricao"));
				listaServicos.add(servico);
			}

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaServicos;
	}

	// Buscar um serviço por ID
	public Servico selectById(int idServico) {
		Servico servico = new Servico();
		String sql = "SELECT * FROM t_servico WHERE cd_servico = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idServico);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				servico = new Servico();
				servico.setIdServico(rs.getInt("cd_Servico"));
				servico.setNome(rs.getString("nome"));
				servico.setDescricao(rs.getString("descricao"));
			}

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return servico;
	}

	
}
