package controller.dao.inscricao;

import java.util.List;

import model.entities.inscricao.Inscricao;

public interface InscricaoDaoInterface {
	List<Inscricao> listarTodasPorAtividade(Integer id_atividade);
	List<Inscricao> listarTodasPorParticipante(Integer id_participante);
}
