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

	/** construtor do ClienteDAO
	 * @param Connection - connection
	 * */
	public ClienteDAO(Connection connection) {
		this.connection = connection;
	}

	/** insere cliente
	 * @param Cliente - cliente
	 * */
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

	/** deleta cliente
	 * @param int - id do cliente
	 * */
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

	/** atualiza cliente
	 * @param Cliente - cliente
	 * */
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

	/** busca todos os clientes
	 * @return List<Cliente> - cliente
	*/
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

	/** busca um cliente com id específico
	 * @param int - id do cliente
	 * @return Cliente - cliente
	 * */
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

	/** busca um cliente com base nos dados de login
	 * @param String - CPF
	 * @param String - senha
	 * @return Cliente - cliente
	 * */
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
	
	/** busca o último cliente inserido no banco de dados
	 * @return Cliente - cliente
	 * */
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
	
	/** busca um imovel de um cliente específicado pelo id
	 * @param int - id do cliente
	 * @return Imovel - imovel
	 * */
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
	
	/** busca um seguro de um cliente específicado pelo id
	 * @param int - id do cliente
	 * @return Seguro - seguro
	 * */
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
	
	/** busca o valor do pacote de assistencia pelo id dele
	 * @param int - id do pacote assistencia
	 * @return double - valor
	 * */
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

	/** busca o valor do pacote de cobertura pelo id dele
	 * @param int - id do pacote cobertura
	 * @return double - valor
	 * */
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


    /** autentica o login de cliente
	 * @param String - cpf
	 * @param String - senha
	 * @return List<Integer> - resultado [status, idCliente]
	 * */
	@Override
	public List<Integer> autenticacao(String cpf, String senha) {
		
		Cliente cliente = new Cliente();
		cliente = selectLogin(cpf, senha);
		List<Integer> resultado = new ArrayList<>();
		
		int status = 0;
		int idCliente = 0;
		
		// verifica se os campos estao vazios
		if (cliente.getCpf() == null && cliente.getSenhaCliente() == null) {
			status = 0;
			idCliente = 0;
		} else {
			// valida se estao corretos
			if (cliente.getCpf().equals(cpf) && cliente.getSenhaCliente().equals(senha)) {
				status = 1; 
				idCliente = cliente.getIdCliente();
			}
		}

		resultado.add(status); 
		resultado.add(idCliente); 
		return resultado;

	}
	
	 /** busca o id de um cliente pelo cpf
		 * @param String - cpf
		 * @return int - id do cliente
		 * */
	public int selectIdByCpf(String cpf) {
	    int idCliente = 0;
	    String sql = "SELECT cd_cliente FROM t_cliente WHERE cpf_cliente = ?";
	    
	    try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setString(1, cpf); 
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            idCliente = rs.getInt("cd_cliente");
	        }
	        
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return idCliente;  
	}

}
