package sheridan;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class Regservlet
 */
@WebServlet("/Regservlet")
public class Regservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Regservlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();		

		// LOOK FOR BUTTON CLICKED IN IF STATEMENT
		if (request.getParameter("login") != null) {
			session.setAttribute("user", null);
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");

			Student s = new Student();
			Professor p = new Professor();

			try {
				String connectionURL = "jdbc:derby://localhost:1527/student;create=true";
				Connection conn = null;
				ResultSet rs;
				conn = DriverManager.getConnection(connectionURL);
				Statement statement = conn.createStatement();

				String sql = "SELECT * FROM Student WHERE username='" + user
						+ "' AND password='" + pass + "'";

				rs = statement.executeQuery(sql);
				rs.next();

				if (rs.getString("username").equals(user) && rs.getString("password").equals(pass)) {					
					s.setStudid(rs.getInt("studid"));
					s.setFirstName(rs.getString("firstname"));
					s.setLastName(rs.getString("lastname"));
					s.setEmail(rs.getString("email"));
					s.setUser(rs.getString("username"));
					s.setPass(rs.getString("password"));
					s.setProgId(rs.getInt("progId"));
					s.setCourse1(rs.getString("course1"));
					s.setCourse2(rs.getString("course2"));
					s.setCourse3(rs.getString("course3"));
					s.setCourse4(rs.getString("course4"));
					s.setCourse5(rs.getString("course5"));
					
					session.setAttribute("student", s);
					response.sendRedirect("http://localhost:8080/SchoolSystem/stud_view.jsp");							
//				else{
//					String sql = "SELECT * FROM Professor WHERE username='" + user
//							+ "' AND password='" + pass + "'";
//					
//					rs = statement.executeQuery(sql);
//					rs.next();									
//					
//					if (rs.getString("username").equals(user)
//							&& rs.getString("password").equals(pass)) {						
//						p.setId(rs.getInt("profid"));
//						p.setFirstName(rs.getString("firstname"));
//						p.setLastName(rs.getString("lastname"));
//						p.setEmail(rs.getString("email"));
//						p.setUser(rs.getString("username"));
//						p.setPass(rs.getString("password"));
//						
//						session.setAttribute("professor", p);
//						response.sendRedirect("http://localhost:8080/SchoolSystem/prof_view.jsp");
					}								
			} catch (ArrayIndexOutOfBoundsException e) {
				response.sendRedirect("http://localhost:8080/SchoolSystem/reg.jsp");
			} catch (IllegalStateException e) {
				out.print(e.getMessage());
			} catch (SQLException e) {
				session.setAttribute("error",
						"Username or password is incorrect!");
				response.sendRedirect("http://localhost:8080/SchoolSystem/reg.jsp");				
			}
									
		} else if (request.getParameter("register") != null) {
			response.sendRedirect("createStud.jsp");
		}

		// OLD CODE
		// try {
		// userbean user = new userbean();
		// user.setUserName(request.getParameter("id"));
		// user.setPassword(request.getParameter("pw"));
		// user = userDao.regist(user);
		// if (user.isValid()) {
		// HttpSession session = request.getSession(true);
		// session.setAttribute("currentuser", user);
		// response.sendRedirect("welcome.jsp");
		// } else {
		// System.out.println("sorry not inserted");
		// response.sendRedirect("Invalid.jsp");
		// }
		// } catch (Throwable theException) {
		// System.out.println(theException);
		// }
	}
}
