package com.oop.motorph.payrollsystem;

public class LeaveRequestDetails {
	
	private String leaveApplicationID;
	private String employeeID;
	private String leaveType;
	private String reason;
	private String startDate;
	private String endDate;
	private String requestDate;
	private String approvalStatus;
	private String HRemployeeID;
	private String comments;
	
	public LeaveRequestDetails(String leaveApplicationID, String employeeID, String leaveType, String reason,
			String startDate, String endDate, String requestDate, String approvalStatus, String hRemployeeID,
			String comments) {

		this.leaveApplicationID = leaveApplicationID;
		this.employeeID = employeeID;
		this.leaveType = leaveType;
		this.reason = reason;
		this.startDate = startDate;
		this.endDate = endDate;
		this.requestDate = requestDate;
		this.approvalStatus = approvalStatus;
		this.HRemployeeID = hRemployeeID;
		this.comments = comments;
	}
	
	//Getters and Setters
	public String getLeaveApplicationID() {
		return leaveApplicationID;
	}

	public void setLeaveApplicationID(String leaveApplicationID) {
		this.leaveApplicationID = leaveApplicationID;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getHRemployeeID() {
		return HRemployeeID;
	}

	public void setHRemployeeID(String hRemployeeID) {
		HRemployeeID = hRemployeeID;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
	
}
