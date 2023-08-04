package iNeuron;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JavaUtil {

	private JavaUtil() {
		
	}
	
	public static Connection getJdbcConnection() throws SQLException{
		
		String url="jdbc:mysql://localhost:3306/nathan_sir";
		String username="root";
		String password="Kritesh21@";
		
		Connection con=null;
	
		con=DriverManager.getConnection(url, username, password);
		if(con!=null)
			return con;
		
		return con;
		
	}
	
	public static void closeConnection(ResultSet rs,PreparedStatement ps,Connection con) throws SQLException {
		if(rs!=null)
			rs.close();
		if(ps!=null)
			ps.close();
		if(con!=null)
			con.close();
	}
}
