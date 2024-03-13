package com.oop.motorph.payrollsystem;

/**
*
* @author Tone
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import com.opencsv.CSVWriter;

public class EmployeeLeave extends Employee {
	
	private static final String CSV_FILE = "csv/LeaveRequests.csv";
	LeaveManagement leavesClass = new LeaveManagement();
	LeaveRequestDetails[] leaves;
	String dateFormat = "yyyy-MM-dd";
	int slCount, elCount, vlCount; // Employee leave credits variables
   
   public void AccessLeave(){
	   //Initialize leave details
	   leavesClass.readLeaveCsv();
	   leaves = Arrays.copyOf(leavesClass.leaves,leavesClass.leaves.length);
	   //Initialize employee details
	   readEmployeeCsv();
	   	
		//Homepage menu
		System.out.println("\nLeave Menu:");
		System.out.println("<1> Check Leave Credits and Pending Leaves");
		System.out.println("<2> Apply For Leave");
		System.out.println("<3> Back");		
		//Prompt user how to response/interact with system
		System.out.print("\nEnter selection: ");
		allowInput();
   }
   
   public void allowInput() {
		String i = scan.next();
		redirectUser(i);
	}
   
   public void redirectUser(String i) {
		switch(i) {
        case "1":
			CheckLeaveCredits();
			break;
		case "2":
            ApplyLeave();
			break;
		case "3":
			PayrollHomepage homepage = new PayrollHomepage();
			homepage.displayHomepage();
			break;
               
               }
   }            

   public void CheckLeaveCredits(){
	   System.out.println("\nCheck Leave Credits");
	   //Initialize leave details
	   leavesClass.readLeaveCsv();  
	   //Initialize employee details
	   readEmployeeCsv();
	   //Count leave balances
	   slCount = countLeaveCredits("Sick", 5);
	   elCount = countLeaveCredits("Emergency", 10);
	   vlCount = countLeaveCredits("Vacation", 5);
	   //Print leave balances
	   System.out.println("Sick Leaves: " + slCount);
	   System.out.println("Emergency Leaves: " + elCount);
	   System.out.println("Vacation Leaves: " + vlCount);
	   System.out.println("\nCheck Pending Leaves");
	 //Check if there is data in the storage. Proceed to print if there are
		boolean hasRecord = false;
		for (LeaveRequestDetails requests : leaves) {
			if (requests != null && requests.getEmployeeID().equals(String.valueOf(PayrollHomepage.currentUser))) {
				if (requests.getApprovalStatus().equals("Pending")) {
					hasRecord = true;
				}
			}
		}
		
		if (hasRecord) {
 			//Print details to console
 			System.out.println(
 					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
 			System.out.printf(
 					"| %-8s | %-11s | %-25s | %-10s | %-25s | %-10s | %-10s | %-12s | %-10s | %-13s | %-25s |\n",
 					"Leave ID", "Employee ID", "Employee Name", "Type", "Reason", "Start Date", "End Date", "Request Date", "Status",
					"Approver", "Comments");
 			System.out.println(
 					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
 			for (int i = 0; i < leaves.length; i++) {
 				if (leaves[i] != null) {
 					if (!leaves[i].getLeaveApplicationID().equals("null")) {
 						if (leaves[i].getLeaveApplicationID().equals("Leave ID")) {
 							//Skip csv header
 							continue;
 						}
 						int index = Integer.parseInt(leaves[i].getEmployeeID()) - 10001;

 						if (leaves[i].getEmployeeID().equals(String.valueOf(PayrollHomepage.currentUser))) {
							System.out.printf(
									"| %-8.8s | %-11.11s | %-25.25s | %-10.10s | %-25.25s | %-10.10s | %-10.10s | %-12.12s | %-10.10s | %-13.13s | %-25.25s |\n",
									leaves[i].getLeaveApplicationID(), leaves[i].getEmployeeID(),
									employee[index].getFullName(), leaves[i].getLeaveType(),
									leaves[i].getReason(), leaves[i].getStartDate(), leaves[i].getEndDate(),
									leaves[i].getRequestDate(), leaves[i].getApprovalStatus(),
									leaves[i].getHRemployeeID(), leaves[i].getComments());
						}
 					}
 				}
 			}
 			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
 		} else {
 			System.out.println("\n!! No Pending Requests !!");
 		}
 				System.out.println("\nPRESS ENTER TO EXIT >>");
 				scan.nextLine();
	   
	   AccessLeave();
   }

   public void ApplyLeave(){
	   leavesClass.readLeaveCsv();
	   try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE, true))) {
	        System.out.println("Apply Overtime");
	        
	        System.out.print("(1) Sick Leave (2) Emergency Leave (3) Vacation Leave\n" + 
	                "Select Leave Type: ");
	        String type = scan.next();
	        scan.nextLine(); // Consume the newline character
	        System.out.print("Write reason for leave: ");
	        String reason = scan.nextLine();
	        System.out.print("Write Start Date (YYYY-MM-DD): ");
	        String leaveStartDate = scan.next();
	        System.out.print("Write End Date (YYYY-MM-DD): ");
	        String leaveEndDate = scan.next();
	        System.out.print("Details of Leave: ");
	        scan.nextLine(); // Consume the newline character
	        String comments = scan.nextLine();

	        // Prepare the data to write to the CSV file      
            type = setLeaveType(type);
            
	        String[] data = {
	                String.valueOf(assignLeaveID()),
	                PayrollHomepage.currentUser + "",
            		type,
            		reason,
	                DateFormatter(leaveStartDate) + "",
	                DateFormatter(leaveEndDate) + "",
	                LocalDate.now() + "",
	                "Pending",
	                "",
	                comments
	        };

	        // Write the data to the CSV file
	        writer.writeNext(data);
	        writer.close();
	        System.out.println("Leave request saved to " + CSV_FILE);
	        AccessLeave();
	    } catch (IOException e) {
	        System.err.println("Error writing to CSV file: " + e.getMessage());
	        AccessLeave();
	    }
       AccessLeave();
   }
   
   public String assignLeaveID() {
	    String lastID = "00000000";

	    for (LeaveRequestDetails details : leavesClass.leaves) {
	        // Ignore empty records
	        if (details == null) {
	            continue;
	        }
	        // Find last application ID
	        String currentID = details.getLeaveApplicationID();
	        if (currentID.compareTo(lastID) > 0) {
	            lastID = currentID;
	        }
	    }

	    // Increment the last ID
	    int incrementedID = Integer.parseInt(lastID) + 1;
	    String formattedID = String.format("%08d", incrementedID); // Add leading zeros
	    return formattedID;
	}

   
   public String setLeaveType(String type) {
	    switch (type) {
	        case "1":
	            return "Sick";
	        case "2":
	            return "Emergency";
	        case "3":
	            return "Vacation";
	        default:
	            System.out.println("You have entered an invalid input for leave type (Input: '" + type + "').");
	            return null; // Or handle the invalid input differently
	    }
	}
   
   public LocalDate DateFormatter(String leaveDate) {
	   LocalDate parsedDate = null;
   	try {
           if (leaveDate != null) {
        	   parsedDate = LocalDate.parse(leaveDate, DateTimeFormatter.ofPattern(dateFormat));
           }
           
       } catch (DateTimeParseException e) {
           // Handle the parsing exception
           e.printStackTrace(); // Print the exception trace for debugging
       }
   	return parsedDate;
   }
   
   public int countLeaveCredits(String leaveType, int maxCredits) {
	   int leavesUsed = 0;
	   
	   for (LeaveRequestDetails requests : leaves) {
			if (requests != null && requests.getEmployeeID().equals(String.valueOf(PayrollHomepage.currentUser))) {
				if (requests.getLeaveType().equals(leaveType)) {
					leavesUsed++;
				}
			}
		}
	   
	   int balance = maxCredits - leavesUsed;
	   return balance;
   }
   
}
