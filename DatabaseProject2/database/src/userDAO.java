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
			String email = resultSet.getString("email");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String password = resultSet.getString("password");
			String birthday = resultSet.getString("birthday");
			String adress_street_num = resultSet.getString("adress_street_num");
			String adress_street = resultSet.getString("adress_street");
			String adress_city = resultSet.getString("adress_city");
			String adress_state = resultSet.getString("adress_state");
			String adress_zip_code = resultSet.getString("adress_zip_code");
			int cash_bal = resultSet.getInt("cash_bal");
			int PPS_bal = resultSet.getInt("PPS_bal");

			user users = new user(email, firstName, lastName, password, birthday, adress_street_num, adress_street,
					adress_city, adress_state, adress_zip_code, cash_bal, PPS_bal);
			listUser.add(users);
		}
		resultSet.close();
		disconnect();
		return listUser;
	}

	public List<Tree> listAllTrees() throws SQLException {
		List<Tree> listTrees = new ArrayList<Tree>();
		String treesql = "SELECT * FROM tree";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(treesql);

		while (resultSet.next()) {
			int height = resultSet.getInt("Height");
			int size = resultSet.getInt("Size");
			int distanceToHouse = resultSet.getInt("DistanceToHouse");
			String location = resultSet.getString("Location");

			Tree trees = new Tree(height, size, distanceToHouse, location);
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
		String sql = "insert into User(email, firstName, lastName, password, birthday,adress_street_num, adress_street,adress_city,adress_state,adress_zip_code,cash_bal,PPS_bal) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getBirthday());
		preparedStatement.setString(6, users.getAdress_street_num());
		preparedStatement.setString(7, users.getAdress_street());
		preparedStatement.setString(8, users.getAdress_city());
		preparedStatement.setString(9, users.getAdress_state());
		preparedStatement.setString(10, users.getAdress_zip_code());
		preparedStatement.setInt(11, users.getCash_bal());
		preparedStatement.setInt(12, users.getPPS_bal());

		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	public void insertTree(Tree trees) throws SQLException {
		connect_func("david@gmail.com", "david1234");
		String sql = "INSERT INTO tree(Height, Size, DistanceToHouse, Location) values (? ? ? ?)";
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
		String sql = "update User set firstName=?, lastName =?,password = ?,birthday=?,adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=?, cash_bal=?, PPS_bal =? where email = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getBirthday());
		preparedStatement.setString(6, users.getAdress_street_num());
		preparedStatement.setString(7, users.getAdress_street());
		preparedStatement.setString(8, users.getAdress_city());
		preparedStatement.setString(9, users.getAdress_state());
		preparedStatement.setString(10, users.getAdress_zip_code());
		preparedStatement.setInt(11, users.getCash_bal());
		preparedStatement.setInt(12, users.getPPS_bal());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return rowUpdated;
	}

	public boolean update1(Tree trees) throws SQLException {
		String sql = "update tree set Height=?, Size=?, DistanceToHousee=?, Location=?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, trees.getHeight());
		preparedStatement.setInt(2, trees.getSize());
		preparedStatement.setInt(3, trees.getDistanceToHouse());
		preparedStatement.setString(4, trees.getLocation());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return rowUpdated;

	}

	public user getUser(String email) throws SQLException {
		user user = null;
		String sql = "SELECT * FROM User WHERE email = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String password = resultSet.getString("password");
			String birthday = resultSet.getString("birthday");
			String adress_street_num = resultSet.getString("adress_street_num");
			String adress_street = resultSet.getString("adress_street");
			String adress_city = resultSet.getString("adress_city");
			String adress_state = resultSet.getString("adress_state");
			String adress_zip_code = resultSet.getString("adress_zip_code");
			int cash_bal = resultSet.getInt("cash_bal");
			int PPS_bal = resultSet.getInt("PPS_bal");
			user = new user(email, firstName, lastName, password, birthday, adress_street_num, adress_street,
					adress_city, adress_state, adress_zip_code, cash_bal, PPS_bal);
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
		preparedStatement.setString(1, password);
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

		String[] INITIALTree = { ("CREATE TABLE  if not exists tree( " + "Height INT, " + "Size INT, "
				+ "DistanceToHouse INT, " + "Location VARCHAR(100) " + "); ") };

		String[] INITLALQuote = { ("Price DOUBLE, " + "Note VARCHAR(90000000)")

		};

		String[] INITIAL = { "drop database if exists testdb; ", "create database testdb; ", "use testdb; ",
				"drop table if exists User; ",
				("CREATE TABLE if not exists User( " + "email VARCHAR(50) NOT NULL, "
						+ "firstName VARCHAR(10) NOT NULL, " + "lastName VARCHAR(10) NOT NULL, "
						+ "password VARCHAR(20) NOT NULL, " + "birthday DATE NOT NULL, "
						+ "adress_street_num VARCHAR(4) , " + "adress_street VARCHAR(30) , "
						+ "adress_city VARCHAR(20)," + "adress_state VARCHAR(2)," + "adress_zip_code VARCHAR(5),"
						+ "cash_bal DECIMAL(13,2) DEFAULT 1000," + "PPS_bal DECIMAL(13,2) DEFAULT 0,"
						+ "PRIMARY KEY (email) " + "); ") };
		String[] TUPLES = {
				("insert into User(email, firstName, lastName, password, birthday, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, cash_bal, PPS_bal)"
						+ "values ('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '2000-06-27', '1234', 'whatever street', 'detroit', 'MI', '48202','1000', '0'),"
						+ "('don@gmail.com', 'Don', 'Cummings','don123', '1969-03-20', '1000', 'hi street', 'mama', 'MO', '12345','1000', '0'),"
						+ "('margarita@gmail.com', 'Margarita', 'Lawson','margarita1234', '1980-02-02', '1234', 'ivan street', 'tata','CO','12561','1000', '0'),"
						+ "('jo@gmail.com', 'Jo', 'Brady','jo1234', '2002-02-02', '3214','marko street', 'brat', 'DU', '54321','1000', '0'),"
						+ "('wallace@gmail.com', 'Wallace', 'Moore','wallace1234', '1971-06-15', '4500', 'frey street', 'sestra', 'MI', '48202','1000', '0'),"
						+ "('amelia@gmail.com', 'Amelia', 'Phillips','amelia1234', '2000-03-14', '1245', 'm8s street', 'baka', 'IL', '48000','1000', '0'),"
						+ "('sophie@gmail.com', 'Sophie', 'Pierce','sophie1234', '1999-06-15', '2468', 'yolos street', 'ides', 'CM', '24680','1000', '0'),"
						+ "('angelo@gmail.com', 'Angelo', 'Francis','angelo1234', '2021-06-14', '4680', 'egypt street', 'lolas', 'DT', '13579','1000', '0'),"
						+ "('rudy@gmail.com', 'Rudy', 'Smith','rudy1234', '1706-06-05', '1234', 'sign street', 'samo ne tu','MH', '09876','1000', '0'),"
						+ "('jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234', '2001-04-24', '0981', 'snoop street', 'kojik', 'HW', '87654','1000', '0'),"
						+ "('root', 'default', 'default','pass1234', '2021-03-22', '0000', 'Default', 'Default', '0', '00000','1000','1000000000'),"
						+ "('david@gmail.com', 'David','Smith','david1234', '2020-02-03', '2323', 'sookie street', 'canton', 'WA', '48189','1000','1000000000')") };

//		String[] ClIENTTUPLES = { ("insert into Client(firstName, lastName, phoneNum, address, credit_card, email, client_id )" 
//				+ "values (Het, Patel, 734-313-9090, 1111 Polaris Drive, Columbus )")
//		};

		String[] TREETUPLE = { ("INSERT INTO tree(Height, Size, DistanceToHouse, Location)"
				+ "values(2,4,6, '1111 valky road, washington'), " 
				+ "(8,10,12,  '1222 Leaf drive, california'), "
				+ "(14,16,18, '2325 wayne drive, michigan'), " 
				+ "(20,22,24, '1627 Leslie street, San Fransisco'), "
				+ "(26,28,30, '2525 Casio road, Texas'), " 
				+ "(32,34,36, '1425 Citizen street, Japan'), "
				+ "(38,40,42, '1010 Hasan street, Ohio'), " 
				+ "(44,46,48, 'Nadeshot street, Detroit'), "
				+ "(50,52,54, '1500 ludwig road, Tokyo'), " 
				+ "(56,58,60, '4292 Mercedes street, Germany')") };

		// for loop to put these in database
		for (int i = 0; i < INITIAL.length; i++)
			statement.execute(INITIAL[i]);

		for (int i = 0; i < TUPLES.length; i++)
			statement.execute(TUPLES[i]);

		for (int i = 0; i < INITIALTree.length; i++)
			statement.execute(INITIALTree[i]);

		for (int i = 0; i < TREETUPLE.length; i++) {
			statement.execute(TREETUPLE[i]);
		}

//		for (int i = 0; i < INITIALClient.length; i++)
//			statement.execute(TUPLES[i]);

		disconnect();
	}

}
