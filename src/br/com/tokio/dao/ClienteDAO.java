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

public class ClienteDAO implements Autenticar {

	private Connection connection;

	public ClienteDAO() {
		this.connection = new ConnectionFactory().conectar(); // criando a conexão e chamando o método conectar
	}

	public ClienteDAO(Connection connection) {
		this.connection = connection;
	}

	// Insert
	public void insert(Cliente cliente) {
		String sql = "insert into t_cliente (nm_cliente, cpf_cliente, telefone_cliente, email_cliente, ds_senha_cliente) values (?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getTelefone());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(5, cliente.getSenhaCliente());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Delete
	public void delete(int idCliente) {
		String sql = "delete from t_cliente where cd_cliente = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idCliente);

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Update
	public void update(Cliente cliente) {
		String sql = "update t_cliente set nm_cliente = ?, cpf_cliente = ?, telefone_cliente = ?, email_cliente = ?, ds_senha_cliente = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getTelefone());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(5, cliente.getSenhaCliente());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Select all
	public List<Cliente> selectAll() {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		String sql = "select * from t_cliente order by cd_cliente";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setIdCliente(rs.getInt("cd_cliente"));
				cliente.setNome(rs.getString("nm_cliente"));
				cliente.setCpf(rs.getString("cpf_cliente"));
				cliente.setTelefone(rs.getString("telefone_cliente"));
				cliente.setEmail(rs.getString("email_cliente"));
				cliente.setSenhaCliente(rs.getString("ds_senha_cliente"));

				listaClientes.add(cliente);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaClientes;
	}

	// Select by id
	public Cliente selectById(int idCliente) {
		Cliente cliente = new Cliente();
		String sql = "select * from t_cliente where cd_cliente = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idCliente);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cliente.setIdCliente(rs.getInt("cd_cliente"));
				cliente.setNome(rs.getString("nm_cliente"));
				cliente.setCpf(rs.getString("cpf_cliente"));
				cliente.setTelefone(rs.getString("telefone_cliente"));
				cliente.setEmail(rs.getString("email_cliente"));
				cliente.setSenhaCliente(rs.getString("ds_senha_cliente"));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

	@Override
	public int autenticacao(String cpf, String senha) {
	    // Aqui, vamos buscar todos os clientes
	    List<Cliente> listaClientes = selectAll();

	    // Percorrer todos os clientes e comparar o CPF e a senha
	    for (Cliente cliente : listaClientes) {
	        if (cliente.getCpf().equals(cpf) && cliente.getSenhaCliente().equals(senha)) {
	            return 1; // CPF e Senha corretos
	        } else if (cliente.getCpf().equals(cpf)) {
	            return 2; // CPF correto, mas senha incorreta
	        } else if (cliente.getSenhaCliente().equals(senha)) {
	            return 3; // Senha correta, mas CPF incorreto
	        }
	    }

	    return 0; // CPF e Senha incorretos
	}

}
