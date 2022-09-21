package controller.dao.atividade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import model.entities.atividade.Atividade;
import model.entities.usuario.Usuario;
import model.service.DbException;

public class AtividadeDaoJDBC implements AtividadeDaoInterface{
	
	private Connection conn;

	public AtividadeDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void criarAtividade(Atividade atividade) {
		List<Atividade> atividades = this.listarTodosPorNome(atividade.getTitulo());
		if(atividades.size() == 0) {
			PreparedStatement statement = null;
			
			try {
				String query = "INSERT INTO atividade " +
						"(titulo, descricao, tipo, data_inicio, data_termino, duracao, nome_responsavel) " +
						"VALUES " +
						"(?, ?, ?, ?, ?, ?, ?)";
				
				statement = conn.prepareStatement(
					query, 
					Statement.RETURN_GENERATED_KEYS
				);

				statement.setString(1, atividade.getTitulo());
				statement.setString(2, atividade.getDescricao());
				statement.setString(3, atividade.getTipoAtividade().name());
				statement.setTimestamp(4, Timestamp.valueOf(atividade.getData_inicio()));
				statement.setTimestamp(5, Timestamp.valueOf(atividade.getData_termino()));
				statement.setDouble(6, atividade.getDuracao());
				statement.setString(7, atividade.getNome_responsavel());

				statement.executeUpdate();
				
			}
			catch (SQLException error) {
				throw new DbException(error.getMessage());
			}
		} else {
			System.out.println("[JÁ EXISTE UMA ATIVIDADE COM ESSE NOME, SEJA MAIS CRIATIVO!]");
		}
		
	}


	@Override
	public void editarAtividade(Atividade atividade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluirAtividade(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Atividade> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Atividade> listarTodosPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

}
