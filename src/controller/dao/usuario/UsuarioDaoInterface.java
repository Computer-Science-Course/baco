package controller.dao.usuario;

import java.util.List;

import model.entities.usuario.Usuario;

public interface UsuarioDaoInterface {
	void criarUsuario(Usuario usuario);
	void editarUsuario(String numero_documento, Usuario usuario);
	void editarSenhaUsuario(String numero_documento, Usuario usuario);
	void excluirUsuario(String numero_documento);
	List<Usuario> listarTodos(Boolean isAdm);	
	List<Usuario> listarTodosPorDocumento(Boolean isAdm, String numero_documento);	
	List<Usuario> listarTodosPorNome(Boolean isAdm, String nome_tabela, String nome);	
}
