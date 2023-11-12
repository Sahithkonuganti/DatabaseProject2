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
		String sql = "SELECT * FROM tree";
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

			Tree trees = new Tree(id, quoteId, size, height, location, distanceFromHouse);
			listTrees.add(trees);
		}
		resultSet.close();
		disconnect();
		return listTrees;
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

	public void insertTree(Tree trees) throws SQLException {
		connect_func("root", "pass1234");
		String sql = "insert into tree(ID, size, height, location, distanceFromHouse) values (?, ?, ?, ?, ?)";

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setDouble(1, trees.getId());
		preparedStatement.setDouble(2, trees.getSize());
		preparedStatement.setDouble(3, trees.getHeight());
		preparedStatement.setString(4, trees.getLocation());
		preparedStatement.setDouble(5, trees.getDistanceFromHouse());

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
		String sql = "update User set id=?, firstName=?, lastName=?, creditCard=?, email=?, password=? where email=?";
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

	public boolean treeUpdate(Tree trees) throws SQLException {
		String sql = "update tree set ID=?, quoteID=?, height=?, size=?, distanceFromHouse=?, location=?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, trees.getId());
		preparedStatement.setDouble(2, trees.getHeight());
		preparedStatement.setDouble(3, trees.getSize());
		preparedStatement.setDouble(4, trees.getDistanceFromHouse());
		preparedStatement.setString(5, trees.getLocation());

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

	public Tree getTree(int height) throws SQLException {
		Tree trees = null;
		String sql = "SELECT * FROM tree WHERE height = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, height);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			int id = resultSet.getInt("id");
			int quoteId = resultSet.getInt("quoteId");
			int size = resultSet.getInt("size");
			height = resultSet.getInt("height");
			String location = resultSet.getString("location");
			int distanceFromHouse = resultSet.getInt("distanceFromHouse");

			trees = new Tree(id, quoteId, size, height, location, distanceFromHouse);
		}

		resultSet.close();
		statement.close();

		return trees;
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
//		String[] INITIALTree = { ("CREATE TABLE  if not exists tree( " + "ID INT, " + "quoteID INT, "
//				+ "height DOUBLE, " + "size DOUBLE, " + "distanceFromHouse DOUBLE, " + "PRIMARY KEY(id), "
//				+ "FOREIGN KEY(quoteid) REFERENCES Quotes(id), " + "location VARCHAR(100) " + "); ") };

//		String[] INITLALQuotes = { ("CREATE TABLE  if not exists Quotes( " + "id INT, " + "contractorID INT,"
//				+ "Price DOUBLE, " + "scheduleStart DATETIME, " + "scheduleEnd DATETIME, " + "PRIMARY KEY(id),"
//				+ "FOREIGN KEY(contractorID) REFERENCES Users(id), " + "FOREIGN KEY(clientId) REFERENCES Users(id)"
//				+ "); ") };

		String[] INITIAL = { "drop database if exists testdb; ", "create database testdb; ", "use testdb; ",
				"drop table if exists User; ",
				("CREATE TABLE if not exists User( " + "id INT," + "firstName VARCHAR(50) NOT NULL, "
						+ "lastName VARCHAR(50) NOT NULL, " + "creditCard CHAR(16), " + "email VARCHAR(50) NOT NULL, "
						+ "password VARCHAR(20) NOT NULL, " + "PRIMARY KEY(id), " + "UNIQUE(email)" + "); ") };

		String[] TUPLES = { ("insert into User(id, firstName, lastName, creditCard, email, password)"
				+ "values ('3', 'Susie ', 'Guzman', '2839349223492349', 'susie@mail.com', 'susie1234'),"
				+ "('1', 'default', 'default', '1234567812345678', 'root', 'pass1234'),"
				+ "('2', 'David', 'Smith', '1234123412341234', 'david@mail.com', 'david1234')") };

		// int, int, double, double, double, varchar(100)
//		String[] TREETUPLE = { ("INSERT INTO tree(ID, quoteID, height, size, distanceFromHouse, location)"
//				+ "values(2, 4, 6.0, 15.5, 5.0, '1111 valky road, washington'), "
//				+ "(8, 10,  12.0, 20.5, 6.0, '1222 Leaf drive, california'), "
//				+ "(14, 16, 18.0, 30.5, 7.0, '2325 wayne drive, michigan'), "
//				+ "(20, 22, 24.0, 35.5, 8.0, '1627 Leslie street, San Fransisco'), "
//				+ "(26, 28, 30.0, 40.5, 9.0, '2525 Casio road, Texas'), "
//				+ "(32, 34, 36.0, 45.5, 10.0, '1425 Citizen street, Japan'), "
//				+ "(38, 40, 42.0, 50.5, 11.0, '1010 Hasan street, Ohio'), "
//				+ "(44, 46, 48.0, 55.5, 12.0, 'Nadeshot street, Detroit'), "
//				+ "(50, 52, 54.0, 60.5, 13.0, '1500 ludwig road, Tokyo'), "
//				+ "(56, 58, 60.0, 65.5, 14.0, '4292 Mercedes street, Germany')") };
		// int, int, double
//		String[] QUOTESTUPLE = { ("INSERT INTO Quotes(id, contractorID, Price, scheduleStart, scheduleEnd)"
//				+ "values(6, 10, 30.5, 2022-01-02 5:20:30)," + "(7, 15, 35.5, 1999-08-31 6:15:30),"
//				+ "(8, 20, 40.5, 2003-06-02 3:33:30)") };
		// for loop to put these in database
		for (int i = 0; i < INITIAL.length; i++)
			statement.execute(INITIAL[i]);

		for (int i = 0; i < TUPLES.length; i++)
			statement.execute(TUPLES[i]);

//		for (int i = 0; i < INITIALTree.length; i++)
//			statement.execute(INITIALTree[i]);
//
//		for (int i = 0; i < TREETUPLE.length; i++) {
//			statement.execute(TREETUPLE[i]);
//		}
//		for (int i = 0; i < INITLALQuotes.length; i++) {
//			statement.execute(INITLALQuotes[i]);
//		}
//		for (int i = 0; i < QUOTESTUPLE.length; i++) {
//			statement.execute(QUOTESTUPLE[i]);
//		}

//		for (int i = 0; i < INITIALClient.length; i++)
//			statement.execute(TUPLES[i]);

		disconnect();
	}

}
