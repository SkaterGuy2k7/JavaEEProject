package sheridan;

import java.io.IOException;

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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			userbean user = new userbean();
			user.setUserName(request.getParameter("id"));
			user.setPassword(request.getParameter("pw"));
			user = userDao.regist(user);
			if (user.isValid()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("currentuser", user);
				response.sendRedirect("welcome.jsp");
			} else {
				System.out.println("sorry not inserted");
				response.sendRedirect("Invalid.jsp");
			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
