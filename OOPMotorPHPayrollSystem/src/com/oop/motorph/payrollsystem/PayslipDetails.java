package com.oop.motorph.payrollsystem;

public class PayslipDetails {
	
	//Payslip information
	private String payslipID;
	private String employeeID;
	private String departmentID;
	private String positionID;
	private String cutoffDate;
	private String grossSalary;
	private String benefits;
	private String deductions;
	private String netSalary;
	private String approvalStatus;
	private String dateApproved;
	private String HRemployeeID;
	
	public PayslipDetails(String payslipID, String employeeID, String departmentID, String positionID, String cutoffDate, String grossSalary, String benefits,
			String deductions, String netSalary, String approvalStatus, String dateApproved, String hRemployeeID) {
		
		this.payslipID = payslipID;
		this.employeeID = employeeID;
		this.departmentID = departmentID;
		this.positionID = positionID;
		this.cutoffDate = cutoffDate;
		this.grossSalary = grossSalary;
		this.benefits = benefits;
		this.deductions = deductions;
		this.netSalary = netSalary;
		this.approvalStatus = approvalStatus;
		this.dateApproved = dateApproved;
		this.HRemployeeID = hRemployeeID;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	public String getPositionID() {
		return positionID;
	}

	public void setPositionID(String positionID) {
		this.positionID = positionID;
	}

	public String getPayslipID() {
		return payslipID;
	}

	public void setPayslipID(String payslipID) {
		this.payslipID = payslipID;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getCutoffDate() {
		return cutoffDate;
	}

	public void setCutoffDate(String cutoffDate) {
		this.cutoffDate = cutoffDate;
	}

	public String getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(String grossSalary) {
		this.grossSalary = grossSalary;
	}

	public String getBenefits() {
		return benefits;
	}

	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}

	public String getDeductions() {
		return deductions;
	}

	public void setDeductions(String deductions) {
		this.deductions = deductions;
	}

	public String getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(String netSalary) {
		this.netSalary = netSalary;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getDateApproved() {
		return dateApproved;
	}

	public void setDateApproved(String dateApproved) {
		this.dateApproved = dateApproved;
	}

	public String getHRemployeeID() {
		return HRemployeeID;
	}

	public void setHRemployeeID(String hRemployeeID) {
		HRemployeeID = hRemployeeID;
	}
	
}
