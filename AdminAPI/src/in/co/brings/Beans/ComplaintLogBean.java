package in.co.brings.Beans;

import java.sql.Timestamp;

public class ComplaintLogBean {

	private int id;
	private int complaintId;
	
	private String extraInfo1;
	private String extraInfo2;
	private Timestamp effectiveDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
	
	public String getExtraInfo1() {
		return extraInfo1;
	}
	public void setExtraInfo1(String extraInfo1) {
		this.extraInfo1 = extraInfo1;
	}
	public String getExtraInfo2() {
		return extraInfo2;
	}
	public void setExtraInfo2(String extraInfo2) {
		this.extraInfo2 = extraInfo2;
	}
	public Timestamp getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Timestamp effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}
