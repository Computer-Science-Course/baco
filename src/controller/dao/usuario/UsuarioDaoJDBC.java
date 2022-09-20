package controller.dao.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.entities.usuario.Usuario;
import model.service.DbException;

public class UsuarioDaoJDBC implements UsuarioDaoInterface{

	private Connection conn;
	
	public UsuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void editarUsuario(String numero_documento, Usuario usuario) {
		PreparedStatement statement = null;
		try {			
			String query = "UPDATE usuario " +
						 "SET " +
						 "numero_documento =  ?, " +
						 "tipo_documento =  ?, " +
						 "nome_completo =  ? " +
						 "WHERE " +
						 "(numero_documento = " + numero_documento + ")";
			
			statement = conn.prepareStatement(query);
			statement.setString(1, usuario.getNumeroDocumento());
			statement.setString(2, usuario.getTipoDocumento().name());
			statement.setString(3, usuario.getNomeCompleto());
			
			statement.executeUpdate();			
			
		}
		catch (SQLException error) {
			error.printStackTrace();
		}
		
	}

	@Override
	public void excluirUsuario(String numero_documento) {
		PreparedStatement statement = null;
		
		try {
			
			String query = "DELETE FROM usuario " +
							"WHERE " +
							"numero_documento = ?";
			
			statement = conn.prepareStatement(query);
			statement.setString(1, numero_documento);
			
			statement.executeUpdate();
		}
		catch (SQLException error) {
			throw new DbException(error.getMessage());
		}	
		
	}

	@Override
	public List<Usuario> listarTodos(String nome_tabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listarTodosPorDocumento(String nome_tabela, String numero_documento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listarTodosPorNome(String nome_tabela, String nome) {
		// TODO Auto-generated method stub
		return null;
	}

}
