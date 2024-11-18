package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tokio.model.Cliente;
import br.com.tokio.model.Funcionario;

public class ClienteDAO {

	private Connection connection;

	public ClienteDAO(Connection connection) {
		this.connection = connection;
	}

	// Insert
	public void insert(Cliente cliente) {
		String sql = "insert into t_cliente (nm_usuario, cpf, telefone, email, ds_senha_cliente) values (?, ?, ?, ?, ?)";

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
		String sql = "update t_cliente set nm_usuario = ?, cpf = ?, telefone = ?, email = ?, ds_senha_cliente = ? where cd_cliente = ?";

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

	/// Select all:
	public List<Cliente> selectAll() {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		String sql = "select * from t_cliente order by cd_cliente";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setIdCliente(rs.getInt("cd_cliente"));
				cliente.setNome(rs.getString("nm_usuario"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setEmail(rs.getString("email"));
				cliente.setSenhaCliente(rs.getString("ds_senha_cliente"));
				
				listaClientes.add(cliente);

			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaClientes;
	}

	// Select by id:
	public Cliente selectById(int idCliente) {
		Cliente cliente = new Cliente();
		String sql = "select * from t_cliente where cd_cliente = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				cliente.setIdCliente(rs.getInt("cd_cliente"));
				cliente.setNome(rs.getString("nm_usuario"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setEmail(rs.getString("email"));
				cliente.setSenhaCliente(rs.getString("ds_senha_cliente"));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

}
