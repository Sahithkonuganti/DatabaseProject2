import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public userDAO() {
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	protected void connect_func() throws SQLException {
		// uses default connection to the database
		if (connect == null || connect.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			connect = (Connection) DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=john1234");
			System.out.println(connect);

		}
	}

	public boolean database_login(String email, String password) throws SQLException {
		try {
			connect_func("root", "pass1234");
			String sql = "select * from user where email = ?";
			preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println("failed login");
			return false;
		}

	}

	// connect to the database
	public void connect_func(String username, String password) throws SQLException {
		if (connect == null || connect.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			connect = (Connection) DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/userdb?" + "useSSL=false&user=" + username + "&password=" + password);
			System.out.println(connect);
		}
	}

	public List<user> listAllUsers() throws SQLException {
		List<user> listUser = new ArrayList<user>();
		String sql = "SELECT * FROM User";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String creditCard = resultSet.getString("creditCard");
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");

			user users = new user(id, firstName, lastName, creditCard, email, password);
			listUser.add(users);

		}
		resultSet.close();
		statement.close();
		disconnect();
		return listUser;
	}

	public List<Tree> listAllTrees() throws SQLException {
		List<Tree> listTrees = new ArrayList<Tree>();
		String sql = "SELECT * FROM Tree";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int quoteId = resultSet.getInt("quoteId");
			double size = resultSet.getDouble("size");
			double height = resultSet.getDouble("height");
			String location = resultSet.getString("location");
			double distanceFromHouse = resultSet.getDouble("distanceFromHouse");
			String email = resultSet.getString("email");

			Tree trees = new Tree(id, quoteId, size, height, location, distanceFromHouse, email);
			listTrees.add(trees);
		}
		resultSet.close();
		disconnect();
		return listTrees;
	}

	public List<Tree> listClientTrees(String currentUser) throws SQLException {
		List<Tree> listTrees = new ArrayList<Tree>();

		String sql = "SELECT * FROM Tree WHERE email = ?";
		connect_func();

		PreparedStatement preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, currentUser);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int quoteId = resultSet.getInt("quoteId");
			double size = resultSet.getDouble("size");
			double height = resultSet.getDouble("height");
			String location = resultSet.getString("location");
			double distanceFromHouse = resultSet.getDouble("distanceFromHouse");

			Tree trees = new Tree(id, quoteId, size, height, location, distanceFromHouse, currentUser);
			listTrees.add(trees);
		}
		resultSet.close();
		disconnect();
		return listTrees;
	}

	public List<quote> listAllQuotes() throws SQLException {
		List<quote> listQuotes = new ArrayList<quote>();
		String sql = "SELECT * FROM Quotes";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int contractorID = resultSet.getInt("contractorID");
			double price = resultSet.getDouble("price");
			Timestamp scheduleStart = resultSet.getTimestamp("scheduleStart");
			Timestamp scheduleEnd = resultSet.getTimestamp("scheduleEnd");

			quote quotes = new quote(id, contractorID, price, scheduleStart, scheduleEnd);
			listQuotes.add(quotes);
		}
		resultSet.close();
		disconnect();
		return listQuotes;
	}

	public List<QuoteRespond> listAllQuoteResponse() throws SQLException {
		List<QuoteRespond> listResponses = new ArrayList<QuoteRespond>();
		String sql = "SELECT * FROM QuoteResponse";
		connect_func();

		PreparedStatement preparedStatement = connect.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int quoteid = resultSet.getInt("quoteid");
			double price = resultSet.getDouble("price");
			String note = resultSet.getString("note");
			String email = resultSet.getString("email");

			QuoteRespond quoteResponses = new QuoteRespond(id, quoteid, price, note, email);
			listResponses.add(quoteResponses);
		}
		resultSet.close();
		disconnect();
		return listResponses;
	}

	public List<QuoteRespond> listClientQuoteResponse(String currentUser) throws SQLException {
		List<QuoteRespond> listResponses = new ArrayList<QuoteRespond>();
		String sql = "SELECT * FROM QuoteResponse WHERE email = ?";
		connect_func();

		PreparedStatement preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, currentUser);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int quoteid = resultSet.getInt("quoteid");
			double price = resultSet.getDouble("price");
			String note = resultSet.getString("note");
			String email = resultSet.getString("email");
			// debugging
			System.out.println("Fetching quote responses for user: " + currentUser);

			System.out.println("id: " + id);
			System.out.println("quoteid: " + quoteid);
			System.out.println("price: " + price);
			System.out.println("note: " + note);
			System.out.println("email: " + email);

			QuoteRespond quoteResponses = new QuoteRespond(id, quoteid, price, note, email);
			listResponses.add(quoteResponses);

		}
		resultSet.close();
		disconnect();
		return listResponses;
	}

	public List<QuoteReject> listAllQuoteRejections() throws SQLException {
		List<QuoteReject> listRejections = new ArrayList<QuoteReject>();
		String sql = "SELECT * FROM QuoteReject";
		connect_func();

		PreparedStatement preparedStatement = connect.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int quoteid = resultSet.getInt("quoteid");
			String note = resultSet.getString("note");
			String email = resultSet.getString("email");

			// debugging
			System.out.println("id: " + id);
			System.out.println("quoteid: " + quoteid);
			System.out.println("note: " + note);
			System.out.println("email: " + email);

			QuoteReject quoteRejections = new QuoteReject(id, quoteid, note, email);
			listRejections.add(quoteRejections);
		}

		resultSet.close();
		disconnect();
		return listRejections;

	}

	public List<QuoteReject> listClientQuoteRejections(String currentUser) throws SQLException {
		List<QuoteReject> listRejections = new ArrayList<QuoteReject>();

		String sql = "SELECT * FROM QuoteReject WHERE email = ?";
		connect_func();

		PreparedStatement preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, currentUser);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int quoteid = resultSet.getInt("quoteid");
			String note = resultSet.getString("note");
			String email = resultSet.getString("email");

			System.out.println("Fetching quote rejections for user: " + currentUser);
			// debugging
			System.out.println("id: " + id);
			System.out.println("quoteid: " + quoteid);
			System.out.println("note: " + note);
			System.out.println("email: " + email);

			QuoteReject quoteRejections = new QuoteReject(id, quoteid, note, email);
			listRejections.add(quoteRejections);

		}
		resultSet.close();
		disconnect();
		return listRejections;

	}

	public List<QuoteResubmission> listAllQuoteResubmission() throws SQLException {
		List<QuoteResubmission> listResubmission = new ArrayList<QuoteResubmission>();
		String sql = "SELECT * FROM QuoteResubmission";
		connect_func();

		PreparedStatement preparedStatement = connect.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int quoteid = resultSet.getInt("quoteid");
			String note = resultSet.getString("note");
			String email = resultSet.getString("email");
			// debugging
			System.out.println("id: " + id);
			System.out.println("quoteid: " + quoteid);
			System.out.println("note:" + note);
			System.out.println("email: " + email);

			QuoteResubmission quoteResubmission = new QuoteResubmission(id, quoteid, note, email);
			listResubmission.add(quoteResubmission);

		}

		resultSet.close();
		disconnect();
		return listResubmission;
	}

	public List<QuoteResubmission> listClientQuoteResubmissions(String currentUser) throws SQLException {
		List<QuoteResubmission> listResubmission = new ArrayList<QuoteResubmission>();
		String sql = "SELECT * FROM QuoteResubmission WHERE email = ?";
		connect_func();

		PreparedStatement preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, currentUser);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int quoteid = resultSet.getInt("quoteid");
			String note = resultSet.getString("note");
			String email = resultSet.getString("email");
			// debugging
			System.out.println("id: " + id);
			System.out.println("quoteid: " + quoteid);
			System.out.println("note:" + note);
			System.out.println("email: " + email);

			QuoteResubmission quoteResubmission = new QuoteResubmission(id, quoteid, note, email);
			listResubmission.add(quoteResubmission);

		}

		resultSet.close();
		disconnect();
		return listResubmission;

	}

	public List<Bill> listBillInformation() throws SQLException {
		List<Bill> listBill = new ArrayList<Bill>();
		String sql = "SELECT * FROM BillInformation";
		connect_func();

		PreparedStatement preparedStatement = connect.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int billid = resultSet.getInt("billid");
			double bill = resultSet.getDouble("bill");
			String status = resultSet.getString("status");
			String email = resultSet.getString("email");

			// debugging
			System.out.println("id: " + id);
			System.out.println("billid: " + billid);
			System.out.println("bill: " + bill);
			System.out.println("status: " + status);
			System.out.println("email: " + email);

			Bill billInformation = new Bill(id, billid, bill, status, email);
			listBill.add(billInformation);
		}

		resultSet.close();
		disconnect();
		return listBill;
	}

	public List<Bill> listClientBillInformation(String currentUser) throws SQLException {
		List<Bill> listBill = new ArrayList<Bill>();
		String sql = "SELECT * FROM BillInformation WHERE email = ?";
		connect_func();
		PreparedStatement preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, currentUser);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int billid = resultSet.getInt("billid");
			double bill = resultSet.getDouble("bill");
			String status = resultSet.getString("status");
			String email = resultSet.getString("email");

			// debugging
			System.out.println("id: " + id);
			System.out.println("billid: " + billid);
			System.out.println("bill: " + bill);
			System.out.println("status: " + status);
			System.out.println("email: " + email);

			Bill billInformation = new Bill(id, billid, bill, status, email);
			listBill.add(billInformation);
		}

		resultSet.close();
		disconnect();
		return listBill;

	}

	public List<Revenue> listAllRevenue() throws SQLException {
		List<Revenue> listRevenue = new ArrayList<Revenue>();
		String sql = "SELECT * FROM Revenue";
		connect_func();
		PreparedStatement preparedStatement = connect.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int billid = resultSet.getInt("billid");
			double payment = resultSet.getDouble("payment");
			String timepaid = resultSet.getString("timepaid");
			String email = resultSet.getString("email");

			Revenue revenueInformation = new Revenue(id, billid, payment, timepaid, email);
			listRevenue.add(revenueInformation);
		}

		resultSet.close();
		disconnect();
		return listRevenue;
	}

	public List<user> listEasyClients() throws SQLException {
		List<user> listEasyClients = new ArrayList<user>();
		String sql = "SELECT DISTINCT u.* " + "FROM User u " + "WHERE NOT EXISTS " + "    (SELECT 1 "
				+ "     FROM Quotes q " + "     LEFT JOIN QuoteResponse qr ON q.id = qr.quoteid "
				+ "     WHERE q.clientId = u.id AND qr.id IS NOT NULL)";
		connect_func();
		statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {

			int id = resultSet.getInt("id");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String creditCard = resultSet.getString("creditCard");
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");

			user users = new user(id, firstName, lastName, creditCard, email, password);
			listEasyClients.add(users);

		}
		resultSet.close();
		disconnect();
		return listEasyClients;
	}

	// INCOMPLETE, needs to be worked on
	public List<user> listBigClients() throws SQLException {
		List<user> listBigClients = new ArrayList<user>();
		String sql = "SELECT DISTINCT u.* " + "FROM User u" + "WHERE NOT EXISTS";

		while (resultSet.next()) {

			int id = resultSet.getInt("id");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String creditCard = resultSet.getString("creditCard");
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");

			user users = new user(id, firstName, lastName, creditCard, email, password);
			listBigClients.add(users);

		}
		resultSet.close();
		disconnect();
		return listBigClients;

	}

	// INCOMPLETE Working on it. Prints nothing right now
	public List<user> listOneTreeQuotes() throws SQLException {
		List<user> listOneTreeQuotes = new ArrayList<user>();
		String sql = "SELECT u.id, u.firstName, u.lastName, u.creditCard, u.email, u.password, "
				+ "qr.id AS responseId, qr.price AS responsePrice, qr.note AS responseNote, qr.email AS responseEmail "
				+ "FROM User u " + "JOIN QuoteResponse qr ON u.id = qr.email "
				+ "WHERE EXISTS (SELECT 1 FROM QuoteResponse qr WHERE qr.email = u.email) "
				+ "GROUP BY u.id, u.firstName, u.lastName, u.creditCard, u.email, u.password, "
				+ "         qr.id, qr.price, qr.note, qr.email " + "HAVING COUNT(qr.id) = 1";

		connect_func();
		statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {

			int id = resultSet.getInt("id");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String creditCard = resultSet.getString("creditCard");
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");

			user users = new user(id, firstName, lastName, creditCard, email, password);
			listOneTreeQuotes.add(users);

		}
		resultSet.close();
		disconnect();
		return listOneTreeQuotes;

	}

	// INCCOMPLETE: prints only the highest tree, not the cut for now
	public List<Tree> listHighestTree() throws SQLException {
		List<Tree> listHighestTrees = new ArrayList<Tree>();
		String sql = "SELECT *\n" + "FROM Tree\n" + "WHERE height = (SELECT MAX(height) FROM Tree);";
		connect_func();
		statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {

			int id = resultSet.getInt("id");
			int quoteId = resultSet.getInt("quoteId");
			double size = resultSet.getDouble("size");
			double height = resultSet.getDouble("height");
			String location = resultSet.getString("location");
			double distanceFromHouse = resultSet.getDouble("distanceFromHouse");
			String email = resultSet.getString("email");

			Tree trees = new Tree(id, quoteId, size, height, location, distanceFromHouse, email);
			listHighestTrees.add(trees);
		}

		resultSet.close();
		disconnect();
		return listHighestTrees;
	}

	// INCOMPLETE
	public List<user> listGoodClients() throws SQLException {
		List<user> listGoodClients = new ArrayList<user>();
		String sql = "";
		connect_func();
		statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {

			int id = resultSet.getInt("id");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String creditCard = resultSet.getString("creditCard");
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");

			user users = new user(id, firstName, lastName, creditCard, email, password);
			listGoodClients.add(users);
		}

		resultSet.close();
		disconnect();
		return listGoodClients;

	}

	protected void disconnect() throws SQLException {
		if (connect != null && !connect.isClosed()) {
			connect.close();
		}
	}

	public void insert(user users) throws SQLException {
		connect_func("root", "pass1234");
		String sql = "insert into User(id, firstName, lastName, creditCard, email, password) values (?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, users.getId());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, String.valueOf(users.getCreditCard()));
		preparedStatement.setString(5, users.getEmail());
		preparedStatement.setString(6, users.getPassword());

		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	public void insert(Tree trees) throws SQLException {
		connect_func();
		String sql = "insert into Tree(id, quoteId, size, height, location, distanceFromHouse, email) values (?, ?, ?, ?, ?, ?, ?)";

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, trees.getId());
		preparedStatement.setInt(2, trees.getQuoteId());
		preparedStatement.setDouble(3, trees.getSize());
		preparedStatement.setDouble(4, trees.getHeight());
		preparedStatement.setString(5, trees.getLocation());
		preparedStatement.setDouble(6, trees.getDistanceFromHouse());
		preparedStatement.setString(7, trees.getEmail());

		preparedStatement.executeUpdate();
		preparedStatement.close();

	}

	public void insert(QuoteRespond quoteResponses) throws SQLException {
		connect_func();

		String sql = "insert into QuoteResponse(id, quoteid, price, note, email) values(?, ?, ?, ?, ?)";

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, quoteResponses.getId());
		preparedStatement.setInt(2, quoteResponses.getQuoteid());
		preparedStatement.setDouble(3, quoteResponses.getPrice());
		preparedStatement.setString(4, quoteResponses.getNote());
		preparedStatement.setString(5, quoteResponses.getEmail());

		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	public void insert(QuoteReject quoteRejections) throws SQLException {
		connect_func();
		String sql = "insert into QuoteReject(id, quoteid, note, email) values(?, ?, ?, ?)";

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, quoteRejections.getId());
		preparedStatement.setInt(2, quoteRejections.getQuoteid());
		preparedStatement.setString(3, quoteRejections.getNote());
		preparedStatement.setString(4, quoteRejections.getEmail());

		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	public void insert(QuoteResubmission quoteResubmission) throws SQLException {
		connect_func();
		String sql = "insert into QuoteResubmission(id, quoteid, note, email) values(?, ?, ?, ?)";

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, quoteResubmission.getId());
		preparedStatement.setInt(2, quoteResubmission.getQuoteid());
		preparedStatement.setString(3, quoteResubmission.getNote());
		preparedStatement.setString(4, quoteResubmission.getEmail());

		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	public void insert(Revenue revenueInformation) throws SQLException {
		connect_func();
		String sql = "insert into Revenue(id, payment, timepaid, email) values(?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, revenueInformation.getId());
		preparedStatement.setDouble(2, revenueInformation.getPayment());
		preparedStatement.setString(3, revenueInformation.getTimepaid());
		preparedStatement.setString(4, revenueInformation.getEmail());

		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	public void insert(Bill billInformation) throws SQLException {
		connect_func();
		String sql = "insert into BillInformation(id, billid, bill, status, email) values(?, ?, ?, ?, ?, ?)";

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, billInformation.getId());
		preparedStatement.setInt(2, billInformation.getBillid());
		preparedStatement.setDouble(3, billInformation.getBill());
		preparedStatement.setString(4, billInformation.getStatus());
		preparedStatement.setString(5, billInformation.getEmail());

		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	public boolean delete(String email) throws SQLException {
		String sql = "DELETE FROM User WHERE email = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);

		boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return rowDeleted;
	}

	public boolean update(user users) throws SQLException {
		String sql = "update User set id=?, firstName=?, lastName=?, creditCard=?, email=?, password=? ";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, users.getId());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, String.valueOf(users.getCreditCard()));
		preparedStatement.setString(5, users.getEmail());
		preparedStatement.setString(6, users.getPassword());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return rowUpdated;
	}

	public boolean update(Tree trees) throws SQLException {
		String sql = "update Tree set ID=?, quoteID=?, height=?, size=?, distanceFromHouse=?, location=?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, trees.getId());
		preparedStatement.setDouble(2, trees.getQuoteId());
		preparedStatement.setDouble(3, trees.getHeight());
		preparedStatement.setDouble(4, trees.getSize());
		preparedStatement.setDouble(5, trees.getDistanceFromHouse());
		preparedStatement.setString(6, trees.getLocation());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return rowUpdated;

	}

	public boolean update(QuoteRespond quoteResponses) throws SQLException {
		String sql = "update QuoteResponse set id=?, quoteid=?, price=?, note=?, email=?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);

		preparedStatement.setInt(1, quoteResponses.getId());
		preparedStatement.setInt(2, quoteResponses.getQuoteid());
		preparedStatement.setDouble(3, quoteResponses.getPrice());
		preparedStatement.setString(4, quoteResponses.getNote());
		preparedStatement.setString(5, quoteResponses.getEmail());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return rowUpdated;
	}

	public boolean update(QuoteReject quoteRejections) throws SQLException {
		String sql = "update QuoteReject set id=?, quoteid=?, note=?, email=?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, quoteRejections.getId());
		preparedStatement.setInt(2, quoteRejections.getQuoteid());
		preparedStatement.setString(3, quoteRejections.getNote());
		preparedStatement.setString(4, quoteRejections.getEmail());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return rowUpdated;
	}

	public boolean update(QuoteResubmission quoteResubmission) throws SQLException {
		String sql = "update QuoteResubmission set id=?, quoteid=?, note=?, email=?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, quoteResubmission.getId());
		preparedStatement.setInt(2, quoteResubmission.getQuoteid());
		preparedStatement.setString(3, quoteResubmission.getNote());
		preparedStatement.setString(4, quoteResubmission.getEmail());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return rowUpdated;
	}

	public boolean update(Bill billInformation) throws SQLException {
		String sql = "update BillInformation set id=?, billid=?, bill=?, status=?, email=?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, billInformation.getId());
		preparedStatement.setInt(2, billInformation.getBillid());
		preparedStatement.setDouble(3, billInformation.getBill());
		preparedStatement.setString(4, billInformation.getStatus());
		preparedStatement.setString(5, billInformation.getEmail());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return rowUpdated;
	}

	public boolean update(Revenue revenueInformation) throws SQLException {
		String sql = "update Revenue set id=?, billid=?, payment=?, timepaid=?, email=?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, revenueInformation.getId());
		preparedStatement.setDouble(2, revenueInformation.getPayment());
		preparedStatement.setInt(3, revenueInformation.getBillid());
		preparedStatement.setString(4, revenueInformation.getTimepaid());
		preparedStatement.setString(5, revenueInformation.getEmail());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return rowUpdated;
	}

	public user getUser(int id) throws SQLException {
		user user = null;
		String sql = "SELECT * FROM User WHERE id = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			id = resultSet.getInt("id");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String creditCard = resultSet.getString("creditCard");
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");

			user = new user(id, firstName, lastName, creditCard, email, password);
		}

		resultSet.close();
		statement.close();

		return user;
	}

	public Tree getTree(double height) throws SQLException {
		Tree trees = null;
		String sql = "SELECT * FROM Tree WHERE height = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setDouble(4, height);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			int id = resultSet.getInt("id");
			int quoteId = resultSet.getInt("quoteId");
			double size = resultSet.getDouble("size");
			height = resultSet.getDouble("height");
			String location = resultSet.getString("location");
			int distanceFromHouse = resultSet.getInt("distanceFromHouse");
			String email = resultSet.getString("email");

			trees = new Tree(id, quoteId, size, height, location, distanceFromHouse, email);
		}

		resultSet.close();
		statement.close();

		return trees;
	}

	public QuoteRespond getQuoteResponses(String email) throws SQLException {
		QuoteRespond quoteResponses = null;

		String sql = "SELECT * FROM QuoteResponse WHERE email = ?";

		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(5, email);

		if (resultSet.next()) {
			int id = resultSet.getInt("id");
			int quoteid = resultSet.getInt("quoteid");
			double price = resultSet.getDouble("price");
			String note = resultSet.getString("note");
			email = resultSet.getString("email");

			quoteResponses = new QuoteRespond(id, quoteid, price, note, email);

		}
		resultSet.close();
		disconnect();
		return quoteResponses;
	}

	public QuoteReject getQuoteRejection(String email) throws SQLException {
		QuoteReject quoteRejections = null;

		String sql = "SELECT * FROM QuoteReject WHERE email = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(4, email);

		if (resultSet.next()) {
			int id = resultSet.getInt("id");
			int quoteid = resultSet.getInt("quoteid");
			String note = resultSet.getString("note");
			email = resultSet.getString("email");

			quoteRejections = new QuoteReject(id, quoteid, note, email);
		}
		resultSet.close();
		disconnect();
		return quoteRejections;
	}

	public QuoteResubmission getQuoteResubmission(String email) throws SQLException {
		QuoteResubmission quoteResubmission = null;

		String sql = "SELECT * FROM QuoteResubmission WHERE email = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);

		if (resultSet.next()) {
			int id = resultSet.getInt("id");
			int quoteid = resultSet.getInt("quoteid");
			String note = resultSet.getString("note");
			email = resultSet.getString("email");

			quoteResubmission = new QuoteResubmission(id, quoteid, note, email);

		}
		resultSet.close();
		disconnect();
		return quoteResubmission;
	}

	public Bill getBillInformation(String email) throws SQLException {
		Bill billInformation = null;
		String sql = "SELECT * FROM BillInformation WHERE email = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);

		if (resultSet.next()) {
			int id = resultSet.getInt("id");
			int billid = resultSet.getInt("billid");
			double bill = resultSet.getDouble("bill");
			String status = resultSet.getString("status");
			email = resultSet.getString("email");

			billInformation = new Bill(id, billid, bill, status, email);
		}
		resultSet.close();
		disconnect();
		return billInformation;
	}

	public Revenue getRevenueInformation(String email) throws SQLException {
		Revenue revenueInformation = null;
		String sql = "SELECT * FROM Revenue WHERE email = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);

		if (resultSet.next()) {
			int id = resultSet.getInt("id");
			int billid = resultSet.getInt("billid");
			double payment = resultSet.getDouble("payment");
			String timepaid = resultSet.getString("timepaid");
			email = resultSet.getString("email");

			revenueInformation = new Revenue(id, billid, payment, timepaid, email);

		}
		resultSet.close();
		disconnect();
		return revenueInformation;
	}

	public boolean checkEmail(String email) throws SQLException {
		boolean checks = false;
		String sql = "SELECT * FROM User WHERE email = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println(checks);

		if (resultSet.next()) {
			checks = true;
		}

		System.out.println(checks);
		return checks;
	}

	public boolean checkPassword(String password) throws SQLException {
		boolean checks = false;
		String sql = "SELECT * FROM User WHERE password = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(6, password);
		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println(checks);

		if (resultSet.next()) {
			checks = true;
		}

		System.out.println(checks);
		return checks;
	}

	public boolean isValid(String email, String password) throws SQLException {
		String sql = "SELECT * FROM User";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		resultSet.last();

		int setsize = resultSet.getRow();
		resultSet.beforeFirst();

		for (int i = 0; i < setsize; i++) {
			resultSet.next();
			if (resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
				return true;
			}
		}
		return false;
	}

	public void init() throws SQLException, FileNotFoundException, IOException {
		connect_func();
		statement = (Statement) connect.createStatement();

//		String[] INITIALClient = { ("CREATE TABLE client( " + "firstName VARCHAR(9000), " + "lastName VARCHAR(9000), "
//				+ "Phone VARCHAR(900000), " + "Address VARCHAR(90000000), " + "CreditCard VARCHAR(90000000000000), "
//				+ "Email VARCHAR(9000000000), " + "PRIMARY KEY(ClientID)") };
//

		String[] INITIAL = { "drop database if exists testdb; ", "create database testdb; ", "use testdb; ",
				"drop table if exists User; ",
				"CREATE TABLE if not exists User( " + "id INT," + "firstName VARCHAR(50) NOT NULL, "
						+ "lastName VARCHAR(50) NOT NULL, " + "creditCard CHAR(16), " + "email VARCHAR(50) NOT NULL, "
						+ "password VARCHAR(20) NOT NULL, " + "PRIMARY KEY(id), " + "UNIQUE(email) " + ");",

				"drop table if exists Quotes;",
				"CREATE TABLE if not exists Quotes( " + "id INT, " + "clientId INT, " + "contractorID INT, "
						+ "Price DOUBLE, " + "scheduleStart DATETIME," + "scheduleEnd DATETIME, " + "PRIMARY KEY(id),"
						+ "FOREIGN KEY(contractorID) REFERENCES User(id), "
						+ "FOREIGN KEY(clientId) REFERENCES User(id) " + ");",

				"drop table if exists Tree; ",
				"CREATE TABLE if not exists Tree( " + "id INT, " + "quoteid INT, " + "height DOUBLE," + "size DOUBLE, "
						+ "distanceFromHouse DOUBLE, " + "PRIMARY KEY(id), "
						+ "FOREIGN KEY(quoteid) REFERENCES Quotes(id), " + "location VARCHAR(100),  "
						+ "email VARCHAR(50)" +

						");",

				"drop table if exists QuoteResponse;",
				"CREATE TABLE if not exists QuoteResponse(" + "id INT, " + "quoteid INT, " + "price DOUBLE, "
						+ "note VARCHAR(100), " + "email VARCHAR(100), " + "PRIMARY KEY(id), "
						+ "FOREIGN KEY(quoteid) REFERENCES Quotes(id)" + ");",

				"drop table if exists QuoteReject;",
				"CREATE TABLE if not exists QuoteReject(" + "id INT, " + "quoteid INT, " + "note VARCHAR(100), "
						+ "email VARCHAR(100), " + "PRIMARY KEY(id), " + "FOREIGN KEY(quoteid) REFERENCES Quotes(id)"
						+ ");",

				"drop table if exists QuoteResubmission;",
				"CREATE TABLE if not exists QuoteResubmission(" + "id INT," + "quoteid INT," + "note VARCHAR(100),"
						+ "email VARCHAR(100), " + "FOREIGN KEY(quoteid) REFERENCES Tree(id)" + ");",

				"drop table if exists BillInformation;",
				"CREATE TABLE if not exists BillInformation(" + "id INT, " + "billid INT," + "bill DOUBLE,"
						+ "status VARCHAR(50), " + "email VARCHAR(100), " + "PRIMARY KEY(id),"
						+ "FOREIGN KEY(id) REFERENCES QuoteResponse(id), UNIQUE(billid)" + ");",

				"drop table if exists Revenue;",
				"CREATE TABLE if not exists Revenue(" + "id INT, " + "billid INT," + "payment DOUBLE,"
						+ "timepaid VARCHAR(100)," + "email VARCHAR(100), " + "PRIMARY KEY(billid)" + ");"

		};

		String[] TUPLES = { ("insert into User(id, firstName, lastName, creditCard, email, password)"
				+ "values (1, 'Susie ', 'Guzman', '2839349223492349', 'susie@mail.com', 'susie1234'),"
				+ "(2, 'default','default', '1234567812345678', 'root', 'pass1234'),"
				+ "(3, 'David', 'Smith', '1234123412341234', 'david@mail.com', 'david1234'), "
				+ "(4, 'pehar', 'rampal', '1234568884447778', 'pehar@mail.com', 'ferrari1234'),"
				+ "(5, 'palkin', 'rampal', '2224567816789178', 'palkin@mail.com', 'palkin1234'),"
				+ "(6, 'valky', 'rae', '987634125689890', 'valky@mail.com', 'mika1234'),"
				+ "(7, 'lud', 'wig', '1356789046789178', 'ludwig@mail.com', 'qt1234'),"
				+ "(8, 'austin', 'show', '2224512098712365', 'austin@mail.com', 'austin1234'),"
				+ "(9, 'hasan', 'abi', '2224530986453100', 'hasan@mail.com', 'hasan1234'),"
				+ "(10, 'het', 'patel', '2224901234560123', 'het@mail.com', 'het1234')") };

//		 int, int, double
		String[] QUOTESTUPLE = { ("INSERT INTO Quotes(id, clientId, contractorID, Price, scheduleStart, scheduleEnd)"
				+ "values(1, 1, 1, 20.50, '2022-01-02 5:20:30', '2022-01-05 6:30:25'),"
				+ "(2, 2, 2, 20.20, '1999-08-31 6:15:30', '1999-09-03 5:15:45'),"
				+ "(3, 3, 3, 11.20, '2003-06-02 3:33:30', '2003-06-06 4:44:40'),"
				+ "(4, 4, 4, 13.50, '2002-02-03 10:10:10', '2002-02-06 12:15:20'),"
				+ "(5, 5, 5, 14.50, '2001-08-02 9:30:30','2001-08-05 4:30:25' ),"
				+ "(6, 6, 7, 16.50, '2002-03-22 10:10:11', '2002-03-28 3:44:44'),"
				+ "(7, 7, 8, 17.50, '1992-01-08 2:22:22', '1992-01-10 3:35:45'), "
				+ "(8, 8, 9, 18.50, '1992-11-23 3:35:35', '1992-11-29 5:35:55'),"
				+ "(9, 9, 10, 19.50, '1997-08-08 1:20:30','1997-08-08 3:55:30'),"
				+ "(10, 10, 10, 22.50, '1991-06-04 6:30:25', '1991-06-06 7:50:55')") };

		String[] QUOTERESPONSETUPLE = { ("INSERT INTO QuoteResponse(id, quoteid, price, note, email) "
				+ "values(1, 2, 50.00, 'Can finish this job in a few days', 'susie@mail.com'), "
				+ "(2, 3, 100.00, 'Might take some time but can do', 'pehar@mail.com'), "
				+ "(3, 4, 150.00, 'Seems tricky but we will do what we can', 'palkin@mail.com'),"
				+ "(4, 4, 0.00, 'Sorry, this is not possible', 'valky@mail.com'), "
				+ "(5, 5, 90.00, 'Can be done wihtin a week', 'ludwig@mail.com'),"
				+ "(6, 6, 300.30, 'Hard job, and you wanted extra work', 'austin@mail.com'),"
				+ "(7, 7, 250.50, 'Tricky but can do this weekend', 'hasan@mail.com'), "
				+ "(8, 8, 50.50, 'Busy week but will come by next weekend', 'het@mail.com'),"
				+ "(9, 9, 1000.00, 'Due to travel, will cost more', 'sykkuno@mail.com'),"
				+ "(10, 10, 500.40, 'Medium tree, medium price, can do', 'ford@mail.com')")

		};

		String[] QUOTEREJECTIONTUPLE = { ("INSERT INTO QuoteReject(id, quoteid, note, email) "
				+ "values(1, 2, 'Unable to do it, we are backed up', 'susie@mail.com'),"
				+ "(2, 2, 'We don''t do service to Michigan fans Ohio ftw', 'pehar@mail.com'),"
				+ "(3, 3, 'We called you many times but no one answered, could not work', 'palkin@mail.com'),"
				+ "(4, 4, 'This work is out of our range', 'valky@mail.com'), "
				+ "(5, 5, 'Price is high', 'ludwig@mail.com'),"
				+ "(6, 6, 'Did not pay on time last time', 'austin@mail.com'),"
				+ "(7, 7, 'Heard customer is not respectful', 'hasan@mail.com'), "
				+ "(8, 8, 'Waited and waited but no one showed up', 'het@mail.com'), "
				+ "(9, 9, 'Unable to pay', 'sykkuno@mail.com'), " + "(10, 10, 'Unserious', 'ford@mail.com')") };

		String[] QUOTERESUBMISSIONTUPLE = { ("INSERT INTO QuoteReSubmission(id, quoteid, note, email) "
				+ "values(1, 1, 'Price too high', 'susie@mail.com'), "
				+ "(2, 2, 'Job was horrendus, left branch pieces everywhere', 'pehar@mail.com'),"
				+ "(3, 3, 'You cant be serious with the price', 'palkin@mail.com'), "
				+ "(4, 4, 'Should be simple no?', 'valky@mail.com'), "
				+ "(5, 5, 'Please, I want it gone', 'ludwig@mail.com'), "
				+ "(6, 6, 'Recheck the quote, there has to be a mistake' , 'austin@mail.com'), "
				+ "(7, 7, 'I migh as well do it myself with this nonsense', 'hasan@mail.com'), "
				+ "(8, 8, 'Have to cancel, moving', 'het@mail.com'), "
				+ "(9, 9, 'Do not remove all the trees', 'sykkuno@mail.com'), "
				+ "(10, 10, 'I want the front tree gone not back', 'ford@mail.com')") };

		// int, int, double, double, double, varchar(100)
		String[] TREETUPLE = { ("INSERT INTO Tree(id, quoteId, height, size, distanceFromHouse, location, email)"
				+ "values(1, 1, 6.0, 15.5, 5.0, '1111 valky road, washington', 'valky@mail.com'), "
				+ "(2, 2,  12.0, 20.5, 6.0, '1222 Leaf drive, california', 'sykkuno@mail.com'), "
				+ "(3, 3, 18.0, 30.5, 7.0, '2325 wayne drive, michigan', 'wayne@mail.com'), "
				+ "(4, 4, 24.0, 35.5, 8.0, '1627 Leslie street, San Fransisco', 'fuslie@mail.com' ), "
				+ "(5, 5, 30.0, 40.5, 9.0, '2525 Casio road, Texas', 'casio@mail.com'), "
				+ "(6, 6, 36.0, 45.5, 10.0, '1425 Citizen street, Japan', 'citizen@mail.com'), "
				+ "(7, 7, 42.0, 50.5, 11.0, '1010 Hasan street, Ohio', 'hasan@mail.com'), "
				+ "(8, 8, 48.0, 55.5, 12.0, 'Nadeshot street, Detroit', 'nadeshot@mail.com'), "
				+ "(9, 9, 54.0, 60.5, 13.0, '1500 ludwig road, Tokyo', 'ludwig@mail.com'), "
				+ "(10, 10, 60.0, 65.5, 14.0, '4292 Mercedes street, Germany', 'mercedes@mail.com')") };

		String[] BILLTUPLE = { ("INSERT INTO BillInformation(id, billid, bill, status, email) "
				+ "values(1, 1, 50.00, 'pending', 'susie@mail.com'), " + "(2, 2, 100.00, 'paid', 'pehar@mail.com'), "
				+ "(3, 3, 150.00, 'pending', 'palkin@mail.com'), " + "(4, 4, 0.00, 'paid', 'valky@mail.com'), "
				+ "(5, 5, 90.00, 'pending', 'ludwig@mail.com'), " + "(6, 6, 300.30, 'paid', 'austin@mail.com'), "
				+ "(7, 7, 250.50, 'pending', 'hasan@mail.com'), " + "(8, 8, 50.50, 'paid', 'het@mail.com'), "
				+ "(9, 9, 1000.00, 'pending', 'sykkuno@mail.com'), " + "(10, 10, 500.40, 'paid', 'ford@mail.com')")

		};

		String[] REVENUETUPLE = { ("INSERT INTO Revenue(id, billid, payment, timepaid, email) "
				+ "values(1, 1, 50.00, '2022-01-08 12:50:20', 'susie@mail.com'),"
				+ "(2, 2, 100.00, '2022-02-05 10:30:52', 'pehar@mail.com'), "
				+ "(3, 3, 150.00, '2022-08-10 7:40:25', 'palkin@mail.com'), "
				+ "(4, 5, 90.00, '2022-07-13 23:39:52', 'ludwig@mail.com') ")

		};

		String[] FOREIGNTUPLE = {
				"ALTER TABLE Quotes " + "ADD CONSTRAINT fk_Quotes_Contractor "
						+ "FOREIGN KEY(contractorID) REFERENCES User(id);",
				"ALTER TABLE Quotes " + "ADD CONSTRAINT fk_Quotes_User " + "FOREIGN KEY(clientId) REFERENCES User(id);",
				"ALTER TABLE Tree " + "ADD CONSTRAINT fk_Tree_Quotes " + "FOREIGN KEY(quoteid) REFERENCES Quotes(id);",
				"ALTER TABLE QuoteResponse " + "ADD CONSTRAINT fk_QuoteResponse_Quotes "
						+ "FOREIGN KEY(quoteid) REFERENCES Quotes(id);",
				"ALTER TABLE QuoteReSubmission " + "ADD CONSTRAINT fk_QuoteReSubmission_Tree "
						+ "FOREIGN KEY(quoteid) REFERENCES Tree(id);",

				"ALTER TABLE BillInformation " + "ADD CONSTRAINT fk_Quotes_BillInformation "
						+ "FOREIGN KEY(id) REFERENCES QuoteResponse(id);",

				"ALTER TABLE Revenue " + "ADD CONSTRAINT fk_BillInformation_Revenue "
						+ "FOREIGN KEY(billid) REFERENCES BillInformation(billid)" };

		// for loop to put these in database
		for (int i = 0; i < INITIAL.length; i++)
			statement.execute(INITIAL[i]);

		for (int i = 0; i < TUPLES.length; i++)
			statement.execute(TUPLES[i]);

//		for (int i = 0; i < INITLALQuotes.length; i++) {
//			statement.execute(INITLALQuotes[i]);
//		}
		for (int i = 0; i < QUOTESTUPLE.length; i++) {
			statement.execute(QUOTESTUPLE[i]);
		}
//		for (int i = 0; i < INITIALTree.length; i++)
//			statement.execute(INITIALTree[i]);
		for (int i = 0; i < QUOTERESPONSETUPLE.length; i++) {
			statement.execute(QUOTERESPONSETUPLE[i]);
		}
		for (int i = 0; i < QUOTEREJECTIONTUPLE.length; i++) {
			statement.execute(QUOTEREJECTIONTUPLE[i]);
		}
		for (int i = 0; i < TREETUPLE.length; i++) {
			statement.execute(TREETUPLE[i]);
		}
		for (int i = 0; i < BILLTUPLE.length; i++) {
			statement.execute(BILLTUPLE[i]);
		}
		for (int i = 0; i < REVENUETUPLE.length; i++) {
			statement.execute(REVENUETUPLE[i]);
		}
		for (int i = 0; i < QUOTERESUBMISSIONTUPLE.length; i++) {
			statement.execute(QUOTERESUBMISSIONTUPLE[i]);
		}
		for (int i = 0; i < FOREIGNTUPLE.length; i++) {
			statement.execute(FOREIGNTUPLE[i]);
		}

//		for (int i = 0; i < INITIALClient.length; i++)
//			statement.execute(TUPLES[i]);

		disconnect();
	}

}
