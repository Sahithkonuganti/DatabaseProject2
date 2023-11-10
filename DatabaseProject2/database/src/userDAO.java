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
			preparedStatement.setString(5, email);
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
		disconnect();
		return listUser;
	}

//	public List<Tree> listAllTrees() throws SQLException {
//		List<Tree> listTrees = new ArrayList<Tree>();
//		String sql = "SELECT * FROM tree";
//		connect_func();
//		statement = (Statement) connect.createStatement();
//		ResultSet resultSet = statement.executeQuery(sql);
//
//		while (resultSet.next()) {
//			int Height = resultSet.getInt("Height");
//			int Size = resultSet.getInt("Size");
//			int DistanceToHouse = resultSet.getInt("DistanceToHouse");
//			String Location = resultSet.getString("Location");
//
//			Tree trees = new Tree(Height, Size, DistanceToHouse, Location);
//			listTrees.add(trees);
//		}
//		resultSet.close();
//		disconnect();
//		return listTrees;
//	}

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
		String sql = "insert into tree(Height, Size, DistanceToHouse, Location) values (? ? ? ?)";

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, trees.getHeight());
		preparedStatement.setInt(2, trees.getSize());
		preparedStatement.setInt(3, trees.getDistanceToHouse());
		preparedStatement.setString(4, trees.getLocation());

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
		String sql = "update User set id=?, firstName=?, lastName =?, creditCard=?, email=?, password=? where email = ?";
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

//	public boolean update1(Tree trees) throws SQLException {
//		String sql = "update tree set Height=?, Size=?, DistanceToHouse=?, Location=?";
//		connect_func();
//
//		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
//		preparedStatement.setInt(1, trees.getHeight());
//		preparedStatement.setInt(2, trees.getSize());
//		preparedStatement.setInt(3, trees.getDistanceToHouse());
//		preparedStatement.setString(4, trees.getLocation());
//
//		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
//		preparedStatement.close();
//		return rowUpdated;
//
//	}

	public user getUser(String email) throws SQLException {
		user user = null;
		String sql = "SELECT * FROM User WHERE email = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			int id = resultSet.getInt("id");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String creditCard = resultSet.getString("creditCard");
			// email = resultSet.getString("email");
			String password = resultSet.getString("password");

			user = new user(id, firstName, lastName, creditCard, email, password);
		}

		resultSet.close();
		statement.close();

		return user;
	}

	public boolean checkEmail(String email) throws SQLException {
		boolean checks = false;
		String sql = "SELECT * FROM User WHERE email = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(5, email);
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

		int setSize = resultSet.getRow();
		resultSet.beforeFirst();

		for (int i = 0; i < setSize; i++) {
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

		String[] INITIALClient = { ("CREATE TABLE client( " + "firstName VARCHAR(9000), " + "lastName VARCHAR(9000), "
				+ "Phone VARCHAR(900000), " + "Address VARCHAR(90000000), " + "CreditCard VARCHAR(90000000000000), "
				+ "Email VARCHAR(9000000000), " + "PRIMARY KEY(ClientID)") };

//		String[] INITIALTree = { ("CREATE TABLE  if not exists tree( " + "Height INT, " + "Size INT, "
//				+ "DistanceToHouse INT, " + "Location VARCHAR(100) " + "); ") };

//		String[] INITLALQuote = {
//				("CREATE TABLE  if not exists Quote( " + "Price DOUBLE, " + "Note VARCHAR(300)" + "); ") };

		String[] INITIAL = { "drop database if exists testdb; ", "create database testdb; ", "use testdb; ",
				"drop table if exists User; ",
				("CREATE TABLE if not exists User( " + "id INTEGER," + "firstName VARCHAR(50) NOT NULL, "
						+ "lastName VARCHAR(50) NOT NULL, " + "creditCard CHAR(16), " + "email VARCHAR(50) NOT NULL, "
						+ "password VARCHAR(20) NOT NULL, " + "PRIMARY KEY(id), " + "UNIQUE(email)" + "); ") };

		String[] TUPLES = { ("insert into User(id, firstName, lastName, creditCard, email, password)"
				+ "values ('31232', 'Susie ', 'Guzman', '2839349223492349', 'susie@gmail.com', 'susie1234'),"
				+ "('1', 'default', 'default', '1234567812345678', 'root', 'pass1234'),"
				+ "('2', 'David', 'Smith', '1234123412341234', 'david@gmail.com', 'david1234')") };

//		String[] TREETUPLE = { ("INSERT INTO tree(Height, Size, DistanceToHouse, Location)"
//				+ "values(2,4,6, '1111 valky road, washington'), " + "(8,10,12,  '1222 Leaf drive, california'), "
//				+ "(14,16,18, '2325 wayne drive, michigan'), " + "(20,22,24, '1627 Leslie street, San Fransisco'), "
//				+ "(26,28,30, '2525 Casio road, Texas'), " + "(32,34,36, '1425 Citizen street, Japan'), "
//				+ "(38,40,42, '1010 Hasan street, Ohio'), " + "(44,46,48, 'Nadeshot street, Detroit'), "
//				+ "(50,52,54, '1500 ludwig road, Tokyo'), " + "(56,58,60, '4292 Mercedes street, Germany')") };

//		String[] QUOTETUPLE = { ("INSERT INTO quote(Price, Note) " + "values(100, 'Why is it priced so high?'),"
//				+ "(110, 'There seems to have been a mistake')," + "(120, 'I dont want this'),"
//				+ "(130, 'Can you re-do the math')," + "(140, 'I have to reconsider')," + "(150, 'Will respond asap'),"
//				+ "(160, 'I found a cheaper quote, backing out')," + "(170, 'Not today'),"
//				+ "(180, 'Cannot go through')," + "(190, 'Not at all')") };

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
//		for (int i = 0; i < INITLALQuote.length; i++) {
//			statement.execute(INITLALQuote[i]);
//		}
//		for (int i = 0; i < QUOTETUPLE.length; i++) {
//			statement.execute(QUOTETUPLE[i]);
//		}

//		for (int i = 0; i < INITIALClient.length; i++)
//			statement.execute(TUPLES[i]);

		disconnect();
	}

}
