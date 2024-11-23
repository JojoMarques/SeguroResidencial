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
	
	 /** construtor de ServicoDAO
		 * @param Connection - connection
		 * */
	public ServicoDAO(Connection connection) {
		this.connection = connection;
	}

	/** insere um servico
	 * @param Servico - servico
	 * */
	public void insert(Servico servico) {
		String sql = "INSERT INTO t_servico (nm_servico, ds_servico) VALUES (?, ?)";

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

	/** deletar um servico
	 * @param int - id do servico
	 * */
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

	/** atualiza um servico
	 * @param Servico - servico
	 * */
	public void update(Servico servico) {
		String sql = "UPDATE t_servico SET nm_servico = ?, ds_servico = ? WHERE cd_servico = ?";
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

	/** listar todos os servicos
	 * @param List<Servico> - lista de servicos
	 * */
	public List<Servico> selectAll() {
		List<Servico> listaServicos = new ArrayList<>();
		String sql = "SELECT * FROM t_servico ORDER BY nm_servico";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Servico servico = new Servico();
				servico.setIdServico(rs.getInt("cd_servico"));
				servico.setNome(rs.getString("nm_servico"));
				servico.setDescricao(rs.getString("ds_servico"));
				listaServicos.add(servico);
			}

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaServicos;
	}

	/** busca um servico pelo id
	 * @param int - id do servico
	 * @return Servico - servico
	 * */
	public Servico selectById(int idServico) {
		Servico servico = new Servico();
		String sql = "SELECT * FROM t_servico WHERE cd_servico = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idServico);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				servico = new Servico();
				servico.setIdServico(rs.getInt("cd_servico"));
				servico.setNome(rs.getString("nm_servico"));
				servico.setDescricao(rs.getString("ds_servico"));
			}

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return servico;
	}
}
