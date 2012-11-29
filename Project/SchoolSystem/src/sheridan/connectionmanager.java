package sheridan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionmanager {

	public static class ConnectionManager {
		static Connection con;
		String url;

		public static Connection getConnection() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println(" Defing the URL");
				String url = "jdbc:derby://localhost:1527/sun-appserv-samples;create=true;upgrade=true";
				// assuming "DataSource" is your DataSource name
				// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				try {
					System.out.println(" Defing the username");
					String username = "e5";
					System.out.println(" Defing the password");
					String password = "";
					System.out.println(" Defing the connection");
					con = DriverManager.getConnection(url, username, password);
					System.out.println(" connection done" + con);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			}
			return con;
		}
	}
}
