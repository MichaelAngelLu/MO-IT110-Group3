package com.oop.motorph.payrollsystem;

public class OvertimeManagement extends EmployeeOvertime {
	
	public void displayOvertimeRequests() {
		//Initialize overtime details
		readOvertimeCsv();
		readEmployeeCsv();
		//Print details to console
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.printf("| %-8s | %-11s | %-15s | %-10s | %-10s | %-10s | %-12s | %-25s | %-10s | %-13s | %-10s |\n", "OT ID", 
						"Employee ID", "Employee Name", "OT Date", "Start Time", "End Time", "Duration", "Reason", "Status", "Date Approved", "Approver");
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				
				for (int i = 0; i < otDetails.length; i++) {
					if (otDetails[i] != null) {
						if (!otDetails[i].getOvertimeID().equals("null")) {
							if (otDetails[i].getOvertimeID().equals("Overtime ID")) {
								//Skip csv header
								continue;
							}
							int index = Integer.parseInt(otDetails[i].getEmployeeID()) - 10001;
							
							System.out.printf("| %-8.8s | %-11.11s | %-15.15s | %-10.10s | %-10.10s | %-10.10s | %-12.12s | %-25.25s | %-10.10s | %-13.13s | %-10.10s |\n",
									otDetails[i].getOvertimeID(), otDetails[i].getEmployeeID(), employee[index].getFullName(), otDetails[i].getOtDate(),
									otDetails[i].getOtStart(), otDetails[i].getOtEnd(), otDetails[i].getDuration(),
									otDetails[i].getReason(), otDetails[i].getApprovalStatus(), otDetails[i].getApprovedDate(),
									otDetails[i].getApprover());
						} 
					}
				}
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("\nPRESS ENTER TO EXIT >>");
				scan.nextLine();
				displayRequestMenu();
	}
	

}
