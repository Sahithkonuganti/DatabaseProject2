public class Revenue {
	protected int id;
	protected double payment;
	protected int billid;
	protected String timepaid;
	protected String email;

	public Revenue() {

	}

	public Revenue(int id, int billid, double payment, String timepaid, String email) {
		this.id = id;
		this.payment = payment;
		this.billid = billid;
		this.timepaid = timepaid;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public int getBillid() {
		return billid;
	}

	public void setBillid(int billid) {
		this.billid = billid;
	}

	public String getTimepaid() {
		return timepaid;
	}

	public void setTimepaid(String timepaid) {
		this.timepaid = timepaid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}