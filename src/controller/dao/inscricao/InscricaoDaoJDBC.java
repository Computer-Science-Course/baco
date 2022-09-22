package controller.dao.inscricao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.dao.atividade.AtividadeDaoJDBC;
import controller.dao.participante.ParticipanteDaoJDBC;
import model.entities.atividade.Atividade;
import model.entities.inscricao.Inscricao;
import model.entities.participante.Participante;
import model.service.DbException;

public class InscricaoDaoJDBC implements InscricaoDaoInterface{

	private Connection conn;
	
	public InscricaoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Inscricao> listarTodasPorAtividade(Integer id_atividade) {
		List<Inscricao> inscricoes = new ArrayList<>();
		try {
			
			String query = "SELECT * FROM inscricao " +
						"WHERE id_atividade = " + id_atividade;
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(query);
			
			AtividadeDaoJDBC atividadeDaoJDBC = new AtividadeDaoJDBC(conn);
			ParticipanteDaoJDBC participanteDaoJDBC = new ParticipanteDaoJDBC(conn);
			
			Atividade atividade = atividadeDaoJDBC.listarTodosPorId(id_atividade);
			while(result.next()) {
				Participante participante = participanteDaoJDBC.listarPorId(result.getInt("id_participante"));
				inscricoes.add(
						new Inscricao(
							participante,
							atividade,
							result.getBoolean("checkin")
						)	
				);
				
			}
			
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Inscricao> listarTodasPorParticipante(Integer id_participante) {
		List<Inscricao> inscricoes = new ArrayList<>();
		try {
			
			String query = "SELECT * FROM inscricao " +
						"WHERE id_participante = " + id_participante;
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(query);
			
			AtividadeDaoJDBC atividadeDaoJDBC = new AtividadeDaoJDBC(conn);
			ParticipanteDaoJDBC participanteDaoJDBC = new ParticipanteDaoJDBC(conn);
			
			Participante participante = participanteDaoJDBC.listarPorId(id_participante);
			while(result.next()) {
				Atividade atividade = atividadeDaoJDBC.listarTodosPorId(result.getInt("id_atividade"));
				inscricoes.add(
						new Inscricao(
							participante,
							atividade,
							result.getBoolean("checkin")
						)	
				);
				
			}
			
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
}
