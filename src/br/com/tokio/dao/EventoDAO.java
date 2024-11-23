package br.com.tokio.dao;

import br.com.tokio.model.Evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {
	private Connection connection;

	/** construtor de EventoDAO
	 * @param Connection - connection
	 * */
	public EventoDAO(Connection connection) {
		this.connection = connection;
	}

	/** insere um evento
	 * @param Evento - evento
	 * */
	public void insert(Evento evento) {
		String sql = "INSERT INTO t_evento (nm_evento, ds_evento) VALUES (?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, evento.getNome());
			stmt.setString(2, evento.getDescricao());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** deleta um evento
	 * @param int - id do evento
	 * */
	public void delete(int idEvento) {
		String sql = "DELETE FROM t_evento WHERE cd_evento = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idEvento);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** atualiza um evento
	 * @param Evento - evento
	 * */
	public void update(Evento evento) {
		String sql = "UPDATE t_evento SET nm_evento = ?, ds_evento = ? WHERE cd_evento = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, evento.getNome());
			stmt.setString(2, evento.getDescricao());
			stmt.setInt(3, evento.getIdEvento());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** busca todos os eventos
	 * @return List<Evento> - lista de eventos
	 * */
	public List<Evento> selectAll() {
		List<Evento> eventos = new ArrayList<>();
		String sql = "SELECT * FROM t_evento ORDER BY nm_evento";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Evento evento = new Evento();
				evento.setIdEvento(rs.getInt("cd_evento"));
				evento.setNome(rs.getString("nm_evento"));
				evento.setDescricao(rs.getString("ds_evento"));
				eventos.add(evento);
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventos;
	}

	/** busca um evento pelo id
	 * @param int - id do evento
	 * @return Evento - evento
	 * */
	public Evento selectById(int idEvento) {
		Evento evento = null;
		String sql = "SELECT * FROM t_evento WHERE cd_evento = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idEvento);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				evento = new Evento();
				evento.setIdEvento(rs.getInt("cd_evento"));
				evento.setNome(rs.getString("nm_evento"));
				evento.setDescricao(rs.getString("ds_evento"));
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return evento;
	}
}
