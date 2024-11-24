package br.com.tokio.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	/** gera uma conexao com o banco de dados oracle
	 * @return Connection
	 * */
	public Connection conectar() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "tm20", "26062006");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}