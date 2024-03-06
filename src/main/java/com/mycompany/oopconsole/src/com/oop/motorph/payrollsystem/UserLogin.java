package com.oop.motorph.payrollsystem;

public class UserLogin extends Employee {
	
	boolean isLoggedIn;
	
	public void insertData() {
    	System.out.println("Welcome to MotorPH Payroll System");
        System.out.println("Enter Employee Number: ");
        String employeeID = scan.next();
        System.out.println("Enter Password: ");
        String password = scan.next();
	}
	
//	public boolean verifyLogin(String employeeID, String password) {
		
//	}
}
