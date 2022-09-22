package controller.dao.participante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import controller.dao.evento.EventoDaoJDBC;
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
	public void editarParticipante(String numero_documento, Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluirParticipante(String numero_documento, Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Participante> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Participante> listarPorDocumento(String numero_documento) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Participante> listarPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
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
