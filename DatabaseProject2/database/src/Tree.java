public class Tree {

	protected int id;
	protected int quoteId;
	protected double size;
	protected double height;
	protected String location;
	protected double distanceFromHouse;
	protected String email;

	public Tree() {

	}

	public Tree(int id, int quoteId, double size, double height, String location, double distanceFromHouse,
			String email) {
		this.id = id;
		this.quoteId = quoteId;
		this.size = size;
		this.height = height;
		this.distanceFromHouse = distanceFromHouse;
		this.location = location;
		this.email = email;
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getDistanceFromHouse() {
		return distanceFromHouse;
	}

	public void setDistanceFromHouse(double distanceFromHouse) {
		this.distanceFromHouse = distanceFromHouse;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

}