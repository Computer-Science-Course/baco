package controller.dao.participante;

import java.util.List;

import model.entities.participante.Participante;
import model.entities.usuario.Usuario;

public interface ParticipanteDaoInterface {
	void criarPartipante(Usuario usuario);
	void editarParticipante(String numero_documento, Usuario usuario);
	void excluirParticipante(String numero_documento, Usuario usuario);
	List<Participante> listarPorNome(String nome);
	List<Participante> listarTodosPorDocumento(String numero_documento);
	
}
