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
		System.out.println("\nDASHBOARD");
		System.out.println("You Have: ");
		System.out.println("n Pending Approval(s)");
		System.out.println("0 Pending Request(s)");
		
		//Homepage menu
		System.out.println("\n" + "Homepage Menu:");
		System.out.println("<1> Dashboard");
		System.out.println("<2> Department");
		System.out.println("<3> Requests");
		System.out.println("<4> Payroll");
		System.out.println("<5> Employees");
		System.out.println("<6> Timesheets");
		System.out.println("<7> Settings");
		System.out.println("<8> Reports");
		System.out.println("<9> Log Out");
		
		//Prompt user how to response/interact with system
		System.out.print("\nEnter selection: ");
		allowHomepageInput();
	}
	
	public void allowHomepageInput() {
		String i = scan.next();
		redirectFromHomepage(i);
	}
	
	public void redirectFromHomepage(String i) {
		switch(i) {
		case "1":
			System.out.println("You chose: " + i); // For debug/testing only. Remove this after finishing this part.
			allowHomepageInput();
			break;
		case "2":
			System.out.println("You chose: " + i); // For debug/testing only. Remove this after finishing this part.
			break;
		case "3":
			displayRequestsMenu();
			break;
		case "4":
			System.out.println("You chose: " + i); // For debug/testing only. Remove this after finishing this part.
			break;
		case "5":
			displayEmployeesAdmin();
			break;
		case "6":
			System.out.println("You chose: " + i); // For debug/testing only. Remove this after finishing this part.
			break;
		case "7":
			System.out.println("You chose: " + i); // For debug/testing only. Remove this after finishing this part.
			break;
		case "8":
			System.out.println("You chose: " + i); // For debug/testing only. Remove this after finishing this part.
			break;
		case "9":
			System.out.println("\nLogging Out...\n");
			UserLogin logout = new UserLogin();
			logout.insertData();
			break;
		default:
			System.out.println("Invalid Input. Please Try Again.");
			allowHomepageInput();
		}
	}
	
	public void displayRequestsMenu() {
		//Requests menu
				System.out.println("\n" + "Requests:");
				System.out.println("<1> Leave");
				System.out.println("<2> Overtime");
				System.out.println("<3> Payslips");
				System.out.print("\nEnter Selection: ");
				allowRequestsInput();
	}
	
	public void allowRequestsInput() {
		String i = scan.next();
		redirectFromRequests(i);
	}
	
	public void redirectFromRequests(String i) {
		switch(i) {
		case "1":
            EmployeeLeave leaveemployee = new EmployeeLeave();
            leaveemployee.AccessLeave();
			break;
		}
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
