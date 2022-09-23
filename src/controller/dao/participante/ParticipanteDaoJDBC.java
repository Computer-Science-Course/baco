package controller.dao.participante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import controller.dao.evento.EventoDaoJDBC;
import controller.dao.genericDb.GenericDbJDBC;
import model.entities.atividade.Atividade;
import model.entities.participante.Participante;
import model.entities.usuario.Usuario;
import model.enums.TipoAtividade;
import model.enums.TipoDocumento;
import model.service.DbException;

public class ParticipanteDaoJDBC implements ParticipanteDaoInterface {
	private Connection conn;
	
	public ParticipanteDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void criarParticipante(Participante participante) {
		PreparedStatement statement = null;
		
		try {
			String query = "INSERT INTO participante " +
					"(nome, numero_documento, tipo_documento) " +
					"VALUES " +
					"(?, ?, ?)";
			
			statement = conn.prepareStatement(
				query, 
				Statement.RETURN_GENERATED_KEYS
			);

			statement.setString(1, participante.getNome());
			statement.setString(2, participante.getNumeroDocumento());
			statement.setString(3, participante.getTipoDocumento().name());

			statement.executeUpdate();		
		}
		catch (SQLException error) {
			throw new DbException(error.getMessage());
		}
		
	}

	@Override
	public void editarParticipante(Participante participante) {
		PreparedStatement statement = null;
		try {			
			String query = "UPDATE participante " +
						 "SET " +
						 "nome =  ?, " +
						 "numero_documento =  ?, " +
						 "tipo_documento =  ? " +
						 "WHERE " +
						 "(id = " + participante.getId() + ")";
			
			statement = conn.prepareStatement(query);
			statement.setString(1, participante.getNome());
			statement.setString(2, participante.getNumeroDocumento());
			statement.setString(3, participante.getTipoDocumento().name());
			
			statement.executeUpdate();			
			
		}
		catch (SQLException error) {
			error.printStackTrace();
		}
		
	}

	@Override
	public void excluirParticipante(Integer id) {
		GenericDbJDBC genericDbJDBC = new GenericDbJDBC(conn);
		
		genericDbJDBC.deleteWhere("participante", "id", id.toString());
		
	}
	
	@Override
	public List<Participante> listarTodos() {
		List<Participante> participantes = new ArrayList<>();
				
		try {
			String query = "SELECT * FROM participante";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				
				Participante participante = new Participante(
						result.getString("nome"),
						result.getString("numero_documento"),
						TipoDocumento.valueOf(result.getString("tipo_documento"))
				);
				
				participante.setId(result.getInt("id"));
				
				participantes.add(participante);
			}
			
			return participantes;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	@Override
	public List<Participante> listarPorDocumento(String numero_documento) {
		List<Participante> participantes = new ArrayList<>();
		
		try {
			String query = "SELECT * FROM participante " +
							"WHERE numero_documento = " + numero_documento;
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				
				Participante participante = new Participante(
						result.getString("nome"),
						result.getString("numero_documento"),
						TipoDocumento.valueOf(result.getString("tipo_documento"))
				);
				
				participante.setId(result.getInt("id"));
				
				participantes.add(participante);
			}
			
			return participantes;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	@Override
	public List<Participante> listarPorNome(String nome) {
		List<Participante> participantes = new ArrayList<>();
		
		try {
			String query = "SELECT * FROM participante " +
							"WHERE nome LIKE '%" + nome + "%'";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				
				Participante participante = new Participante(
						result.getString("nome"),
						result.getString("numero_documento"),
						TipoDocumento.valueOf(result.getString("tipo_documento"))
				);
				
				participante.setId(result.getInt("id"));
				
				participantes.add(participante);
			}
			
			return participantes;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public Participante listarPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * FROM participante " 
					+ "WHERE id = " + id);
			rs = st.executeQuery();
			
			while (rs.next()) {
				Participante participante = new Participante();

				participante.setId(id);
				participante.setNome(rs.getString("nome"));
				participante.setTipoDocumento(TipoDocumento.valueOf(rs.getString("tipo_documento")));
				participante.setNumeroDocumento(rs.getString("numero_documento"));
				
				return participante;
			}
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
}
