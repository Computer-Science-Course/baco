package controller.dao.certificado;

import java.util.List;

import model.entities.certificado.Certificado;

public interface CertificadoDaoInterface {

	List<Certificado> listarTodosPorAtividade(Integer id_atividade);
	List<Certificado> listarTodosPorParticipante(Integer id_participante);

}
