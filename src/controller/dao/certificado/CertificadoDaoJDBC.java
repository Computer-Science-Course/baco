package controller.dao.certificado;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import controller.dao.inscricao.InscricaoDaoJDBC;
import model.entities.certificado.Certificado;
import model.entities.inscricao.Inscricao;

public class CertificadoDaoJDBC implements CertificadoDaoInterface{
	
	private Connection conn;

	public CertificadoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 *
	 */
	@Override
	public List<Certificado> listarTodosPorAtividade(Integer id_atividade) {
		List<Certificado> certificados = new ArrayList<>();
		InscricaoDaoJDBC inscricaoDaoJDBC = new InscricaoDaoJDBC(conn);
		
		List<Inscricao> inscricoes = inscricaoDaoJDBC.listarTodasPorAtividade(id_atividade);
		for(Inscricao inscricao: inscricoes) {
			if(inscricao.getCheckin()) {
				Certificado certificado = new Certificado();
				certificado.setResponsavel(inscricao.getAtividade().getNomeResponsavel());
				certificado.setDataCertificado(inscricao.getAtividade().getDataInicio().toLocalDate());
				certificado.setTipoAtividade(inscricao.getAtividade().getTipoAtividade());
				certificado.setTitulo(inscricao.getAtividade().getTitulo());
				certificado.setTituloEvento(inscricao.getAtividade().getEvento().getNome());
				certificado.setDuracao(inscricao.getAtividade().getDuracao());
				certificado.setParticipante(inscricao.getParticipante().getNome());
				certificado.setCheckin(true);
				
				certificados.add(certificado);
			}
			
		}	
		return certificados;	 
	}
		
	
	
	@Override
	public List<Certificado> listarTodosPorParticipante(Integer id_participante) {
		List<Certificado> certificados = new ArrayList<>();
		InscricaoDaoJDBC inscricaoDaoJDBC = new InscricaoDaoJDBC(conn);
		
		List<Inscricao> inscricoes = inscricaoDaoJDBC.listarTodasPorParticipante(id_participante);
		for(Inscricao inscricao: inscricoes) {
			if(inscricao.getCheckin()) {
				Certificado certificado = new Certificado();
				certificado.setResponsavel(inscricao.getAtividade().getNomeResponsavel());
				certificado.setDataCertificado(inscricao.getAtividade().getDataInicio().toLocalDate());
				certificado.setTipoAtividade(inscricao.getAtividade().getTipoAtividade());
				certificado.setTitulo(inscricao.getAtividade().getTitulo());
				certificado.setTituloEvento(inscricao.getAtividade().getEvento().getNome());
				certificado.setDuracao(inscricao.getAtividade().getDuracao());
				certificado.setParticipante(inscricao.getParticipante().getNome());
				certificado.setCheckin(true);
				
				certificados.add(certificado);
			}

		}

		return certificados;
	}

}
