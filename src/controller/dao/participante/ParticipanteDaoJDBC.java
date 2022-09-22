package controller.dao.participante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.entities.participante.Participante;
import model.entities.usuario.Usuario;

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

	
	


	
	
	
	
}
