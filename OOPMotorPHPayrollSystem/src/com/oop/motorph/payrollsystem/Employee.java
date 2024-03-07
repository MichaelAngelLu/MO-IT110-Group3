package com.oop.motorph.payrollsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
//import com.opencsv.CSVReader;

public class Employee {
	
	Scanner scan = new Scanner(System.in);
	protected EmployeeDetails[] employee = new EmployeeDetails[34];
	private String path = "csv/EmployeeDetails.csv", line = "";
	
	public void readCsvFile() {
		//Read from csv file
		try {
	        BufferedReader br = new BufferedReader(new FileReader(path));
	        int i = 0;

	        while ((line = br.readLine()) != null) {

	            String[] values = line.split(",");
	            
	         // check if any value is empty or null
	            boolean hasEmptyValue = false;
	            for (String value : values) {
	                if (value == null || value.isEmpty()) {
	                    hasEmptyValue = true;
	                    break;
	                }
	            }

	            	if (hasEmptyValue) {
	                // skip this line
	                continue;
	            }
	            employee[i] = new EmployeeDetails(values[0], values[1], values[2], values[3], values[4], values[5],
	                    values[6], values[7], values[8], values[9], values[10], values[11], values[12], values[13],
	                    values[14], values[15], values[16], values[17], values[18], values[19], values[20]);
	            
	            i++;
	        }
	        br.close();
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//Display List of Employees w/Admin permission
	public void displayEmployeesAdmin() {
		
	//Reinitialize employee details
	readCsvFile();
	//Print details to console
		System.out.println("--------------------------------------------------------------------------------------------------");
		System.out.printf("| %-5s | %-25s | %-15s | %-15s | %-11s | %-8s |\n", "ID", "Name", "Department", "Position", "Contact", "Requests");
		System.out.println("--------------------------------------------------------------------------------------------------");
//		System.out.printf("| %-5s | %-25s | %-15s | %-15s | %-11s |\n", "10001", "Manuel III Garcia", "Executive", "CEO", "9XX-XXX-XXX");
			for (int i = 0; i < employee.length; i++) {
				System.out.printf("| %-5s | %-25s | %-15s | %-15s | %-11s | %-8s |\n", employee[i].getEmployeeID(), employee[i].getFullName(),
						employee[i].getDepartmentID(), employee[i].getPositionID(), employee[i].getPhoneNumber(), "   " + 0);
			}
		System.out.println("--------------------------------------------------------------------------------------------------");
		displaySelection();
	}
	
	public void displaySelection() {
		System.out.println("\n"
				+ "<1> Search\n"
				+ "<2> Employee Requests\n"
				+ "<3> Edit Employee\n"
				+ "<4> Add Employee\n"
				+ "<5> Remove Employee\n"
				+ "<6> Back");
		allowSelectionInput();
	}
	
	public void allowSelectionInput() {
		String i = scan.next();
		
		switch(i) {
		case "1":
			searchEmployee();
			break;
		case "6":
			PayrollHomepage homepage = new PayrollHomepage();
			homepage.displayHomepage();
			break;
		}
	}
 	
	public void searchEmployee() {
		System.out.print("Enter Employee ID: ");
		String search = scan.next();
		boolean found = false;
		EmployeeDetails foundEmployee = null;
		
		for (EmployeeDetails employee : employee) {
			if (employee.getEmployeeID().equals(search)) {
                found = true;
                foundEmployee = employee;
                break;
            }
		}
		
		// Check if the employee was found
        if (found) {
            System.out.println("Employee found:");
    		System.out.println("--------------------------------------------------------------------------------------------------");
    		System.out.printf("| %-5s | %-25s | %-15s | %-15s | %-11s | %-8s |\n", "ID", "Name", "Department", "Position", "Contact", "Requests");
    		System.out.println("--------------------------------------------------------------------------------------------------");
    		
				System.out.printf("| %-5s | %-25s | %-15s | %-15s | %-11s | %-8s |\n", foundEmployee.getEmployeeID(), foundEmployee.getFullName(),
						foundEmployee.getDepartmentID(), foundEmployee.getPositionID(), foundEmployee.getPhoneNumber(), "   " + 0);
			
		System.out.println("--------------------------------------------------------------------------------------------------");
		scan.nextLine();
		System.out.println("\nPRESS ENTER TO EXIT");
		scan.nextLine();
		displaySelection();
        } else {
            System.out.println("Employee with ID " + search + " not found.");
        }
	}
}
