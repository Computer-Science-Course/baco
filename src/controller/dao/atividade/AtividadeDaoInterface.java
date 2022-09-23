package controller.dao.atividade;

import java.util.List;

import model.entities.atividade.Atividade;
import model.entities.participante.Participante;

public interface AtividadeDaoInterface {
	
	void criarAtividade(Atividade atividade);
	void editarAtividade(Atividade atividade);
	void excluirAtividade(Integer id);
	void inscricao(Participante participante, Atividade atividade);
	List<Atividade> listarTodos();
	List<Atividade> listarTodosPorNome(String nome);
	Atividade listarTodosPorId(Integer id);
}
