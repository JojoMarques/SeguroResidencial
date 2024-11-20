package br.com.tokio.teste;

import java.sql.Connection;
import java.sql.Date;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.FuncionarioDAO;
import br.com.tokio.model.Funcionario;

public class TesteFuncionario {

	public static void main(String[] args) {
        Connection connection = new ConnectionFactory().conectar();

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);
		  // Criação de funcionários com o novo construtor em uma única linha
        Funcionario funcionario1 = new Funcionario("João Souza", "12345678901", "11999887766", "joao@gmail.com", "admin", "Joao123", Date.valueOf("2023-01-01"));
        Funcionario funcionario2 = new Funcionario("Ana Costa", "10987654321", "11997766554", "ana@gmail.com", "user", "Ana456", Date.valueOf("2023-02-15"));
        // Inserindo os funcionários no banco de dados
        funcionarioDAO.insert(funcionario1);
        funcionarioDAO.insert(funcionario2);
        System.out.println("Funcionários inseridos com sucesso!");
        
        // Listando os funcionários cadastrados
        System.out.println("\nListando Funcionários:");
        for (Funcionario funcionario : funcionarioDAO.selectAll()) {
            System.out.println("ID Funcionário: " + funcionario.getIdFuncionario());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("CPF: " + funcionario.getCpf());
            System.out.println("Telefone: " + funcionario.getTelefone());
            System.out.println("Email: " + funcionario.getEmail());
            System.out.println("Data de Admissão: " + funcionario.getDataAdmissao());
            System.out.println("Acesso: " + funcionario.getAcessoFunc());
            System.out.println("----------------------------------");
        }
	
	}

}
