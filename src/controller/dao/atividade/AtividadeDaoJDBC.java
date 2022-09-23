package controller.dao.atividade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.dao.evento.EventoDaoJDBC;
import model.entities.atividade.Atividade;
import model.entities.evento.Evento;
import model.enums.TipoAtividade;
import model.service.DbException;

public class AtividadeDaoJDBC implements AtividadeDaoInterface {

	private Connection conn;

	public AtividadeDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void criarAtividade(Atividade atividade) {
		List<Atividade> atividades = this.listarTodosPorNome(atividade.getTitulo());
		if (atividades.size() == 0) {
			PreparedStatement statement = null;

			try {
				String query = "INSERT INTO atividade "
						+ "(titulo_atividade, descricao_atividade, tipo, data_inicio_atividade, data_termino_atividade, duracao, nome_responsavel, id_evento) "
						+ "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?)";

				statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

				statement.setString(1, atividade.getTitulo());
				statement.setString(2, atividade.getDescricao());
				statement.setString(3, atividade.getTipoAtividade().name());
				statement.setTimestamp(4, Timestamp.valueOf(atividade.getDataInicio()));
				statement.setTimestamp(5, Timestamp.valueOf(atividade.getDataTermino()));
				statement.setDouble(6, atividade.getDuracao());
				statement.setString(7, atividade.getNomeResponsavel());
				statement.setInt(8, atividade.getEvento().getId());

				int rowsAffected = statement.executeUpdate();

				if (rowsAffected > 0) {
					ResultSet rs = statement.getGeneratedKeys();
					if (rs.next()) {
						int id = rs.getInt(1);
						atividade.setId(id);
					}
				} else {
					throw new DbException("Unexpected error! No rows affected!");
				}
			}

			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		} else {
			System.out.println("[JÁ EXISTE UMA ATIVIDADE COM ESSE NOME, SEJA MAIS CRIATIVO!]");
		}

	}

	@Override
	public void editarAtividade(Atividade atividade) {
		PreparedStatement statement = null;
		try {
			String query = "UPDATE atividade " 
					+ "SET " + "titulo_atividade =  ?, " 
					+ "descricao_atividade =  ?, " 
					+ "tipo =  ? "
					+ "data_inicio_atividade =  ? " 
					+ "data_termino_atividade =  ? " 
					+ "duracao =  ? " 
					+ "nome_responsavel =  ? "
					+ "id_evento =  ? " 
					+ "WHERE id = ?";

			statement = conn.prepareStatement(query);
			statement.setString(1, atividade.getTitulo());
			statement.setString(2, atividade.getDescricao());
			statement.setString(3, atividade.getTipoAtividade().name());
			statement.setTimestamp(4, Timestamp.valueOf(atividade.getDataInicio()));
			statement.setTimestamp(5, Timestamp.valueOf(atividade.getDataTermino()));			
			statement.setDouble(6, atividade.getDuracao());
			statement.setString(7, atividade.getNomeResponsavel());
			statement.setInt(8, atividade.getEvento().getId());
			statement.setInt(9, atividade.getId());
			statement.executeUpdate();

		} catch (SQLException error) {
			error.printStackTrace();
		}

	}

