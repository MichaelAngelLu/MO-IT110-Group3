package com.oop.motorph.payrollsystem;

public class UserLogin extends Employee {
	
	boolean isLoggedIn;
	
	public void greetUser() {
		//Login message
    	System.out.println("Welcome to MotorPH Payroll System");
    	System.out.println("\nSign In\n");
    	insertData();
	}
	
	public void insertData() {
    	//Prompt user to input credentials, employee ID first then password
        System.out.println("Enter Employee Number: ");
        String employeeID = scan.next();
        System.out.println("Enter Password: ");
        String password = scan.next();
        
        if (verifyLogin(employeeID, password)) {
        	//Display log in success message
        	System.out.println("\n" + "Logged in successfully!" + "\n");
        	//Initialize Hompage
        	PayrollHomepage homepage = new PayrollHomepage();
        	PayrollHomepage.currentUser = Integer.parseInt(employeeID);
        	homepage.displayHomepage();
        } else {
        	System.out.println("Incorrect Employee ID/Password. Please Try Again.");
        	insertData();
        }
	}
	
	public boolean verifyLogin(String employeeID, String password) {
	    // Initialize employee details
	    Employee employeeDetails = new Employee();
	    employeeDetails.readEmployeeCsv();

	    // Ensure employeeDetails is not null
	    if (employeeDetails != null && employeeDetails.employee != null) {
	        for (int i = 0; i < employeeDetails.employee.length; i++) {
	            // Check if the current element is not null
	            if (employeeDetails.employee[i] != null) {
	                if (employeeDetails.employee[i].getEmployeeID().equals(employeeID) &&
	                    employeeDetails.employee[i].getPassword().equals(password)) {
	                    return true;
	                } else if (employeeID.equals("null") && password.equals("null")) {
	                    return false;
	                }
	            }
	        }
	    }
	    return false;
	}

}
