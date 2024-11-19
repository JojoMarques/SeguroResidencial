package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.model.Autenticar;
import br.com.tokio.model.Funcionario;

public class FuncionarioDAO implements Autenticar {

	private Connection connection;

	public FuncionarioDAO() {
		this.connection = new ConnectionFactory().conectar(); // criando a conexão e chamando o método conectar
	}

	public FuncionarioDAO(Connection connection) {
		this.connection = connection;
	}

	// Insert
	public void insert(Funcionario funcionario) {
		String sql = "insert into t_funcionario (nm_func, cpf_func, telefone_func, email_func, ds_acesso_func, dt_admissao, senha) values (?, ?, ?, ?, ?, ?, ?)";

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
		String sql = "update t_funcionario set nm_func = ?, cpf_func = ?, telefone_func = ?, email_func = ?, ds_acesso_func = ?, dt_admissao = ?, senha = ? where cd_funcionario";

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
				funcionario.setNome(rs.getString("nm_func"));
				funcionario.setCpf(rs.getString("cpf_func"));
				funcionario.setTelefone(rs.getString("telefone_func"));
				funcionario.setEmail(rs.getString("email_func"));
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
				funcionario.setNome(rs.getString("nm_func"));
				funcionario.setCpf(rs.getString("cpf_func"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setEmail(rs.getString("email_func"));
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

	@Override
	public int autenticacao(String acesso, String senha) {
	    // Aqui, vamos buscar todos os clientes
	    List<Funcionario> listaFuncionarios = selectAll();

	    // Percorrer todos os clientes e comparar o CPF e a senha
	    for (Funcionario funcionario : listaFuncionarios) {
	        if (funcionario.getAcessoFunc().equals(acesso) && funcionario.getSenhaFunc().equals(senha)) {
	            return 1; // acesso e Senha corretos
	        } else if (funcionario.getAcessoFunc().equals(acesso)) {
	            return 2; // acesso correto, mas senha incorreta
	        } else if (funcionario.getSenhaFunc().equals(senha)) {
	            return 3; // Senha correta, mas acesso incorreto
	        }
	    }

	    return 0; // acesso e Senha incorretos
	}



}
