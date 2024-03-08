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
      



public class EmployeeOvertime extends Employee {
   private static final String CSV_FILE = "..\\payrollsystem\\csv\\overtime_application.csv";
   Scanner scan = new Scanner(System.in);
   
   public void AccessOvertime(){
		//Homepage menu
		System.out.println("\nOvertime Menu:");
		System.out.println("<1> Check Pending Overtime");
		System.out.println("<2> Apply For Overtime");
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
			CheckPendingOvertime();
			break;
		case "2":
			System.out.println("You chose: " + i);
                        ApplyOvertime();
			break;
		case "3":
			System.out.println("You chose: " + i);
                        PayrollHomepage homepage = new PayrollHomepage();
                        homepage.displayRequestsMenu();
			break;
               
               }
   }            

   public void CheckPendingOvertime(){
       System.out.println("Check Pending Overtime");
       AccessOvertime();
   }

   public void ApplyOvertime(){
       
       try (FileWriter writer = new FileWriter(CSV_FILE, true)) {
            System.out.println("Apply Overtime");

            System.out.print("Write reason for leave: ");
            String reason = scan.next();
            System.out.print("Write Overtime Date (YYYY-MM-DD): ");
            String overtimeDate = scan.next();
            System.out.print("Write Start Time (HH:MM): ");
            String overtimeStartTime = scan.next();
            System.out.print("Write End Time (HH:MM): ");
            String overtimeEndTime = scan.next();

            // Write the input data into the CSV file
            writer.write("\n" + reason + "," + overtimeDate + "," + overtimeStartTime + "," + overtimeEndTime);

            System.out.println("Leave application saved to " + CSV_FILE);
            AccessOvertime();
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
            AccessOvertime();
        }
   }
   
   public void DateFormatter(String leaveDate) {
       
   }
   
}
