package cybersoft.java12.crmapp.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	private static Connection connection;
	
	private static String URL ="jdbc:mysql://localhost:3306/crm";
	private static String USERNAME ="root";
	private static String PASSWORD = "@@123Lo456Ng";
	
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if (connection==null||connection.isClosed())
				 return DriverManager.getConnection(URL, USERNAME, PASSWORD);
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Can not find MySQL DB Driver!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Can not connect to database due to : invalid url of invalid username of invalid password");
		}
		
		return null;
	}
}
