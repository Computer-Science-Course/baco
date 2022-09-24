package controller.dao.certificado;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import controller.dao.inscricao.InscricaoDaoJDBC;
import model.entities.atividade.Atividade;
import model.entities.certificado.Certificado;
import model.entities.inscricao.Inscricao;

public class CertificadoDaoJDBC implements CertificadoDaoInterface{
	
	private Connection conn;

	public CertificadoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Certificado> listarTodosPorAtividade(Integer id_atividade) {
		List<Certificado> certificados = new ArrayList<>();
		InscricaoDaoJDBC inscricaoDaoJDBC = new InscricaoDaoJDBC(conn);
		
		List<Inscricao> inscricoes = inscricaoDaoJDBC.listarTodasPorAtividade(id_atividade);
		for(Inscricao obj: inscricoes) {
			if(obj.getCheckin()==true) {
				System.out.println(obj);
				
			}
			
		}
		
		return null;
		
		 
	}
		
	
	
	
	
	
	
	@Override
	public List<Certificado> listarTodosPorParticipante(Integer id_participante) {
		// TODO Auto-generated method stub
		return null;
	}

}
