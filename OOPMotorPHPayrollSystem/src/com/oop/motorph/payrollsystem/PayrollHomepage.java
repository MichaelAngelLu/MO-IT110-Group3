package com.oop.motorph.payrollsystem;

public class PayrollHomepage extends Employee {
	
	static int currentUser;
	
	public void displayHomepage() {
		//Check if user is logged in
		if (currentUser == 0) {
			displayError(1);
		}
		//Initialize employee details
		readEmployeeCsv();
		//Homepage greetings and notifications
		System.out.println("Welcome, " + employee[currentUser - 10001].getFullName() + " !!!");

		//Homepage menu
		System.out.println("\n" + "Homepage Menu:");
		System.out.println("<1> Leaves");
		System.out.println("<2> Overtime");
		System.out.println("<3> Payslip");
		System.out.println("<4> Employees");
		System.out.println("<5> Log Out");
		
		//Prompt user how to response/interact with system
		System.out.print("\nEnter selection: ");
		allowHomepageInput();
	}
	
	public void redirectFromHomepage(String i) {
		switch(i) {
		case "1":
			EmployeeLeave leaveemployee = new EmployeeLeave();
            leaveemployee.AccessLeave();
			allowHomepageInput();
			break;
		case "2":
			EmployeeOvertime overtime = new EmployeeOvertime();
			overtime.AccessOvertime();
			break;
		case "3":
			Payslip payslip = new Payslip();
			payslip.cutOffViewer();
			break;
		case "4":
			displayEmployeesAdmin();
			displaySelectionAdmin();
			break;
		case "5":
			System.out.println("\nLogging Out...\n");
			UserLogin logout = new UserLogin();
			logout.insertData();
			break;
		default:
			System.out.println("Invalid Input. Please Try Again.");
			allowHomepageInput();
		}
	}

	

	public void allowHomepageInput() {
		String i = scan.next();
		redirectFromHomepage(i);
	}
	
	
	public void displayError(int type) {
		switch (type) {
		case 1:
			System.out.println("You must login to continue. Press \"ENTER\" key to proceed to login. >>");
			scan.nextLine();
			UserLogin login = new UserLogin();
			login.greetUser();
		}
	}
	
}
