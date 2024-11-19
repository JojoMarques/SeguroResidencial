package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.tokio.model.Evento;
import br.com.tokio.model.PacoteCobertura;
import br.com.tokio.model.Servico;

public class PacoteCoberturaEventoDAO {
	private Connection connection;
	
	
	
	public PacoteCoberturaEventoDAO(Connection connection) {
		this.connection = connection;
	}



	public void insert(PacoteCobertura pacoteCobertura, Evento evento) {
		String sql = "INSERT INTO t_evento_cobertura (cd_cobertura, cd_evento) VALUES (?, ?)";
		try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pacoteCobertura.getIdCobertura());
            stmt.setInt(2, evento.getIdEvento());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}