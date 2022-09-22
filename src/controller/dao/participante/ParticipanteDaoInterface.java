package controller.dao.participante;

import java.util.List;

import model.entities.participante.Participante;
import model.entities.usuario.Usuario;

public interface ParticipanteDaoInterface {
	void editarParticipante(String numero_documento, Usuario usuario);
	void excluirParticipante(String numero_documento, Usuario usuario);
	List<Participante> listarTodos();
	List<Participante> listarPorDocumento(String numero_documento);
	List<Participante> listarPorNome(String nome);
	Participante listarPorId(Integer id);
	
	
}
