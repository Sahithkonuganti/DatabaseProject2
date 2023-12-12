public class QuoteRespond {
	protected int id;
	protected int quoteid;
	protected double price;
	protected String note;
	protected String email;

	public QuoteRespond() {

	}

	public QuoteRespond(int id, int quoteid, double price, String note, String email) {
		this.id = id;
		this.quoteid = quoteid;
		this.price = price;
		this.note = note;
		this.email = email;
	}

	public QuoteRespond(int id, int quoteid, String note, String email) {
		this.id = id;
		this.quoteid = quoteid;
		this.note = note;
		this.email = email;
	}

	// setters and getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuoteid() {
		return quoteid;
	}

	public void setQuoteid(int quoteid) {
		this.quoteid = quoteid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}