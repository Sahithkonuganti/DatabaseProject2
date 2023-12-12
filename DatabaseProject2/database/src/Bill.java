public class Bill {
	protected int id;
	protected int billid;
	protected double bill;
	protected String status;
	protected String email;

	public Bill() {

	}

	public Bill(int id, int billid, double bill, String status, String email) {
		this.id = id;
		this.billid = billid;
		this.bill = bill;
		this.status = status;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBillid() {
		return billid;
	}

	public void setBillid(int billid) {
		this.billid = billid;
	}

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}