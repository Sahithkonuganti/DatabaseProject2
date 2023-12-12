import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

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

	// Create instance of Random class
	Random rand = new Random();
	int treeCutCount = 1;

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
			case "/client":
				clientPage(request, response, "");
				break;
			case "/register":
				register(request, response);
				break;
			case "/addTree":
				addTree(request, response);
				break;
			case "/quoteResponse":
				quoteResponse(request, response);
				break;
			case "/quoteRejection":
				quoteRejection(request, response);
				break;
			case "/quoteReSubmission":
				quoteResubmission(request, response);
				break;
			case "/clientResubmissionResponse":
				clientResubmissionResponse(request, response);
				break;
			case "/billPayment":
				billPayment(request, response);
				break;
			case "/cut":
				cutTree(request, response);
				break;
//			case "/submitRequest":
//				submitRequest(request, response);
//				break;
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

	private void listTree(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		System.out.println("listTrees started: 00000000000000000000000000000000000");

		List<Tree> listTrees = userDAO.listAllTrees();
		request.setAttribute("listTree", listTrees);
		RequestDispatcher dispatcher = request.getRequestDispatcher("TreeList.jsp");
		dispatcher.forward(request, response);

		System.out.println("listPeople finished: 111111111111111111111111111111111111");
	}

	private void rootPage(HttpServletRequest request, HttpServletResponse response, String view)
			throws ServletException, IOException, SQLException {
		System.out.println("root view");
		request.setAttribute("listUser", userDAO.listAllUsers());
		request.setAttribute("listEasyClients", userDAO.listEasyClients());
		request.setAttribute("listOneTreeQuotes", userDAO.listOneTreeQuotes());
		request.setAttribute("listHighestTrees", userDAO.listHighestTree());
		request.getRequestDispatcher("rootView.jsp").forward(request, response);
	}

	private void davidPage(HttpServletRequest request, HttpServletResponse response, String view)
			throws ServletException, IOException, SQLException {
		System.out.println("david view");
		request.setAttribute("listTrees", userDAO.listAllTrees());
		request.setAttribute("listQuotes", userDAO.listAllQuotes());
		request.setAttribute("listResponses", userDAO.listAllQuoteResponse());
		request.setAttribute("listRejections", userDAO.listAllQuoteRejections());
		request.setAttribute("listResubmission", userDAO.listAllQuoteResubmission());
		request.setAttribute("listBill", userDAO.listBillInformation());
		request.setAttribute("listRevenue", userDAO.listAllRevenue());
		request.getRequestDispatcher("davidView.jsp").forward(request, response);
	}

	private void clientPage(HttpServletRequest request, HttpServletResponse response, String view)
			throws ServletException, IOException, SQLException {
		System.out.println("Client view");
		request.setAttribute("listTrees", userDAO.listClientTrees(currentUser));
		request.setAttribute("listResponses", userDAO.listClientQuoteResponse(currentUser));
		request.setAttribute("listBill", userDAO.listClientBillInformation(currentUser));
		request.getRequestDispatcher("clientpage.jsp").forward(request, response);
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

		} else if (email.equals("david@mail.com") && password.equals("david1234")) {
			System.out.println("Login Successful! Redirecting to David's root view");
			session = request.getSession();
			session.setAttribute("email", email);
			davidPage(request, response, "");

		} else if (userDAO.isValid(email, password)) {
			currentUser = email;
			System.out.println("Login Successful! Redirecting");
			session = request.getSession();
			session.setAttribute("username", currentUser);
			request.setAttribute("listClientTrees", userDAO.listClientTrees(currentUser));
			request.setAttribute("listClientQuoteResponse", userDAO.listClientQuoteResponse(currentUser));
			request.setAttribute("listClientQuoteRejections", userDAO.listClientQuoteRejections(currentUser));
			request.setAttribute("listClientQuoteResubmissions", userDAO.listClientQuoteResubmissions(currentUser));
			request.setAttribute("listClientBillInformation", userDAO.listClientBillInformation(currentUser));
			request.getRequestDispatcher("clientpage.jsp").forward(request, response);

		} else {
			request.setAttribute("loginStr", "Login Failed: Please check your credentials.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int id = rand.nextInt(1000);
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

	private void addTree(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// might be getting an error with foreign key and reference because of id and
		// quoteid being random values.
		int id = rand.nextInt(100);
		int quoteId = rand.nextInt(1, 10);
		double size = Double.parseDouble(request.getParameter("size"));
		double height = Double.parseDouble(request.getParameter("height"));
		String location = request.getParameter("location");
		double distanceFromHouse = Double.parseDouble(request.getParameter("distanceFromHouse"));

		// debugging
		System.out.println("Size: " + size);
		System.out.println("height: " + height);
		System.out.println("location: " + location);
		System.out.println("distanceFromHouse: " + distanceFromHouse);

		System.out.println("id: " + id);
		System.out.println("quoteid: " + quoteId);

		System.out.println("Tree information has added to database");
		Tree trees = new Tree(id, quoteId, size, height, location, distanceFromHouse, currentUser);
		userDAO.insert(trees);
		response.sendRedirect("InitialRequest.jsp");
	}

	private void quoteResponse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		try {
			int id = rand.nextInt(100);
			int quoteid = rand.nextInt(1, 10);
			double price = Double.parseDouble(request.getParameter("price"));
			String note = request.getParameter("note");
			// not printing email, is empty
			String email = request.getParameter("email");

			System.out.println("id: " + id);
			System.out.println("quoteid: " + quoteid);
			System.out.println("price: " + price);
			System.out.println("note: " + note);
			System.out.println("email: " + email);
			QuoteRespond quoteResponses = new QuoteRespond(id, quoteid, price, note, email);
			request.setAttribute("QuoteRespond", quoteResponses);
			userDAO.insert(quoteResponses);
			response.sendRedirect("QuoteRespond.jsp");

		} catch (Exception e) {
			System.out.println("NumberFormatException: " + e);
		}
	}

	public void quoteRejection(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int id = rand.nextInt(100);
		int quoteid = rand.nextInt(1, 10);
		String note = request.getParameter("note");
		String email = request.getParameter("email");

		System.out.println("id: " + id);
		System.out.println("quoteid: " + quoteid);
		System.out.println("note: " + note);
		System.out.println("email: " + email);

		QuoteReject quoteRejections = new QuoteReject(id, quoteid, note, currentUser);
		userDAO.insert(quoteRejections);
		response.sendRedirect("QuoteRejection.jsp");
	}

	public void quoteResubmission(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int id = rand.nextInt(100);
		int quoteid = rand.nextInt(1, 10);
		String note = request.getParameter("note");
		String email = request.getParameter("email");

		QuoteResubmission quoteResubmission = new QuoteResubmission(id, quoteid, note, currentUser);
		userDAO.insert(quoteResubmission);
		response.sendRedirect("ClientQuoteResponse.jsp");
	}

	public void clientResubmissionResponse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int id = rand.nextInt(100);
		int quoteid = rand.nextInt(1, 10);
		double price = Double.parseDouble(request.getParameter("price"));
		String note = request.getParameter("note");
		String email = request.getParameter("email");

		response.sendRedirect("QuoteResubmissionResponse.jsp");
	}

	public void billPayment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		try {

			int id = rand.nextInt(100);
			int billid = rand.nextInt(1, 10);
			double payment = Double.parseDouble(request.getParameter("payment"));
			String email = request.getParameter("email");
			// time stamp
			LocalDateTime timestamp = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String timepaid = timestamp.format(formatter);
			System.out.println("Payment has been submitted at " + timepaid);

			System.out.println("Payment has been sent");
			Revenue revenueInformation = new Revenue(id, billid, payment, timepaid, currentUser);
			userDAO.insert(revenueInformation);

			response.sendRedirect("clientpage.jsp");
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException: " + e);
		}
	}

	public void cutTree(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int id = rand.nextInt(100);

	}

//	public void submitRequest(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException, SQLException {
//		String note = request.getParameter("note");
//
//	}

}
