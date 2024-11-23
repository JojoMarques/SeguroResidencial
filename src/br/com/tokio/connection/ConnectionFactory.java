package br.com.tokio.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	/*
	 * @return Connection
	 * */
	public Connection conectar() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "tm22", "31102005");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}