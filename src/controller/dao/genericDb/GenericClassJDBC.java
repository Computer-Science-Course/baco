package controller.dao.genericDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.service.DbException;

public class GenericClassJDBC implements GenericDbInterface{

	private Connection conn;
	
	public GenericClassJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public ResultSet listAll(String tableName) {
		try {
			String query = "SELECT * FROM " + tableName;
			
			Statement statement = conn.createStatement();
;
			return statement.executeQuery(query);
		}
		catch (SQLException error) {
			throw new DbException(error.getMessage());
		}	
	}

	@Override
	public ResultSet listAllWhere(String tableName, String key, String value) {
		try {
			String query = "SELECT * FROM " + tableName +
							" WHERE " + key + " = " + value;
			
			Statement statement = conn.createStatement();
;
			return statement.executeQuery(query);
		}
		catch (SQLException error) {
			throw new DbException(error.getMessage());
		}	
	}

	@Override
	public void deleteWhere(String tableName, String key, String value) {
		
		PreparedStatement statement = null;
		try {
			String query = "DELETE FROM " + tableName +
					" WHERE " + key + " = ?";
			statement = conn.prepareStatement(query);
			statement.setString(1,value);
			
			statement.executeUpdate();
		}
		catch (SQLException error) {
			throw new DbException(error.getMessage());
		}
	}

}
