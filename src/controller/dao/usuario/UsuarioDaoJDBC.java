package controller.dao.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public void criarUsuario(Usuario usuario) {
		List<Usuario> usuarios = this.listarTodosPorDocumento(usuario.isAdm(), usuario.getNumeroDocumento());
		if(usuarios.size() == 0) {
			PreparedStatement statement = null;
			
			try {
				String query = "INSERT INTO usuario " +
						"(numero_documento, tipo_documento, nome_completo, senha, isAdm) " +
						"VALUES " +
						"(?, ?, ?, ?, ?)";
				
				statement = conn.prepareStatement(
					query, 
					Statement.RETURN_GENERATED_KEYS
				);

				statement.setString(1, usuario.getNumeroDocumento());
				statement.setString(2, usuario.getTipoDocumento().name());
				statement.setString(3, usuario.getNomeCompleto());
				statement.setString(4, usuario.getSenha());
				statement.setBoolean(5, usuario.isAdm());

				statement.executeUpdate();
				
			}
			catch (SQLException error) {
				throw new DbException(error.getMessage());
			}
		} else {
			System.out.println("[JÁ EXISTE UM USÁRIO COM ESSE DOCUMENTO]");
		}
		
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
	public void editarSenhaUsuario(String numero_documento, Usuario usuario) {
		PreparedStatement statement = null;
		try {			
			String query = "UPDATE usuario " +
					"SET " +
					"senha =  ? " +
					"WHERE " +
					"(numero_documento = " + numero_documento + ")";
			
			statement = conn.prepareStatement(query);
			statement.setString(1, usuario.getSenha());
			
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
	public List<Usuario> listarTodos(Boolean isAdm) {
		List<Usuario> usuarios = new ArrayList<>();
		
		DateTimeFormatter formatterWithHour = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		try {
			String query = "SELECT * FROM usuario WHERE isAdm = " + isAdm;
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(query);
			
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
	public List<Usuario> listarTodosPorDocumento(Boolean isAdm, String numero_documento) {
		List<Usuario> usuarios = new ArrayList<>();
		
		DateTimeFormatter formatterWithHour = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		try {
			
			String query = "SELECT * FROM usuario " +
						"WHERE isAdm = " + isAdm + 
						" AND numero_documento = " + numero_documento;
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(query);
			
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
	public List<Usuario> listarTodosPorNome(Boolean isAdm, String nome) {
		List<Usuario> usuarios = new ArrayList<>();
		
		DateTimeFormatter formatterWithHour = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		try {
			
			String query = "SELECT * FROM usuario " +
						"WHERE isAdm = " + isAdm + 
						" AND nome_completo LIKE '%" + nome + "%'";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(query);
			
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

}
