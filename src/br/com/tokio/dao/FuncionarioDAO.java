package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.model.Autenticar;
import br.com.tokio.model.Cliente;
import br.com.tokio.model.Funcionario;

public class FuncionarioDAO implements Autenticar {

	private Connection connection;

	/** construtor de FuncionarioDAO
	 * @param Connection - connection
	 * */
	public FuncionarioDAO(Connection connection) {
		this.connection = connection;
	}

	/** insere um funcionario
	 * @param Funcionario - funcionario
	 * */
	public void insert(Funcionario funcionario) {
		String sql = "insert into t_funcionario (nm_func, cpf_func, telefone_func, email_func, ds_acesso_func, dt_admissao, senha) values (?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getTelefone());
			stmt.setString(4, funcionario.getEmail());
			stmt.setString(5, funcionario.getAcessoFunc());
			stmt.setString(6, funcionario.getDataAdmissao().toString());
			stmt.setString(7, funcionario.getSenhaFunc());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** deleta um funcionario
	 * @param int - id do funcionario
	 * */
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

	/** atualiza um funcionario
	 * @param Funcionario - funcionario
	 * */
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

	/** busca todos os funcionarios
	 * @return List<Funcionario> - lista de funcionarios
	 * */
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

	/** busca um funcionario pelo id
	 * @param int - id do funcionario
	 * @return Funcionario - funcionario
	 * */
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
				funcionario.setTelefone(rs.getString("telefone_func"));
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
	
	
	/** busca um funcionario com base nos dados de login
	 * @param String - acesso
	 * @param String - senha
	 * @return Funcionario - funcionario
	 * */
	public Funcionario selectLogin(String acesso, String senha) {
		Funcionario funcionario = new Funcionario();
		String sql = "select * from t_funcionario where ds_acesso_func = ? and senha = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, acesso);
			stmt.setString(2, senha);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				funcionario.setIdFuncionario(rs.getInt("cd_funcionario"));
				funcionario.setNome(rs.getString("nm_func"));
				funcionario.setCpf(rs.getString("cpf_func"));
				funcionario.setTelefone(rs.getString("telefone_func"));
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

	
	/** autentica o login de funcionario
	 * @param String - acesso
	 * @param String - senha
	 * @return List<Integer> - resultado [status, idFuncionario]
	 * */
	@Override
	public List<Integer> autenticacao(String acesso, String senha) {
		
		Funcionario funcionario = selectLogin(acesso, senha);
		List<Integer> resultado = new ArrayList<>();
		
		int status = 0;
		int idFuncionario = 0;
		
		// verifica se os campos estao vazios
		if (funcionario.getAcessoFunc() == null && funcionario.getSenhaFunc() == null) {
			status = 0; 
			idFuncionario = 0;
		} else if (funcionario.getAcessoFunc().equals(acesso) && funcionario.getSenhaFunc().equals(senha)) {
			//valida se estao corretos
				status = 1; 
				idFuncionario = funcionario.getIdFuncionario();
			}
		
		resultado.add(status); 
		resultado.add(idFuncionario); 
		return resultado;

	}

}
