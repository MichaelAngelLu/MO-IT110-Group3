package com.oop.motorph.payrollsystem;

public class PayrollHomepage extends Employee {
	
	static int currentUser;
	
	public void displayHomepage() {
		//Initialize employee details
		readCsvFile();
		//Homepage greetings and notifications
		System.out.println("Welcome, " + employee[currentUser - 10001].getFullName() + " !!!");
		System.out.println("\nDASHBOARD");
		System.out.println("You Have: ");
		System.out.println("n Pending Approval(s)");
		System.out.println("n Pending Request(s)");
		
		//Homepage menu
		System.out.println("\n" + "Homepage Menu:");
		System.out.println("<1> Dashboard");
		System.out.println("<2> Department");
		System.out.println("<3> Requests");
		System.out.println("<4> Payroll");
		System.out.println("<5> Timesheets");
		System.out.println("<6> Settings");
		System.out.println("<7> Reports");
		System.out.println("<8> Log Out");
		
		//Prompt user how to response/interact with system
		System.out.println("\nEnter selection:");
		allowInput();
	}
	
	public void allowInput() {
		String i = scan.next();
		redirectUser(i);
	}
	
	public void redirectUser(String i) {
		switch(i) {
		case "1":
			System.out.println("You chose: " + i);
			allowInput();
			break;
		case "2":
			System.out.println("You chose: " + i);
			break;
		case "3":
			System.out.println("You chose: " + i);
			break;
		case "4":
			System.out.println("You chose: " + i);
			break;
		case "5":
			System.out.println("You chose: " + i);
			break;
		case "6":
			System.out.println("You chose: " + i);
			break;
		case "7":
			System.out.println("You chose: " + i);
			break;
		case "8":
			System.out.println("\nLogging Out...\n");
			UserLogin logout = new UserLogin();
			logout.insertData();
			break;
		default:
			System.out.println("Invalid Input. Please Try Again.");
			allowInput();
		}
	}
	
}