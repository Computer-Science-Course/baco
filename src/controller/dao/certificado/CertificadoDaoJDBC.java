package controller.dao.certificado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.dao.atividade.AtividadeDaoJDBC;
import controller.dao.evento.EventoDaoJDBC;
import controller.dao.inscricao.InscricaoDaoJDBC;
import model.entities.atividade.Atividade;
import model.entities.certificado.Certificado;
import model.entities.inscricao.Inscricao;
import model.enums.TipoAtividade;
import model.service.DbException;

public class CertificadoDaoJDBC implements CertificadoDaoInterface{
	
	private Connection conn;

	public CertificadoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Certificado> listarTodosPorAtividade(Integer id_atividade) {
		InscricaoDaoJDBC inscricaoDaoJDBC = new InscricaoDaoJDBC(conn);
		List<Certificado> certificados = new ArrayList<>();
		List<Inscricao> inscricoes = inscricaoDaoJDBC.listarTodasPorAtividade(id_atividade);
		PreparedStatement st = null;
		ResultSet rs = null;
		
		/*
		 * try { st = conn.prepareStatement( "SELECT * FROM inscricao " + "WHERE id = "
		 * + id_atividade); rs = st.executeQuery();
		 * 
		 * }
		 */
	}
		
	
	
	
	
	
	
	@Override
	public List<Certificado> listarTodosPorParticipante(Integer id_participante) {
		// TODO Auto-generated method stub
		return null;
	}

}
