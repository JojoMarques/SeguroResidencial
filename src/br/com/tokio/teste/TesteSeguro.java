package br.com.tokio.teste;

import java.sql.Connection;
import java.sql.Date;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.SeguroDAO;
import br.com.tokio.model.Seguro;

public class TesteSeguro {

	public static void main(String[] args) {

		Connection connection = new ConnectionFactory().conectar();
		SeguroDAO seguroDAO = new SeguroDAO(connection);
		Seguro seguro1 = new Seguro(2000.00, 1, 1, Date.valueOf("2023-05-10"), Date.valueOf("2023-05-10"), 1, 1);
		Seguro seguro2 = new Seguro(3000.00, 1, 2, Date.valueOf("2023-05-10"), Date.valueOf("2023-05-10"), 2, 2);
		System.out.println("criou");
		seguroDAO.insert(seguro1);
		seguroDAO.insert(seguro2);
		System.out.println("Seguros inseridos com sucesso.");

	}

}
