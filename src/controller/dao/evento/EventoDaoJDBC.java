package controller.dao.evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.entities.evento.Evento;
import model.service.DbException;

public class EventoDaoJDBC implements EventoDaoInterface {

	private Connection conn;

	public EventoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void criarEvento(Evento evento) {
		List<Evento> eventos = this.listarTodosPorNome(evento.getNome());
		if (eventos.size() == 0) {
			PreparedStatement statement = null;

			try {
				String query = "INSERT INTO evento " + "(nome, titulo, descricao, data_inicio, data_termino) "
						+ "VALUES " + "(?, ?, ?, ?, ?)";

				statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

				statement.setString(1, evento.getNome());
				statement.setString(2, evento.getTitulo());
				statement.setString(3, evento.getDescricao());
				statement.setTimestamp(4, Timestamp.valueOf(evento.getDataInicio()));
				statement.setTimestamp(5, Timestamp.valueOf(evento.getDataTermino()));

				statement.executeUpdate();

			} catch (SQLException error) {
				throw new DbException(error.getMessage());
			}
		} else {
			System.out.println("[JÁ EXISTE UM EVENTO COM ESSE NOME]");
		}

	}

	@Override
	public void editarEvento(Evento evento) {

		PreparedStatement statement = null;
		try {
			String query = "UPDATE evento "
					+ "SET nome = ?, titulo = ?, descricao = ?, data_inicio = ?, data_termino = ?" + "WHERE id = ?";

			statement = conn.prepareStatement(query);

			statement.setString(1, evento.getNome());
			statement.setString(2, evento.getTitulo());
			statement.setString(3, evento.getDescricao());
			statement.setTimestamp(4, Timestamp.valueOf(evento.getDataInicio()));
			statement.setTimestamp(5, Timestamp.valueOf(evento.getDataTermino()));
			statement.setInt(6, evento.getId());
			statement.executeUpdate();

		} catch (SQLException error) {
			error.printStackTrace();
		}

	}

	@Override
	public void excluirEvento(Integer id) {

		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM evento WHERE id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Evento> listarTodos() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM evento ORDER BY nome");
			rs = st.executeQuery();

			List<Evento> eventos = new ArrayList<>();

			while (rs.next()) {
				Evento evento = new Evento();
				evento.setId(rs.getInt("id"));
				evento.setNome(rs.getString("nome"));
				evento.setTitulo(rs.getString("titulo"));
				evento.setDescricao(rs.getString("descricao"));
				evento.setDataInicio(rs.getTimestamp("data_inicio").toLocalDateTime());
				evento.setDataTermino(rs.getTimestamp("data_inicio").toLocalDateTime());
				eventos.add(evento);
			}
			return eventos;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public List<Evento> listarTodosPorNome(String nome) {
		List<Evento> eventos = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * FROM evento " 
					+ "WHERE nome LIKE '%" + nome + "%'");
			rs = st.executeQuery();
			while (rs.next()) {
				Evento evento = new Evento();
				evento.setId(rs.getInt("id"));
				evento.setNome(rs.getString("nome"));
				evento.setTitulo(rs.getString("titulo"));
				evento.setDescricao(rs.getString("descricao"));
				evento.setDataInicio(rs.getTimestamp("data_inicio").toLocalDateTime());
				evento.setDataTermino(rs.getTimestamp("data_inicio").toLocalDateTime());
				eventos.add(evento);
			}
			return eventos;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
	}
}
