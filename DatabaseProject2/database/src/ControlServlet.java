import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private userDAO userDAO = new userDAO();
	private String currentUser;
	private HttpSession session = null;

	public ControlServlet() {

	}

	public void init() {
		userDAO = new userDAO();
		currentUser = "";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);

		try {
			switch (action) {
			case "/login":
				login(request, response);
				break;
			case "/register":
				register(request, response);
				break;
			case "/initialize":
				userDAO.init();
				System.out.println("Database successfully initialized!");
				rootPage(request, response, "");
				break;
			case "/root":
				rootPage(request, response, "");
				break;
			case "/logout":
				logout(request, response);
				break;
			case "/list":
				System.out.println("The action is: list");
				listUser(request, response);
				break;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		System.out.println("listUser started: 00000000000000000000000000000000000");

		List<user> listUser = userDAO.listAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
		dispatcher.forward(request, response);

		System.out.println("listPeople finished: 111111111111111111111111111111111111");
	}

//	private void listTree(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException, ServletException {
//		System.out.println("listUser started: 00000000000000000000000000000000000");
//
//		List<Tree> listTrees = userDAO.listAllTrees();
//		request.setAttribute("listTree", listTrees);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
//		dispatcher.forward(request, response);
//
//		System.out.println("listPeople finished: 111111111111111111111111111111111111");
//	}

	private void rootPage(HttpServletRequest request, HttpServletResponse response, String view)
			throws ServletException, IOException, SQLException {
		System.out.println("root view");
		request.setAttribute("listUser", userDAO.listAllUsers());
		request.getRequestDispatcher("rootView.jsp").forward(request, response);
	}

	private void davidPage(HttpServletRequest request, HttpServletResponse response, String view)
			throws ServletException, IOException, SQLException {
		System.out.println("david view");
//		request.setAttribute("listTree", userDAO.listAllTrees());
		request.getRequestDispatcher("davidView.jsp").forward(request, response);
	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (email.equals("root") && password.equals("pass1234")) {
			System.out.println("Login Successful! Redirecting to root");
			session = request.getSession();
			session.setAttribute("email", email);
			rootPage(request, response, "");

		} else if (email.equals("david@gmail.com") && password.equals("david1234")) {
			System.out.println("Login Successful! Redirecting to David's root view");
			session = request.getSession();
			session.setAttribute("email", email);
			davidPage(request, response, "");

		} else if (userDAO.isValid(email, password)) {
			currentUser = email;
			System.out.println("Login Successful! Redirecting");
			request.getRequestDispatcher("clientpage.jsp").forward(request, response);

		} else {
			request.setAttribute("loginStr", "Login Failed: Please check your credentials.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("StudentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String creditCard = request.getParameter("creditCard");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirmation");

		if (password.equals(confirm)) {
			if (!userDAO.checkEmail(email)) {
				System.out.println("Registration Successful! Added to database");
				user users = new user(id, firstName, lastName, creditCard, email, password);
				userDAO.insert(users);
				response.sendRedirect("login.jsp");
			} else {
				System.out.println("Username taken, please enter new username");
				request.setAttribute("errorOne", "Registration failed: Username taken, please enter a new username.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		} else {
			System.out.println("Password and Password Confirmation do not match");
			request.setAttribute("errorTwo", "Registration failed: Password and Password Confirmation do not match.");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		currentUser = "";
		response.sendRedirect("login.jsp");
	}

}
