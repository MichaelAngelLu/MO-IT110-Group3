package com.oop.motorph.payrollsystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


public class Employee {
	
	Scanner scan = new Scanner(System.in);
	protected EmployeeDetails[] employee = new EmployeeDetails[50];
	private String path = "csv/EmployeeDetails.csv";
	
	public void readEmployeeCsv() {
	    try (CSVReader reader = new CSVReader(new FileReader(path))) {
	        String[] values;
	        int i = 0;
	        while ((values = reader.readNext()) != null) {
	            // Check if any value is empty or null
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
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public int countTotalEmployees() {
	    int count = 0;
	    for (EmployeeDetails emp : employee) {
	        if (emp != null) {
	            count++;
	        }
	    }
	    return count;
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
		try {
	        for (int i = 0; i < employee.length; i++) {
	            if (employee[i] != null && employee[i].getEmployeeID() != null) {
	                System.out.printf("| %-5.5s | %-25.25s | %-15.15s | %-15.15s | %-11.11s | %-8.8s |\n", employee[i].getEmployeeID(), employee[i].getFullName(),
	                        employee[i].getDepartmentID(), employee[i].getPositionID(), employee[i].getPhoneNumber(), "   " + 0);
	            } else {
	                // Handle the case where employee[i] is null
	                continue;
	            }
	        }
	    } catch (NullPointerException e) {
	        // Handle NullPointerException
	        System.out.println("Error: NullPointerException occurred while accessing employee information.");
	        e.printStackTrace(); // Print the stack trace for debugging purposes
	    }
		System.out.println("--------------------------------------------------------------------------------------------------");
		
	}
	
	public void displaySelectionAdmin() {
		//Reinitialize employee details
		readEmployeeCsv();
		if (hasAccess()) {
		System.out.println("\n"
				+ "<1> Search\n"
				+ "<2> Employee Requests\n"
				+ "<3> Edit Employee\n"
				+ "<4> Add Employee\n"
				+ "<5> Remove Employee\n"
				+ "<6> Back");
		System.out.print("\nEnter selection: ");
		} else {
			System.out.println("\n"
					+ "<1> Search\n"
					+ "<2> Back");
			System.out.print("\nEnter selection: ");
		}
		allowSelectionInput();
	}
	
	public void allowSelectionInput() {
		String i = scan.next();
		
		switch(i) {
		case "1":
			searchEmployee();
			break;
		case "2":
			if (hasAccess()) {
				displayRequestMenu();
			} else {
				PayrollHomepage homepage = new PayrollHomepage();
				homepage.displayHomepage();
				break;
			}
			break;
		case "3":
			if (hasAccess()) {
			inputEmployeeToEdit();
			} else {
				System.out.println("Invalid Input. Please Try Again.");
				allowSelectionInput();
			}
			break;
		case "4":
			if (hasAccess()) {
			addEmployee();
			} else {
				System.out.println("Invalid Input. Please Try Again.");
				allowSelectionInput();
			}
			break;
		case "5":
			if (hasAccess()) {
			System.out.print("\nEnter Employee ID of Employee to Remove from Employee List: ");
			String employee, response;
			int index; 
			employee = scan.next();
			index = Integer.parseInt(employee) - 10001;
			if (employee != null) {
				System.out.println("Are you sure your want to remove Employee " + employee + " from list? (Y/N)");
				response = scan.next();
				if (response.equalsIgnoreCase("Y")) {
					removeEmployee(index);
					System.out.println("Employee successfully removed from employee list.");
					displayEmployeesAdmin();
					displaySelectionAdmin();
				} else {
					System.out.println("Removal Cancelled. Redirecting User to Employee Menu.");
					displaySelectionAdmin();
				}
			}
			} else {
				System.out.println("Invalid Input. Please Try Again.");
				allowSelectionInput();
			}
			break;
		case "6":
			if (hasAccess()) {
			PayrollHomepage homepage = new PayrollHomepage();
			homepage.displayHomepage();
			break;
			} else {
				System.out.println("Invalid Input. Please Try Again.");
				allowSelectionInput();
			}
		default:
			System.out.println("Invalid Input. Please Try Again.");
			allowSelectionInput();
			break;
		}
	}
	
	public boolean hasAccess() {
		return employee[PayrollHomepage.currentUser - 10001].getAccessRole().equals("admin");
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
			OvertimeManagement ot = new OvertimeManagement();
			ot.displayOvertimeRequests();
			break;
		case "3":
			displaySelectionAdmin();
			break;
		default:
			System.out.println("Invalid Input. Please Try Again.");
			displayRequestMenu();
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
		            
		            System.out.print("New Department (current: " + employee[index].getDepartmentID() + "): ");
		            String newDepartmentID = scan.nextLine();
		            if (!newDepartmentID.isEmpty()) {
		                employee[index].setPositionID(newDepartmentID);
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
					writeEmployeeCsv(path);
					
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
	
	public void addEmployee() {
		//Auto-increment new employee ID
		String newEmployeeID = countTotalEmployees() + 10001 + "";
		int index = countTotalEmployees() + 1;
		
		//Create new instance for new employee
		if (this.employee[index] == null) {
		    this.employee[index] = new EmployeeDetails(newEmployeeID, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "temp", "password"); // Create a new EmployeeDetails object
		}
		
		System.out.println("\nAdd Employee " + newEmployeeID + " to System:");
		System.out.println("Please fill all information to complete this process.\n");
		
		String[] fieldNames = {"First Name", "Last Name", "Birthday", "Address", "Phone Number", "Employee Status",
		        "Department ID", "Position ID", "Immediate Supervisor ID", "SSS #", "PhilHealth #",
		        "TIN #", "Pag-Ibig #", "Basic Salary", "Hourly Rate", "Rice Subsidy", "Phone Allowance",
		        "Clothing Allowance"};
		scan.nextLine();
		for (String fieldName : fieldNames) {
		    String fieldValue;
		    boolean invalidInput = false;
		    do {
		        System.out.print(fieldName + ": ");
		        fieldValue = scan.nextLine();

		        if (fieldValue.isEmpty()) {
		            System.out.println("Please enter a non-empty value.");
		        } else {
		            if (fieldName.equals("Basic Salary") || fieldName.equals("Hourly Rate") || fieldName.equals("Rice Subsidy")
		                    || fieldName.equals("Phone Allowance") || fieldName.equals("Clothing Allowance")) {
		                // Check if user input in fieldValue variable is a number
		                try {
		                    float fieldValueFloat = Float.parseFloat(fieldValue);
		                    // If the conversion succeeds, set the field value
		                    invalidInput = false;
		                    setFieldValue(fieldName, fieldValue, index);
		                } catch (NumberFormatException e) {
		                    System.out.println("Please enter a valid number for " + fieldName);
		                    invalidInput = true;
		                    continue;
		                }
		            } else {
		            	invalidInput = false;
		                // Set the field value based on the field name
		                setFieldValue(fieldName, fieldValue, index);
		            }
		        }
		    } while (fieldValue.isEmpty() || invalidInput == true);
		}
		
		displayCurrentInfo(newEmployeeID, index);
		System.out.println("\nDo you want to save new employee record? (Y/N)");
		String confirm = scan.next();
		if (confirm.equalsIgnoreCase("Y")) {
			//Write to csv file
			writeEmployeeCsv(path);
			
			//Print message once changes has been saved
			System.out.println("Changes saved successfully!");
			displaySelectionAdmin();
		} else if (confirm.equalsIgnoreCase("N")) {
			employee[index] = null;
			addEmployee();
		}
	}
	
	public void removeEmployee(int index) {
		this.employee[index] = null;
		writeEmployeeCsv(path);
	}
	
	private void setFieldValue(String fieldName, String fieldValue, int index) {
	    switch (fieldName) {
	        case "First Name":
	            employee[index].setFirstName(fieldValue);
	            break;
	        case "Last Name":
	            employee[index].setLastName(fieldValue);
	            break;
	        case "Birthday":
	            employee[index].setBirthday(fieldValue);
	            break;
	        case "Address":
	            employee[index].setAddress(fieldValue);
	            break;
	        case "Phone Number":
	            employee[index].setPhoneNumber(fieldValue);
	            break;
	        case "Employee Status":
	            employee[index].setEmployeeStatus(fieldValue);
	            break;
	        case "Department ID":
	            employee[index].setDepartmentID(fieldValue);
	            break;
	        case "Position ID":
	            employee[index].setPositionID(fieldValue);
	            break;
	        case "Immediate Supervisor ID":
	            employee[index].setImmediateSupervisorID(fieldValue);
	            break;
	        case "SSS #":
	            employee[index].setSSSNo(fieldValue);
	            break;
	        case "PhilHealth #":
	            employee[index].setPhilHealthNo(fieldValue);
	            break;
	        case "TIN #":
	            employee[index].setTINNo(fieldValue);
	            break;
	        case "Pag-Ibig #":
	            employee[index].setPagibigNo(fieldValue);
	            break;
	        case "Basic Salary":
	            employee[index].setBasicSalary(fieldValue);
	            break;
	        case "Hourly Rate":
	            employee[index].setHourlyRate(fieldValue);
	            break;
	        case "Rice Subsidy":
	            employee[index].setRiceSubsidy(fieldValue);
	            break;
	        case "Phone Allowance":
	            employee[index].setPhoneAllowance(fieldValue);
	            break;
	        case "Clothing Allowance":
	            employee[index].setClothingAllowance(fieldValue);
	            break;
	        default:
	            break;
	    }
	}
	
	public void writeEmployeeCsv(String outputPath) {
	    try (CSVWriter writer = new CSVWriter(new FileWriter(outputPath))) {
	        for (EmployeeDetails emp : employee) {
	            if (emp != null) {
	                String[] data = {
	                		emp.getEmployeeID(), emp.getFirstName(), emp.getLastName(),
	                        emp.getBirthday(), emp.getAddress(), emp.getPhoneNumber(),
	                        emp.getEmployeeStatus(), emp.getDepartmentID(), emp.getPositionID(),
	                        emp.getimmediateSupervisorID(), emp.getSSSNo(), emp.getPhilHealthNo(),
	                        emp.getTinNo(), emp.getPagibigNo(), emp.getBasicSalary(),
	                        emp.getRiceSubsidy(), emp.getPhoneAllowance(), emp.getClothingAllowance(),
	                        emp.getHourlyRate(), emp.getAccessRole(), emp.getPassword()
	                };
	                writer.writeNext(data);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
}
