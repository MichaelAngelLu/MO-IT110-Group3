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
	
	public void readEmployeeCsv() {
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
	readEmployeeCsv();
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
		displaySelectionAdmin();
	}
	
	public void displaySelectionAdmin() {
		System.out.println("\n"
				+ "<1> Search\n"
				+ "<2> Employee Requests\n"
				+ "<3> Edit Employee\n"
				+ "<4> Add Employee\n"
				+ "<5> Remove Employee\n"
				+ "<6> Back");
		System.out.print("\nEnter selection: ");
		allowSelectionInput();
	}
	
	public void allowSelectionInput() {
		String i = scan.next();
		
		switch(i) {
		case "1":
			searchEmployee();
			break;
		case "2":
			displayRequestMenu();
			break;
		case "3":
			inputEmployeeToEdit();
			break;
		case "6":
			PayrollHomepage homepage = new PayrollHomepage();
			homepage.displayHomepage();
			break;
		}
	}
	
	public void displayRequestMenu() {
		System.out.print("\nEmployee Requests:\n"
				+ "<1> Leave Requests\n"
				+ "<2> Overtime Requests\n"
				+ "<3> Back\n\n"
				+ "Enter selection: ");
		String i = scan.next();
		switch (i) {
		case "1":
			LeaveManagement leaves = new LeaveManagement();
			leaves.displayLeaveRequests();
			break;
		case "2":
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
		System.out.println("\nPRESS ENTER TO EXIT >>");
		scan.nextLine();
		displaySelectionAdmin();
        } else {
            System.out.println("Employee with ID '" + search + "' not found.");
            searchEmployee();
        }
	}
	
	public void inputEmployeeToEdit() {
		System.out.print("\nEnter ID of Employee to Edit: ");
		String edit = scan.next();
		editEmployeeInfo(edit);
	}
	
	public void editEmployeeInfo(String edit) {
		
		boolean found = false;
		
		for (EmployeeDetails employee : employee) {
			if (employee.getEmployeeID().equals(edit)) {
                found = true;
                break;
            }
		}
				if (found) {
					int index = Integer.parseInt(edit) - 10001;
					
					//Print current employee details
					displayCurrentInfo(edit, index);
					
					System.out.println("Enter new information (or leave blank to keep current):");
					String newFirstName = scan.nextLine();
		            System.out.print("New First Name (current: " + employee[index].getFirstName() + "): ");
		            newFirstName = scan.nextLine();
		            if (!newFirstName.isEmpty()) {
		            	employee[index].setFirstName(newFirstName);
		            } //Skip if blank
		            
		            System.out.print("New Last Name (current: " + employee[index].getLastName() + "): ");
		            String newLastName = scan.nextLine();
		            if (!newLastName.isEmpty()) {
		            	employee[index].setLastName(newLastName);
		            } //Skip if blank
		            
		            System.out.print("New Birthday (current: " + employee[index].getBirthday() + "): ");
		            String newBirthday = scan.nextLine();
		            if (!newBirthday.isEmpty()) {
		                employee[index].setBirthday(newBirthday);
		            } // Skip if blank

		            System.out.print("New Address (current: " + employee[index].getAddress() + "): ");
		            String newAddress = scan.nextLine();
		            if (!newAddress.isEmpty()) {
		                employee[index].setAddress(newAddress);
		            } // Skip if blank

		            System.out.print("New Phone Number (current: " + employee[index].getPhoneNumber() + "): ");
		            String newPhoneNumber = scan.nextLine();
		            if (!newPhoneNumber.isEmpty()) {
		                employee[index].setPhoneNumber(newPhoneNumber);
		            } // Skip if blank

		            System.out.print("New SSS # (current: " + employee[index].getSSSNo() + "): ");
		            String newSSSNo = scan.nextLine();
		            if (!newSSSNo.isEmpty()) {
		                employee[index].setSSSNo(newSSSNo);
		            } // Skip if blank

		            System.out.print("New Philhealth # (current: " + employee[index].getPhilHealthNo() + "): ");
		            String newPhilhealthNo = scan.nextLine();
		            if (!newPhilhealthNo.isEmpty()) {
		                employee[index].setPhilHealthNo(newPhilhealthNo);
		            } // Skip if blank

		            System.out.print("New TIN # (current: " + employee[index].getTinNo() + "): ");
		            String newTinNo = scan.nextLine();
		            if (!newTinNo.isEmpty()) {
		                employee[index].setTINNo(newTinNo);
		            } // Skip if blank

		            System.out.print("New Pag-ibig # (current: " + employee[index].getPagibigNo() + "): ");
		            String newPagibigNo = scan.nextLine();
		            if (!newPagibigNo.isEmpty()) {
		                employee[index].setPagibigNo(newPagibigNo);
		            } // Skip if blank

		            System.out.print("New Status (current: " + employee[index].getEmployeeStatus() + "): ");
		            String newStatus = scan.nextLine();
		            if (!newStatus.isEmpty()) {
		                employee[index].setEmployeeStatus(newStatus);
		            } // Skip if blank

		            System.out.print("New Position (current: " + employee[index].getPositionID() + "): ");
		            String newPositionID = scan.nextLine();
		            if (!newPositionID.isEmpty()) {
		                employee[index].setPositionID(newPositionID);
		            } // Skip if blank

		            System.out.print("New Immediate Supervisor (current: " + employee[index].getimmediateSupervisorID() + "): ");
		            String newSupervisorID = scan.nextLine();
		            if (!newSupervisorID.isEmpty()) {
		                employee[index].setImmediateSupervisorID(newSupervisorID);
		            } // Skip if blank

		            System.out.print("New Basic Salary (current: " + employee[index].getBasicSalary() + "): ");
		            String newBasicSalary = scan.nextLine();
		            if (!newBasicSalary.isEmpty()) {
		                employee[index].setBasicSalary(newBasicSalary);
		            } // Skip if blank

		            System.out.print("New Hourly Rate (current: " + employee[index].getHourlyRate() + "): ");
		            String newHourlyRate = scan.nextLine();
		            if (!newHourlyRate.isEmpty()) {
		                employee[index].setHourlyRate(newHourlyRate);
		            } // Skip if blank

		            System.out.print("New Rice Subsidy (current: " + employee[index].getRiceSubsidy() + "): ");
		            String newRiceSubsidy = scan.nextLine();
		            if (!newRiceSubsidy.isEmpty()) {
		                employee[index].setRiceSubsidy(newRiceSubsidy);
		            } // Skip if blank

		            System.out.print("New Phone Allowance (current: " + employee[index].getPhoneAllowance() + "): ");
		            String newPhoneAllowance = scan.nextLine();
		            if (!newPhoneAllowance.isEmpty()) {
		                employee[index].setPhoneAllowance(newPhoneAllowance);
		            } // Skip if blank

		            System.out.print("New Clothing Allowance (current: " + employee[index].getClothingAllowance() + "): ");
		            String newClothingAllowance = scan.nextLine();
		            if (!newClothingAllowance.isEmpty()) {
		                employee[index].setClothingAllowance(newClothingAllowance);
		            } // Skip if blank
		            
		            displayCurrentInfo(edit, index);
		            
				} else {
		            System.out.println("Employee with ID '" + edit + "' not found.");
		            inputEmployeeToEdit();
		        }
				
				System.out.println("\nDo you want to save these changes? (Y/N)");
				String confirm = scan.next();
				if (confirm.equalsIgnoreCase("Y")) {
					//Write to csv file
					//Write code here
					
					//Print message once changes has been saved
					System.out.println("Changes saved successfully!");
					displaySelectionAdmin();
				} else if (confirm.equalsIgnoreCase("N")) {
					editEmployeeInfo(edit);
				}
		
	}
	
	public void displayCurrentInfo(String edit, int index) {
		String employeeIS;
		if (employee[index].getimmediateSupervisorID().equals("none")) {
			employeeIS = "none";
		} else {
			employeeIS = employee[Integer.parseInt(employee[index].getimmediateSupervisorID())-10001].getFullName();
		}
		//Print current employee details
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("|                         CURRENT INFORMATION - EMPLOYEE " + edit + "                         |");
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("|                                 Personal Information                                 |");
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.printf("| %-20s | %-61.61s |\n", "Employee ID", employee[index].getEmployeeID());
		System.out.printf("| %-20s | %-61.61s |\n", "First Name", employee[index].getFirstName());
		System.out.printf("| %-20s | %-61.61s |\n", "Last Name", employee[index].getLastName());
		System.out.printf("| %-20s | %-61.61s |\n", "Birthday", employee[index].getBirthday());
		System.out.printf("| %-20s | %-61.61s |\n", "Address", employee[index].getAddress());
		System.out.printf("| %-20s | %-61.61s |\n", "Phone Number", employee[index].getPhoneNumber());
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("|                            Employment-Related Information                            |");
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.printf("| %-20s | %-61.61s |\n", "Employee Status", employee[index].getEmployeeStatus());
		System.out.printf("| %-20s | %-61.61s |\n", "Department", employee[index].getDepartmentID());
		System.out.printf("| %-20s | %-61.61s |\n", "Position", employee[index].getPositionID());
		System.out.printf("| %-20s | %-61.61s |\n", "Immediate Supervisor", employeeIS);
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("|                               Governmental Dues Details                              |");
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.printf("| %-20s | %-61.61s |\n", "SSS #", employee[index].getSSSNo());
		System.out.printf("| %-20s | %-61.61s |\n", "PhilHealth #", employee[index].getPhilHealthNo());
		System.out.printf("| %-20s | %-61.61s |\n", "TIN #", employee[index].getTinNo());
		System.out.printf("| %-20s | %-61.61s |\n", "Pag-Ibig #", employee[index].getPagibigNo());
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("|                                  Salary Information                                  |");
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.printf("| %-20s | PHP %-57.2f |\n", "Basic Salary", Float.parseFloat(employee[index].getBasicSalary()));
		System.out.printf("| %-20s | PHP %-57.2f |\n", "Hourly Rate", Float.parseFloat(employee[index].getHourlyRate()));
		System.out.printf("| %-20s | PHP %-57.2f |\n", "Rice Subsidy", Float.parseFloat(employee[index].getRiceSubsidy()));
		System.out.printf("| %-20s | PHP %-57.2f |\n", "Phone Allowance", Float.parseFloat(employee[index].getPhoneAllowance()));
		System.out.printf("| %-20s | PHP %-57.2f |\n", "Clothing Allowance", Float.parseFloat(employee[index].getClothingAllowance()));
		System.out.println("----------------------------------------------------------------------------------------");
	}
}
