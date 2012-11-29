package sheridan;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import sheridan.connectionmanager.ConnectionManager;

public class userDao {

	static Connection con;
	static Statement stmt;

	public static userbean regist(userbean user) { // TODO Auto-generated method
													// stub

		String username = user.getUserName();
		String pass = user.getPassword();
		String query = "insert into register values('" + username + "','"
				+ pass + "')";

		try {
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			int rows = stmt.executeUpdate(query);
			if (rows != 0) {
				user.setValid(true);
			} else
				user.setValid(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
