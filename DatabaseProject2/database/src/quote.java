import java.sql.Timestamp;

public class quote {

	protected int id;
	protected int contractorID;
	protected double price;
	protected Timestamp scheduleStart;
	protected Timestamp scheduleEnd;

	public quote() {

	}

	public quote(int id, int contractorID, double price, Timestamp scheduleStart, Timestamp scheduleEnd) {
		this.id = id;
		this.contractorID = contractorID;
		this.price = price;
		this.scheduleStart = scheduleStart;
		this.scheduleEnd = scheduleEnd;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getContractorID() {
		return contractorID;
	}

	public void setContractorID(int contractorID) {
		this.contractorID = contractorID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setScheduleStart(Timestamp scheduleStart) {
		this.scheduleStart = scheduleStart;
	}

	public Timestamp getScheduleStart() {
		return scheduleStart;
	}

	public void setScheduleEnd(Timestamp scheduleEnd) {
		this.scheduleEnd = scheduleEnd;
	}

	public Timestamp getScheduleEnd() {
		return scheduleEnd;
	}
}