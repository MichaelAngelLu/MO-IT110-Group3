package com.oop.motorph.payrollsystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;

public class LeaveManagement extends Employee {
	
	protected LeaveRequestDetails[] leaves = new LeaveRequestDetails[100];
	private String path = "csv/LeaveRequests.csv";
	
	public void readLeaveCsv() {
	    // Read from csv file using OpenCSV
	    try (CSVReader reader = new CSVReader(new FileReader(path))) {
	        String[] values;
	        int i = 0;
	        while ((values = reader.readNext()) != null) {
	            // Check if any value is empty or null
	            for (String value : values) {
	                if (value == null || value.isEmpty()) {
	                    value = "null";
	                    break;
	                }
	            }

	            leaves[i] = new LeaveRequestDetails(values[0], values[1], values[2], values[3], values[4], values[5],
	                    values[6], values[7], values[8], values[9]);
	            
	            i++;
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void displayLeaveRequests() {
		//Initialize leave details
		readLeaveCsv();
		if (leaves[0] != null) {
			//Print details to console
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("| %-8s | %-8s | %-10s | %-25s | %-10s | %-10s | %-12s | %-7s | %-8s | %-25s |\n",
					"Leave ID", "Employee", "Type", "Reason", "Start Date", "End Date", "Request Date", "Status",
					"Approver", "Comments");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------------------------");
			for (int i = 0; i < leaves.length; i++) {
				if (leaves[i] != null) {
					if (!leaves[i].getLeaveApplicationID().equals("null")) {
						System.out.printf(
								"| %-8.8s | %-8.8s | %-10.10s | %-25.25s | %-10.10s | %-10.10s | %-12.12s | %-7.7s | %-8.8s | %-25.25s |\n",
								leaves[i].getLeaveApplicationID(), leaves[i].getEmployeeID(), leaves[i].getLeaveType(),
								leaves[i].getReason(), leaves[i].getStartDate(), leaves[i].getEndDate(),
								leaves[i].getRequestDate(), leaves[i].getApprovalStatus(), leaves[i].getHRemployeeID(),
								leaves[i].getComments());
					}
				}
			}
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------------------------");
		} else {
			System.out.println("\n!! No Data !!");
		}
		System.out.println("\nPRESS ENTER TO EXIT >>");
		scan.nextLine();
		displayRequestMenu();
	}
	
}
