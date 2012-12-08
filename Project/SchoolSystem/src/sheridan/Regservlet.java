package sheridan;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String courseName = request.getParameter("courseName");

		if (null != courseName) {
			try {
				String connectionURL = "jdbc:derby://localhost:1527/student;create=true";
				Connection conn = null;
				ResultSet rs;
				conn = DriverManager.getConnection(connectionURL);
				Statement statement = conn.createStatement();
				ArrayList<Material> materials = new ArrayList<Material>();

				String sql = "SELECT courseId FROM Course WHERE courseName='"
						+ courseName + "'";
				rs = statement.executeQuery(sql);
				rs.next();

				String courseId = rs.getString("courseId");

				sql = "SELECT * FROM Material WHERE courseId=" + courseId + "";
				rs = statement.executeQuery(sql);

				while (rs.next()) {
					Material m = new Material();

					m.setCourseId(rs.getInt("courseId"));
					m.setMatId(rs.getInt("matId"));
					m.setStudId(rs.getInt("studId"));
					m.setGrade(rs.getString("grade"));
					m.setMatName(rs.getString("matName"));
					m.setMatType(rs.getString("matType"));
					m.setMatWeight(rs.getString("matWeight"));

					materials.add(m);
				}
				session.setAttribute("materials", materials);
				response.sendRedirect("add_edit_weight.jsp");
			} catch (SQLException e) {
				System.out.print(e.getMessage());
			}
		}

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
		// LOGIN BUTTON
		if (request.getParameter("login") != null) {
			// clear the materials attribute
			session.setAttribute("materials", null);
			ArrayList<Course> courses = new ArrayList<Course>();
			ArrayList<Material> materials = new ArrayList<Material>();
			Student s = new Student();
			Professor p = new Professor();
			session.setAttribute("user", null);
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");

			try {
				String connectionURL = "jdbc:derby://localhost:1527/student;create=true";
				Connection conn = null;
				ResultSet studrs;
				ResultSet profrs;
				conn = DriverManager.getConnection(connectionURL);
				Statement studStatement = conn.createStatement();
				Statement profStatement = conn.createStatement();

				String sql = "SELECT * FROM Student WHERE username='" + user
						+ "' AND password='" + pass + "'";

				studrs = studStatement.executeQuery(sql);
				// STUDENT LOGIN
				if (studrs.next()) {
					s.setStudid(studrs.getInt("studid"));
					s.setFirstName(studrs.getString("firstname"));
					s.setLastName(studrs.getString("lastname"));
					s.setEmail(studrs.getString("email"));
					s.setUser(studrs.getString("username"));
					s.setPass(studrs.getString("password"));
					s.setProgId(studrs.getInt("progId"));

					session.setAttribute("student", s);

					// ADDING COURSES TO stud_view
					sql = "SELECT * FROM Course WHERE progId=" + s.getProgId();

					studrs = studStatement.executeQuery(sql);

					courses = new ArrayList<Course>();
					while (studrs.next()) {
						Course c = new Course();

						c.setCourseId(studrs.getInt("courseId"));
						c.setProgId(studrs.getInt("progId"));
						c.setProfId(studrs.getInt("profId"));
						c.setCourseName(studrs.getString("courseName"));
						c.setCourseTime(studrs.getString("courseTime"));
						c.setCourseCode(studrs.getString("courseCode"));
						c.setRoomNum(studrs.getString("roomnum"));
						int profId = studrs.getInt("profId");
						sql = "SELECT profId, firstName, lastName FROM Professor WHERE profId="
								+ studrs.getInt("profId");
						profrs = profStatement.executeQuery(sql);
						while (profrs.next()) {
							if (profId == profrs.getInt("profId")) {
								c.setProfName(profrs.getString("firstName")
										+ " " + profrs.getString("lastName"));
							}
						}

						courses.add(c);
					}

					session.setAttribute("courses", courses);
					// END ADDING COURSES TO stud_view

					// ADD MATERIALS OF COURSES TO stud_view
					sql = "SELECT * FROM Material";

					studrs = profStatement.executeQuery(sql);

					while (studrs.next()) {
						Material m = new Material();

						m.setCourseId(studrs.getInt("courseId"));
						m.setMatId(studrs.getInt("matId"));
						m.setStudId(studrs.getInt("studId"));
						m.setGrade(studrs.getString("grade"));
						m.setMatName(studrs.getString("matName"));
						m.setMatType(studrs.getString("matType"));
						m.setMatWeight(studrs.getString("matWeight"));

						materials.add(m);
					}
					session.setAttribute("materials", materials);
					// END ADDING MATERIAL TO stud_view

					response.sendRedirect("stud_view.jsp");
					// PROFESSOR LOGIN
				} else {
					sql = "SELECT * FROM Professor WHERE username='" + user
							+ "' AND password='" + pass + "'";
					profrs = profStatement.executeQuery(sql);
					if (profrs.next()) {
						p.setId(profrs.getInt("profid"));
						p.setFirstName(profrs.getString("firstname"));
						p.setLastName(profrs.getString("lastname"));
						p.setEmail(profrs.getString("email"));
						p.setUser(profrs.getString("username"));
						p.setPass(profrs.getString("password"));

						session.setAttribute("professor", p);

						sql = "SELECT * FROM Course WHERE profId=" + p.getId();

						profrs = profStatement.executeQuery(sql);

						while (profrs.next()) {
							Course c = new Course();

							c.setCourseId(profrs.getInt("courseId"));
							c.setProgId(profrs.getInt("progId"));
							c.setProfId(profrs.getInt("profId"));
							c.setCourseName(profrs.getString("courseName"));
							c.setCourseTime(profrs.getString("courseTime"));
							c.setCourseCode(profrs.getString("courseCode"));
							c.setRoomNum(profrs.getString("roomnum"));

							courses.add(c);
						}
						profrs.close();

						sql = "SELECT * FROM Material";

						profrs = profStatement.executeQuery(sql);

						while (profrs.next()) {
							Material m = new Material();

							m.setCourseId(profrs.getInt("courseId"));
							m.setMatId(profrs.getInt("matId"));
							m.setStudId(profrs.getInt("studId"));
							m.setGrade(profrs.getString("grade"));
							m.setMatName(profrs.getString("matName"));
							m.setMatType(profrs.getString("matType"));
							m.setMatWeight(profrs.getString("matWeight"));

							materials.add(m);
						}
						session.setAttribute("materials", materials);
						session.setAttribute("courses", courses);
						response.sendRedirect("prof_view.jsp");
						// FAILED LOGIN
					} else {
						// POP UP MESSAGE TELLING THE USER LOGIN INFORMATION WAS
						// INCORRECT
						response.setContentType("text/html");
						out.println("<script type=\"text/javascript\">");
						out.println("var con = confirm('Login information was incorrect.');");
						out.println("if(con == true){");
						out.println("document.location.href=\"http://localhost:8080/SchoolSystem/reg.jsp\"");
						out.println("} else {");
						out.println("document.location.href=\"http://localhost:8080/SchoolSystem/reg.jsp\" }");
						out.println("</script>");
						// END OF POP UP MESSAGE
					}
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
			session.setAttribute("stud", null);
			response.sendRedirect("createStud.jsp");
		} else if (request.getParameter("materialPage") != null) {
			String courseName = request.getParameter("matCourseName");
			System.out.println("Course: " + courseName);
			response.sendRedirect("add_edit_material.jsp");

		} else if (request.getParameter("addMaterial") != null) {
			String matName = "";
			String matWeight = "";
			HashMap<String, String> errors = new HashMap<String, String>();

			if (request.getParameter("newName").equals(""))
				errors.put("errors", "Please enter in a Material Name");
			else
				matName = request.getParameter("newName");

			if (request.getParameter("newWeight").equals(""))
				errors.put("errors", "Please enter in a Material Weight");
			else
				matWeight = request.getParameter("newWeight");

			String courseName = request.getParameter("courseList");
			String matType = request.getParameter("newType");

			try {
				String connectionURL = "jdbc:derby://localhost:1527/student;create=true";
				Connection conn = null;
				ResultSet rs;
				conn = DriverManager.getConnection(connectionURL);
				Statement statement = conn.createStatement();

				String sql = "SELECT courseId FROM Course WHERE courseName='"
						+ courseName + "'";

				rs = statement.executeQuery(sql);
				rs.next();

				String courseId = rs.getString("courseId");

				sql = "SELECT s.studid FROM Student s, Program p, Course c WHERE s.progId=p.progId AND p.progId=c.progId AND c.courseId="
						+ courseId + "";

				rs = statement.executeQuery(sql);
				rs.next();
				String studId = rs.getString("studId");

				sql = "INSERT INTO Material(studId,courseId,matWeight,matName,matType) VALUES("
						+ ""
						+ studId
						+ ","
						+ ""
						+ courseId
						+ ","
						+ "'"
						+ matWeight
						+ "',"
						+ "'"
						+ matName
						+ "',"
						+ "'"
						+ matType + "')";

				statement.executeUpdate(sql);

				ArrayList<Material> materials = new ArrayList<Material>();
				// Refreshing the course list
				sql = "SELECT * FROM Material";

				rs = statement.executeQuery(sql);

				while (rs.next()) {
					Material m = new Material();

					m.setCourseId(rs.getInt("courseId"));
					m.setMatId(rs.getInt("matId"));
					m.setStudId(rs.getInt("studId"));
					m.setGrade(rs.getString("grade"));
					m.setMatName(rs.getString("matName"));
					m.setMatType(rs.getString("matType"));
					m.setMatWeight(rs.getString("matWeight"));

					materials.add(m);
				}
				session.setAttribute("materials", materials);
				response.sendRedirect("prof_view.jsp");
			} catch (SQLException e) {
				System.out.print(e.getMessage());
			}

		} else if (request.getParameter("addGrade") != null) { // addGrade is a
																// button from
																// Student view
			// SEND TO add_edit_grade.jsp
			session.setAttribute("selectedCourse", null);
			response.sendRedirect("add_edit_grade.jsp");

		} else if (request.getParameter("createStud") != null) {
			// VALIDATION FOR STUDENT INFO
			session.setAttribute("errors", null);
			Map<String, String> errors = new HashMap<String, String>(6);
			Student s = new Student();

			try {
				// SQL STUFF TO CHECK USERNAMES AGAINST OTHER NAMES IN THE
				// DATABASE
				String connectionURL = "jdbc:derby://localhost:1527/student;create=true";
				Connection conn = null;
				ResultSet rs;
				conn = DriverManager.getConnection(connectionURL);
				Statement statement = conn.createStatement();

				String sql = "";
				// END OF SQL DECLARATIONS

				// VALIDATE FIRST NAME
				if (request.getParameter("firstName").equals(""))
					errors.put("firstNError", "Please enter a first name");
				else
					s.setFirstName(request.getParameter("firstName"));
				// VALIDATE LAST NAME
				if (request.getParameter("lastName").equals(""))
					errors.put("lastNError", "Please enter a last name");
				else
					s.setLastName(request.getParameter("lastName"));
				// VALIDATE Email
				if (request.getParameter("email").equals(""))
					errors.put("emailError", "Please enter a email");
				else
					s.setEmail(request.getParameter("email"));
				// VALIDATE Username
				if (request.getParameter("user").equals(""))
					errors.put("userError", "Please enter a username");
				else
					s.setUser(request.getParameter("user"));
				// VALIDATE PASSWORD
				if (request.getParameter("pass").equals(""))
					errors.put("passError", "Please enter a password");
				else
					s.setPass(request.getParameter("pass"));
				// VALIDATE RE PASS
				if (request.getParameter("repass").equals("")
						|| !request.getParameter("repass").equals(
								request.getParameter("pass")))
					errors.put("repassError", "Passwords do not match");
				// VALIDATE PROGRAM COMBOBOX
				if (request.getParameter("programs").equals(
						"Select a program..."))
					errors.put("progError", "Please select a program.");
				else {
					String progName = request.getParameter("programs");
					int index = Integer.parseInt(progName.substring(0, 1));
					s.setProgId(index);
				}
				// END VALIDATION

				if (!errors.isEmpty()) {
					session.setAttribute("stud", s);
					session.setAttribute("errors", errors);
					response.sendRedirect("createStud.jsp");
				} else {
					// ADD NEW STUDENT TO DATABASE
					sql = "INSERT INTO Student (firstName, lastName, email, username, password, progId) VALUES ('"
							+ s.getFirstName()
							+ "', '"
							+ s.getLastName()
							+ "', '"
							+ s.getEmail()
							+ "', '"
							+ s.getUser()
							+ "', '"
							+ s.getPass()
							+ "', "
							+ s.getProgId()
							+ ")";
					statement.executeUpdate(sql);

					statement.close();
					conn.close();
					// END OF ADDING NEW STUDENT TO DATABASE

					// THIS BIT SENDS A POP TO TELL THE USER A NEW STUDENT WAS
					// MADE
					response.setContentType("text/html");
					out.println("<script type=\"text/javascript\">");
					out.println("var con = confirm('New Student has been created!');");
					out.println("if(con == true){");
					out.println("document.location.href=\"http://localhost:8080/SchoolSystem/reg.jsp\"");
					out.println("} else {");
					out.println("document.location.href=\"http://localhost:8080/SchoolSystem/reg.jsp\" }");
					out.println("</script>");
					// END OF POP UP BIT
				}
			} catch (SQLException e) {
				System.out
						.println("ADDING NEW STUDENT TO DATABASE - SQLEXCEPTION - "
								+ e.getMessage());
			}

		} else if (request.getParameter("weightPage") != null) {
			response.sendRedirect("add_edit_weight.jsp");

		} else if (request.getParameter("addWeight") != null) {
			// Not Working
			out.println("OUT OF SERVICE");
		} else if (request.getParameter("btnGrade") != null) {
			try {
				String connectionURL = "jdbc:derby://localhost:1527/student;create=true";
				Connection conn = null;
				ResultSet rs;
				conn = DriverManager.getConnection(connectionURL);
				Statement statement = conn.createStatement();

				int grade = Integer.parseInt(request.getParameter("newGrade"));

				String sql = "SELECT courseId FROM Course WHERE courseName='"
						+ session.getAttribute("selectedCourse") + "'";

				rs = statement.executeQuery(sql);

				if (rs.next()) {
					int courseId = rs.getInt("courseId");

					sql = "SELECT matId FROM Material WHERE courseId="
							+ courseId + " AND matName='"
							+ request.getParameter("materials") + "'";
					rs = statement.executeQuery(sql);

					if (rs.next()) {
						int matId = rs.getInt("matId");

						sql = "UPDATE Material SET grade=" + grade
								+ " WHERE matId=" + matId;
						statement.executeUpdate(sql);

						ArrayList<Material> materials = new ArrayList<Material>();
						// Refreshing the course list
						sql = "SELECT * FROM Material";

						rs = statement.executeQuery(sql);

						while (rs.next()) {
							Material m = new Material();

							m.setCourseId(rs.getInt("courseId"));
							m.setMatId(rs.getInt("matId"));
							m.setStudId(rs.getInt("studId"));
							m.setGrade(rs.getString("grade"));
							m.setMatName(rs.getString("matName"));
							m.setMatType(rs.getString("matType"));
							m.setMatWeight(rs.getString("matWeight"));

							materials.add(m);
						}

						session.setAttribute("materials", materials);
						response.sendRedirect("stud_view.jsp");

					}
				}
			} catch (SQLException e) {
				System.out.println("btnGrade - SQLEXCEPTION - "
						+ e.getMessage());
			}

		} else {
			// COMES FROM createStud.jsp onchange select tag
			// session.setAttribute("courses", null);
			String nameOfForm = request.getParameter("nameOfForm");

			if (nameOfForm.equals("studForm")) {
				try {
					String progName = request.getParameter("programs");
					int progId = Integer.parseInt(progName.substring(0, 1));

					clearProgDropAttributes(request);
					// INSTEAD OF LINE BELOW USE A METHOD TO CLEAR ALL
					// prog+progid
					// ATTRIBUTES
					// session.setAttribute("prog" + progId, null);

					String connectionURL = "jdbc:derby://localhost:1527/student;create=true";
					Connection conn = null;
					ResultSet rs;
					conn = DriverManager.getConnection(connectionURL);
					Statement statement = conn.createStatement();
					Statement profStatement = conn.createStatement();

					String sql = "SELECT * FROM Course WHERE progId=" + progId;
					rs = statement.executeQuery(sql);

					ArrayList<Course> courses = new ArrayList<Course>();

					while (rs.next()) {
						Course c = new Course();
						c.setCourseId(rs.getInt("courseId"));
						c.setProfId(rs.getInt("profId"));
						c.setProgId(progId);
						c.setCourseName(rs.getString("courseName"));
						c.setCourseCode(rs.getString("courseCode"));
						c.setCourseTime(rs.getString("courseTime"));
						c.setRoomNum(rs.getString("roomNum"));
						int profId = rs.getInt("profId");
						sql = "SELECT profId, firstName, lastName FROM Professor WHERE profId="
								+ rs.getInt("profId");
						ResultSet profrs = profStatement.executeQuery(sql);
						while (profrs.next()) {
							if (profId == profrs.getInt("profId")) {
								c.setProfName(profrs.getString("firstName")
										+ " " + profrs.getString("lastName"));
							}
						}

						courses.add(c);
					}

					session.setAttribute("courses", courses);

					statement.close();
					conn.close();

					// RESTORE DATA THAT MAY HAVE BEEN IN TEXT BOXES
					Student s = new Student();
					s.setFirstName(request.getParameter("firstName"));
					s.setLastName(request.getParameter("lastName"));
					s.setEmail(request.getParameter("email"));
					s.setUser(request.getParameter("user"));
					s.setPass(request.getParameter("pass"));
					session.setAttribute("prog" + progId, "selected");
					session.setAttribute("stud", s);
					// END INPUT RESTORES

					response.sendRedirect("createStud.jsp");

				} catch (SQLException e) {
					out.print("Program dropbox change - SQL Error -  "
							+ e.getMessage());
				}
				// COMES FROM add_edit_grade.jsp
			} else if (nameOfForm.equals("gradeForm")) {
				int courseId = 0;
				String courseName = request.getParameter("courses");

				try {
					String connectionURL = "jdbc:derby://localhost:1527/student;create=true";
					Connection conn = null;
					ResultSet rs;
					conn = DriverManager.getConnection(connectionURL);
					Statement statement = conn.createStatement();

					String sql = "SELECT courseId FROM Course WHERE courseName='"
							+ courseName + "'";
					rs = statement.executeQuery(sql);

					if (rs.next()) {
						courseId = rs.getInt("courseId");

						ArrayList<Material> mats = new ArrayList<Material>();
						Student s = (Student) session.getAttribute("student");

						sql = "SELECT * FROM Material WHERE courseId ="
								+ courseId;

						rs = statement.executeQuery(sql);

						while (rs.next()) {
							Material m = new Material();

							m.setMatType(rs.getString("matType"));
							m.setMatName(rs.getString("matName"));
							m.setMatWeight(rs.getString("matWeight"));
							m.setCourseId(courseId);
							m.setStudId(s.getStudid());

							mats.add(m);
						}

						session.setAttribute("mats", mats);
						session.setAttribute("selectedCourse", courseName);

						// RESTORE COURSES COMBOBOX
						// session.setAttribute("courseBox" + courseId,
						// "selected");
						// CANT RESTORE COMBO BOX

						response.sendRedirect("add_edit_grade.jsp");
					} else
						System.out.println("Failed");

				} catch (SQLException e) {
					System.out
							.println("FROM SUBMITTED GRADE FORM - SQL EXCEPTION - "
									+ e.getMessage());
				}
			}
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

	// CLEARS ALL "prog"+progId ATTRIBUTES
	void clearProgDropAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession();

		try {
			String connectionURL = "jdbc:derby://localhost:1527/student;create=true";
			Connection conn = null;
			ResultSet rs;
			conn = DriverManager.getConnection(connectionURL);
			Statement statement = conn.createStatement();

			String sql = "SELECT * FROM Program";
			rs = statement.executeQuery(sql);

			while (rs.next()) {
				session.setAttribute("prog" + rs.getInt("progId"), null);
			}
		} catch (SQLException e) {
			System.out.println("clearProgDropAttributes() - " + e.getMessage());
		}

	}
}
