package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tokio.model.Funcionario;

public class FuncionarioDAO {

	private Connection connection;

	public FuncionarioDAO(Connection connection) {
		this.connection = connection;
	}

	// Insert
	public void insert(Funcionario funcionario) {
		String sql = "insert into t_funcinario (nm_usuario, cpf, telefone, email, ds_acesso_func, dt_admissao, senha) values (?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getTelefone());
			stmt.setString(4, funcionario.getEmail());
			stmt.setString(5, funcionario.getAcessoFunc());
			stmt.setDate(6, funcionario.getDataAdmissao());
			stmt.setString(7, funcionario.getSenhaFunc());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Delete
	public void delete(int idFuncionario) {
		String sql = "delete from t_funcionario where cd_funcionario = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idFuncionario);

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Update
	public void update(Funcionario funcionario) {
		String sql = "update t_funcionario set nm_usuario = ?, cpf = ?, telefone = ?, email = ?, ds_acesso_func = ?, dt_admissao = ?, senha = ? where cd_funcionario";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getTelefone());
			stmt.setString(4, funcionario.getEmail());
			stmt.setString(5, funcionario.getAcessoFunc());
			stmt.setDate(6, funcionario.getDataAdmissao());
			stmt.setString(7, funcionario.getSenhaFunc());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/// Select all:
	public List<Funcionario> selectAll() {
		List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
		String sql = "select * from t_funcionario order by cd_funcionario";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Funcionario funcionario = new Funcionario();

				funcionario.setIdFuncionario(rs.getInt("cd_funcionario"));
				funcionario.setNome(rs.getString("nm_usuario"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setAcessoFunc(rs.getString("ds_acesso_func"));
				funcionario.setDataAdmissao(rs.getDate("dt_admissao"));
				funcionario.setSenhaFunc(rs.getString("senha"));

				listaFuncionarios.add(funcionario);

			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFuncionarios;
	}
	
	// Select by id:
		public Funcionario selectById(int idFuncionario) {
			Funcionario funcionario = new Funcionario();
			String sql = "select * from t_funcionario where cd_funcionario = ?";
			try {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setLong(1, idFuncionario);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {

					funcionario.setIdFuncionario(rs.getInt("cd_funcionario"));
					funcionario.setNome(rs.getString("nm_usuario"));
					funcionario.setCpf(rs.getString("cpf"));
					funcionario.setTelefone(rs.getString("telefone"));
					funcionario.setEmail(rs.getString("email"));
					funcionario.setAcessoFunc(rs.getString("ds_acesso_func"));
					funcionario.setDataAdmissao(rs.getDate("dt_admissao"));
					funcionario.setSenhaFunc(rs.getString("senha"));
				}
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return funcionario;
		}

}
