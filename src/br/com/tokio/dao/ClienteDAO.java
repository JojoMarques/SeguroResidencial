package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tokio.model.Autenticar;
import br.com.tokio.model.Cliente;
import br.com.tokio.model.Imovel;
import br.com.tokio.model.Seguro;

public class ClienteDAO implements Autenticar {

	private Connection connection;

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
		String sql = "update t_cliente set nm_cliente = ?, cpf_cliente = ?, telefone_cliente = ?, email_cliente = ?, ds_senha_cliente = ? where cd_cliente = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getTelefone());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(5, cliente.getSenhaCliente());
			stmt.setInt(6, cliente.getIdCliente());

			stmt.execute();
			stmt.close();
			System.out.println("UPDATE FEITO");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Select all
	public List<Cliente> selectAll() {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		String sql = "select * from t_cliente";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			System.out.println(sql);
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

	public Cliente selectLogin(String cpf, String senha) {
		Cliente cliente = new Cliente();
		String sql = "select * from t_cliente where cpf_cliente = ? and ds_senha_cliente = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.setString(2, senha);

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
		System.out.println(cliente.getIdCliente() + " - " + cliente.getNome());
		return cliente;
	}
	
	public Cliente getLastCliente() {
		Cliente cliente = new Cliente();
		String sql = "SELECT * FROM t_cliente WHERE cd_cliente = (SELECT MAX(cd_cliente) FROM t_cliente)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
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
		System.out.println(cliente.getIdCliente() + " - " + cliente.getNome());
		return cliente;
	}
	
	public Imovel selectImovelByClienteId(int idCliente) {
	    Imovel imovel = new Imovel();
	    String sql = "SELECT * FROM t_imovel WHERE cd_cliente = ?";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setLong(1, idCliente);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            imovel.setIdImovel(rs.getInt("cd_imovel"));
	            imovel.setCep(rs.getString("cep"));
	            imovel.setNumero(rs.getInt("numero"));
	            imovel.setLogradouro(rs.getString("logradouro"));
	            imovel.setBairro(rs.getString("bairro"));
	            imovel.setCidade(rs.getString("cidade"));
	            imovel.setEstado(rs.getString("estado"));
	            imovel.setPais(rs.getString("pais"));
	            imovel.setArea(rs.getDouble("vl_area"));
	            imovel.setValorImovel(rs.getDouble("vl_imovel"));
	            imovel.setIdCliente(rs.getInt("cd_cliente"));
	        }
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return imovel;
	}
	
	public Seguro selectSeguroByClienteId(int idCliente) {
	    Seguro seguro = new Seguro();
	    String sql = "SELECT cd_seguro, vl_premio, dt_inicio, dt_fim, cd_assistencia, cd_cobertura " +
	                 "FROM t_seguro WHERE cd_cliente = ?";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setInt(1, idCliente);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            seguro.setIdSeguro(rs.getInt("cd_seguro"));
	            seguro.setValorPremio(rs.getDouble("vl_premio"));
	            seguro.setDataInicio(rs.getDate("dt_inicio"));
	            seguro.setDataFim(rs.getDate("dt_fim"));
	            seguro.setIdAssistencia(rs.getInt("cd_assistencia"));
	            seguro.setIdCobertura(rs.getInt("cd_cobertura"));
	        }
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return seguro;
	}
	
	public double getValorAssistencia(int idAssistencia) {
        double valor = 0.0;
        String sql = "SELECT vl_pct_assistencia FROM t_pct_assistencia WHERE cd_assistencia = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idAssistencia);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                valor = rs.getDouble("vl_pct_assistencia");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valor;
    }

    public double getValorCobertura(int idCobertura) {
        double valor = 0.0;
        String sql = "SELECT vl_pct_cobertura FROM t_pct_cobertura WHERE cd_cobertura = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCobertura);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                valor = rs.getDouble("vl_pct_cobertura");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valor;
    }



	@Override
	public List<Integer> autenticacao(String cpf, String senha) {
		// Buscar todos os clientes
		Cliente cliente = new Cliente();
		cliente = selectLogin(cpf, senha);
		List<Integer> resultado = new ArrayList<>();
		// Status padrão: CPF e senha incorretos
		int status = 0;
		int idCliente = 0;
		// Verificar os clientes

		/*
		if(cliente.getCpf().equals(cpf) && cliente.getSenhaCliente().equals(senha)) {
			status = 1;
			idCliente = cliente.getIdCliente();
		} else if ((cliente.getCpf() != cpf) || (cliente.getSenhaCliente() != senha)) {
			status = 2;
			idCliente = 0;
		}else if (cliente.getCpf() == null && cliente.getSenhaCliente() == null) {
			status = 0; // Cliente sem CPF e senha
			idCliente = 0;
		}*/

		if (cliente.getCpf() == null && cliente.getSenhaCliente() == null) {
			status = 0; // Cliente sem CPF e senha
			idCliente = 0;
		} else {
			if (cliente.getCpf().equals(cpf) && cliente.getSenhaCliente().equals(senha)) {
				status = 1; // CPF e senha corretos
				idCliente = cliente.getIdCliente();
			}
		}

		// Adicionar resultados na lista
		resultado.add(status); // Status da autenticação
		resultado.add(idCliente); // ID do cliente (ou 0)
		return resultado;

	}
	
	public int selectIdByCpf(String cpf) {
	    int idCliente = 0;
	    String sql = "SELECT cd_cliente FROM t_cliente WHERE cpf_cliente = ?";
	    
	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setString(1, cpf);  // Definindo o parâmetro CPF
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            idCliente = rs.getInt("cd_cliente");  // Obtendo o ID do cliente
	        }
	        
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return idCliente;  // Retorna o ID do cliente encontrado ou 0 caso não exista
	}

}
