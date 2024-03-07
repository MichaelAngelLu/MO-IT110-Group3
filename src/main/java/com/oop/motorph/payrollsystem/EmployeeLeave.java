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
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
      



public class EmployeeLeave extends Employee {
   private static final String CSV_FILE = "..\\payrollsystem\\csv\\1leave_application.csv";
   Scanner scan = new Scanner(System.in);
   
   public void AccessLeave(){
		//Homepage menu
		System.out.println("\nLeave Menu:");
		System.out.println("<1> Check Leave Credits and Pending Leaves");
		System.out.println("<2> Apply For Leave");
		System.out.println("<3> Back");		
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
			CheckLeaveCredits();
			break;
		case "2":
			System.out.println("You chose: " + i);
                       ApplyLeave();
			break;
		case "3":
			System.out.println("You chose: " + i);
                       PayrollHomepage homepage = new PayrollHomepage();
                       homepage.displayRequestsMenu();
			break;
               
               }
   }            

   public void CheckLeaveCredits(){
       System.out.println("Check Leave Credits");
       AccessLeave();
   }

   public void ApplyLeave(){
       
       try (FileWriter writer = new FileWriter(CSV_FILE)) {
            System.out.println("Apply Leave");

            System.out.print("Write reason for leave: ");
            String reason = scan.next();
            System.out.print("Write Start Date (YYYY-MM-DD): ");
            String leaveStartDate = scan.next();
            System.out.print("Write End Date (YYYY-MM-DD): ");
            String leaveEndDate = scan.next();
            System.out.print("Details of Leave: ");
            String comments = scan.next();

            // Write the input data into the CSV file
            writer.write(reason + "," + leaveStartDate + "," + leaveEndDate + "," + comments);

            System.out.println("Leave application saved to " + CSV_FILE);
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
       AccessLeave();
   }
   
   public void DateFormatter(String leaveDate) {
       
   }
   
}
