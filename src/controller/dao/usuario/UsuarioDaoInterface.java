package controller.dao.usuario;

import java.util.List;

import model.entities.usuario.Usuario;

public interface UsuarioDaoInterface {
	void editarUsuario(String numero_documento, Usuario usuario);
	void excluirUsuario(String numero_documento);
	List<Usuario> listarTodos(String nome_tabela);	
	List<Usuario> listarTodosPorDocumento(String nome_tabela, String numero_documento);	
	List<Usuario> listarTodosPorNome(String nome_tabela, String nome);	
}
