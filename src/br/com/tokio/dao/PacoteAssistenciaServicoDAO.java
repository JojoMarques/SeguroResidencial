package br.com.tokio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.tokio.model.Evento;
import br.com.tokio.model.PacoteAssistencia;
import br.com.tokio.model.Servico;

public class PacoteAssistenciaServicoDAO {
	private Connection connection;
	
	

	public PacoteAssistenciaServicoDAO(Connection connection) {
		this.connection = connection;
	}



	public void insert(PacoteAssistencia pacoteAssistencia, Servico servico) {
		String sql = "INSERT INTO t_servico_assistencia (cd_assistencia, cd_servico) VALUES (?, ?)";
		
		try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pacoteAssistencia.getIdAssistencia());
            stmt.setInt(2, servico.getIdServico());
         
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}