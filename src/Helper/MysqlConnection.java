package Helper;
import java.sql.*;

public class MysqlConnection {
	
	private static MysqlConnection instant;
	
	private Connection c = null;
	
	private MysqlConnection() throws SQLException {
		this.c = DriverManager.getConnection("jdbc:mySql://localhost:3306/hospital?user=root&password=12345678");
	}
	
	public static Connection connMysql() throws SQLException {
		if (instant == null) instant = new MysqlConnection();
		
		return instant.c;
		
	}
}
