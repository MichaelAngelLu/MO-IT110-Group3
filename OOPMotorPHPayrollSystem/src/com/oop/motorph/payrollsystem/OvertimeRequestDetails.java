package com.oop.motorph.payrollsystem;

public class OvertimeRequestDetails {
	
	private String overtimeID;
	private String employeeID;
	private String otDate;
	private String otStart;
	private String otEnd;
	private String duration;
	private String reason;
	private String approvalStatus;
	private String approvedDate;
	private String approver;
	
	public OvertimeRequestDetails(String overtimeID, String employeeID, String otDate, String otStart, String otEnd,
			String duration, String reason, String approvalStatus, String approvedDate, String approver) {

		this.overtimeID = overtimeID;
		this.employeeID = employeeID;
		this.otDate = otDate;
		this.otStart = otStart;
		this.otEnd = otEnd;
		this.duration = duration;
		this.reason = reason;
		this.approvalStatus = approvalStatus;
		this.approvedDate = approvedDate;
		this.approver = approver;
	}

	public String getOvertimeID() {
		return overtimeID;
	}

	public void setOvertimeID(String overtimeID) {
		this.overtimeID = overtimeID;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getOtDate() {
		return otDate;
	}

	public void setOtDate(String otDate) {
		this.otDate = otDate;
	}

	public String getOtStart() {
		return otStart;
	}

	public void setOtStart(String otStart) {
		this.otStart = otStart;
	}

	public String getOtEnd() {
		return otEnd;
	}

	public void setOtEnd(String otEnd) {
		this.otEnd = otEnd;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}
	
	
	
}
