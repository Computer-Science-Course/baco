package controller.dao.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import controller.dao.genericDb.GenericDbJDBC;
import model.entities.usuario.Usuario;
import model.enums.TipoDocumento;
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
		GenericDbJDBC genericDbJDBC = new GenericDbJDBC(conn);
		
		genericDbJDBC.deleteWhere("usuario", "numero_documento", numero_documento);	
		
	}

	@Override
	public List<Usuario> listarTodos(String nome_tabela) {
		GenericDbJDBC genericDbJDBC = new GenericDbJDBC(conn);
		List<Usuario> usuarios = new ArrayList<>();
		
		DateTimeFormatter formatterWithHour = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		try {
;
			ResultSet result = genericDbJDBC.listAll("usuario");
			
			while(result.next()) {
				LocalDateTime ultimo_acesso = result.getString("ultimo_acesso") == null ?
						null : LocalDateTime.parse(result.getString("ultimo_acesso"), formatterWithHour);
				
				Usuario usuario = new Usuario(
						result.getString("numero_documento"),
						TipoDocumento.valueOf(result.getString("tipo_documento")),
						result.getString("nome_completo"),
						null,
						result.getBoolean("isAdm"),
						ultimo_acesso
				);
				
				usuario.setId(result.getInt("id"));
				
				usuarios.add(usuario);
			}
			
			return usuarios;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
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
