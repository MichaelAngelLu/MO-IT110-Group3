package com.oop.motorph.payrollsystem;

public class Main {

	public static void main(String[] args) {
		
//Ignore this block. For debug and testing purposes
//		PayrollHomepage payroll = new PayrollHomepage();
//		payroll.displayHomepage();
//		Employee employeeDetails = new Employee();
//		employeeDetails.displayEmployeesAdmin();
//		System.out.println(employeeDetails.employee[0].getFirstName());
//		LeaveManagement leave = new LeaveManagement();
//		leave.displayLeaveRequests();
		//Prompt user to login after application startup
		UserLogin login = new UserLogin();
		login.greetUser();
	}

}
