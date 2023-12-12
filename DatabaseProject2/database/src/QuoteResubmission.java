public class QuoteResubmission {
	protected int id;
	protected int quoteid;
	protected String note;
	protected String email;

	public QuoteResubmission() {

	}

	public QuoteResubmission(int id, int quoteid, String note, String email) {
		this.id = id;
		this.quoteid = quoteid;
		this.note = note;
		this.email = email;
	}

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