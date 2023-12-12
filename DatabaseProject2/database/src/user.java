public class user {
	protected int id;
	protected String firstName;
	protected String lastName;
	protected String creditCard;
	protected String email;
	protected String password;

	// constructors
	public user() {
	}

	public user(String email) {
		this.email = email;
	}

	public user(int id, String firstName, String lastName, String creditCard, String email, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.creditCard = creditCard;
		this.email = email;
		this.password = password;
	}

	// getter and setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setcreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}