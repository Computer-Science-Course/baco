package controller.dao.atividade;

import java.util.List;

import model.entities.atividade.Atividade;

public interface AtividadeDaoInterface {
	
	void criarAtividade(Atividade atividade);
	void editarAtividade(Atividade atividade);
	void excluirAtividade(Integer id);
	List<Atividade> listarTodos();
	List<Atividade> listarTodosPorNome(String nome);
	
}
