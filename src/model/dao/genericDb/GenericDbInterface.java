package model.dao.genericDb;
import java.sql.ResultSet;

public interface GenericDbInterface {
	ResultSet listAll(String tableName);
	ResultSet listAllWhere(String tableName, String key, String value);
	void deleteWhere(String tableName, String key, String value);
}