	@Override
	public void excluirAtividade(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM atividade WHERE id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	private Atividade instantiateAtividade(ResultSet rs, Evento evento) throws SQLException {
		Atividade atividade = new Atividade();
		atividade.setId(rs.getInt("id"));
		atividade.setTitulo(rs.getString("titulo_atividade"));
		atividade.setDescricao(rs.getString("descricao_atividade"));
		atividade.setTipoAtividade(TipoAtividade.valueOf(rs.getString("tipo")));
		atividade.setDataInicio(rs.getTimestamp("data_inicio_atividade").toLocalDateTime());
		atividade.setDataTermino(rs.getTimestamp("data_termino_atividade").toLocalDateTime());
		atividade.setDuracao(rs.getDouble("duracao"));
		atividade.setNomeResponsavel(rs.getString("nome_responsavel"));
		atividade.setEvento(evento);
		return atividade;
	}

	private Evento instantiateEvento(ResultSet rs) throws SQLException {
		Evento evento = new Evento();
		evento.setId(rs.getInt("id"));
		evento.setNome(rs.getString("nome"));
		evento.setTitulo(rs.getString("titulo"));
		evento.setDescricao(rs.getString("descricao"));
		evento.setDataInicio(rs.getTimestamp("data_inicio").toLocalDateTime());
		evento.setDataTermino(rs.getTimestamp("data_termino").toLocalDateTime());
		return evento;
	}

	@Override
	public List<Atividade> listarTodos() {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			st = conn.prepareStatement(
					"SELECT * FROM atividade INNER JOIN evento "
					+ "WHERE atividade.id_evento = evento.id " 
							+ "ORDER BY titulo_atividade");

			rs = st.executeQuery();

			List<Atividade> atividades = new ArrayList<>();
			Map<Integer, Evento> map = new HashMap<>();

			while (rs.next()) {

				Evento evento = map.get(rs.getInt("id_evento"));

				if (evento == null) {
					evento = instantiateEvento(rs);
					map.put(rs.getInt("id_evento"), evento);
				}

				Atividade atividade = instantiateAtividade(rs, evento);
				atividades.add(atividade);
			}
			return atividades;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	@Override
	public Atividade listarTodosPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		EventoDaoJDBC eventoDaoJDBC = new EventoDaoJDBC(conn);
		
		try {
			st = conn.prepareStatement(
					"SELECT * FROM atividade " 
					+ "WHERE id = " + id);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Atividade atividade = new Atividade();
				
				atividade.setId(id);
				atividade.setTitulo(rs.getString("titulo_atividade"));
				atividade.setDescricao(rs.getString("descricao_atividade"));
				atividade.setTipoAtividade(TipoAtividade.valueOf(rs.getString("tipo")));
				atividade.setDataInicio(rs.getTimestamp("data_inicio_atividade").toLocalDateTime());
				atividade.setDataTermino(rs.getTimestamp("data_termino_atividade").toLocalDateTime());
				atividade.setDuracao(rs.getDouble("duracao"));
				atividade.setNomeResponsavel("nome_responsavel");
				atividade.setEvento(eventoDaoJDBC.listarTodosPorId(rs.getInt("id_evento")));
				return atividade;
			}
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	@Override
	public List<Atividade> listarTodosPorNome(String nome) {
		
		List<Atividade> atividades = new ArrayList<>();
		EventoDaoJDBC eventoDaoJDBC = new EventoDaoJDBC(conn);
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * FROM atividade " 
					+ "WHERE titulo_atividade LIKE '%" + nome + "%'");
			rs = st.executeQuery();
			while (rs.next()) {
				Atividade atividade = new Atividade();
				atividade.setId(rs.getInt("id"));
				atividade.setTitulo(rs.getString("titulo_atividade"));
				atividade.setDescricao(rs.getString("descricao_atividade"));
				atividade.setTipoAtividade(TipoAtividade.valueOf(rs.getString("tipo")));
				atividade.setDataInicio(rs.getTimestamp("data_inicio_atividade").toLocalDateTime());
				atividade.setDataTermino(rs.getTimestamp("data_termino_atividade").toLocalDateTime());
				atividade.setDuracao(rs.getDouble("duracao"));
				atividade.setNomeResponsavel(rs.getString("nome_responsavel"));
				atividade.setEvento(eventoDaoJDBC.listarTodosPorId(rs.getInt("id_evento")));
				atividades.add(atividade);
			}
			return atividades;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
	}
}

/*
 * PreparedStatement st = null; ResultSet rs = null;
 * 
 * try { st = conn.
 * prepareStatement("SELECT * FROM atividade WHERE titulo_atividade LIKE '%" +
 * nome + "%'"); rs = st.executeQuery();
 * 
 * List<Atividade> atividades = new ArrayList<>(); Map<Integer, Evento> map =
 * new HashMap<>();
 * 
 * while (rs.next()) {
 * 
 * Evento evento = map.get(rs.getInt("id_evento"));
 * 
 * if (evento == null) { evento = instantiateEvento(rs);
 * map.put(rs.getInt("id_evento"), evento); }
 * 
 * Atividade atividade = instantiateAtividade(rs, evento);
 * atividades.add(atividade); } return atividades; } catch (SQLException e) {
 * throw new DbException(e.getMessage()); } } }
 */