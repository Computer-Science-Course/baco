package controller.dao.evento;

import java.util.List;

import model.entities.evento.Evento;


public interface EventoDaoInterface {
	
	void criarEvento(Evento evento);
	void editarEvento(Evento evento);
	void excluirEvento(Integer id);
	List<Evento> listarTodos();	
	List<Evento> listarTodosPorNome(String nome);

}
