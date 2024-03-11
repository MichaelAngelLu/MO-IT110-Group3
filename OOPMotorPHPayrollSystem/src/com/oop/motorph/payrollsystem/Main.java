package com.oop.motorph.payrollsystem;

public class Main {

	public static void main(String[] args) {
		
//Ignore this block. For debug and testing purposes
//		PayrollHomepage payroll = new PayrollHomepage();
//		payroll.displayHomepage();
//		Employee employeeDetails = new Employee();
//		employeeDetails.readEmployeeCsv();
//		employeeDetails.displayEmployeesAdmin();
//		employeeDetails.displaySelectionAdmin();
//		employeeDetails.inputEmployeeToEdit();
//		System.out.println(employeeDetails.employee[0].getFirstName());
//		LeaveManagement leave = new LeaveManagement();
//		leave.displayLeaveRequests();
//		Payslip payslip = new Payslip();
//		payslip.cutOffViewer();
		
		//Prompt user to login after application startup
		UserLogin login = new UserLogin();
		login.greetUser();
	}

}
