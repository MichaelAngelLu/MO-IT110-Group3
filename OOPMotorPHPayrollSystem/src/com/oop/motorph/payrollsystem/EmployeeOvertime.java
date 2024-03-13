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

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;




public class EmployeeOvertime extends Employee {
   private static final String CSV_FILE = "csv/overtime_application.csv";
   protected OvertimeRequestDetails[] otDetails = new OvertimeRequestDetails[500];
   Scanner scan = new Scanner(System.in);
   String dateFormat = "yyyy-MM-dd";
   
public void AccessOvertime(){
	   //read overtime details csv files
	   readOvertimeCsv();

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
                        homepage.displayHomepage();
			break;

               }
   }            

   public void CheckPendingOvertime(){
       System.out.println("Check Pending Overtime");
       AccessOvertime();
   }

   public void ApplyOvertime() {
	    try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE, true))) {
	        System.out.println("Apply Overtime");

	        System.out.print("Write reason for overtime: ");
	        String reason = scan.next();
	        System.out.print("Write Overtime Date (YYYY-MM-DD): ");
	        scan.next();
	        String overtimeDate = scan.next();
	        System.out.print("Write Start Time (HH:MM): ");
	        String overtimeStartTime = scan.next();
	        System.out.print("Write End Time (HH:MM): ");
	        String overtimeEndTime = scan.next();

	        // Parse time strings to LocalTime objects
	        LocalTime startTime = parseTime(overtimeStartTime);
	        LocalTime endTime = parseTime(overtimeEndTime);

	        // Convert LocalTime objects to strings
	        String startTimeString = startTime.format(DateTimeFormatter.ofPattern("HH:mm"));
	        String endTimeString = endTime.format(DateTimeFormatter.ofPattern("HH:mm"));

	        // Prepare the data to write to the CSV file
	        String[] data = {
	                String.valueOf(assignOvertimeID()),
	                PayrollHomepage.currentUser + "",
	                DateFormatter(overtimeDate) + "",
	                startTimeString,
	                endTimeString,
	                String.valueOf(calculateDuration(startTime, endTime)),
	                reason,
	                "Pending",
	                "",
	                ""
	        };

	        // Write the data to the CSV file
	        writer.writeNext(data);
	        writer.close();
	        System.out.println("Overtime application saved to " + CSV_FILE);
	        AccessOvertime();
	    } catch (IOException e) {
	        System.err.println("Error writing to CSV file: " + e.getMessage());
	        AccessOvertime();
	    }
	}

   public LocalDate DateFormatter(String overtimeDate) {
	   LocalDate parsedDate = null;
   	try {
           if (overtimeDate != null) {
        	   parsedDate = LocalDate.parse(overtimeDate, DateTimeFormatter.ofPattern(dateFormat));
           }
           
       } catch (DateTimeParseException e) {
           // Handle the parsing exception
           e.printStackTrace(); // Print the exception trace for debugging
       }
   	return parsedDate;
   }
   
   private LocalTime parseTime(String timeRecord) {
   	LocalTime parsedTime = null;
   	try {
           if (timeRecord != null) {
               parsedTime = LocalTime.parse(timeRecord);
           }
           
       } catch (DateTimeParseException e) {
           // Handle the parsing exception
           e.printStackTrace(); // Print the exception trace for debugging
       }
   	return parsedTime;
   }

   
   public void readOvertimeCsv() {
	   try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE))) {
	        String[] values;
	        int i = 0;
	        while ((values = reader.readNext()) != null) {
	        	
	            if (true) {
					otDetails[i] = new OvertimeRequestDetails(values[0], values[1], values[2], values[3], values[4],
							values[5], values[6], values[7], values[8], values[9]);
					i++;
				}
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
   }
   
   public String assignOvertimeID() {
	    String lastID = "00000000";

	    for (OvertimeRequestDetails details : otDetails) {
	    	//Ignore empty records
	    	if (details == null) {
	    		continue;
	    	}
	    	//Ignore csv header
	    	if (details.getOvertimeID().equals("Overtime ID")) {
	    		continue;
	    	}
	    	//Find last application ID
	        String currentID = details.getOvertimeID();
	        if (currentID.compareTo(lastID) > 0) {
	            lastID = currentID;
	        }
	    }

	    return lastID;
	}
   
   private String calculateDuration(LocalTime overtimeStartTime, LocalTime overtimeEndTime) {			
	    long hours = overtimeStartTime.until(overtimeEndTime, ChronoUnit.HOURS);
	    long minutes = overtimeStartTime.until(overtimeEndTime, ChronoUnit.MINUTES) % 60;

	    String formattedDuration = hours + " hr " + minutes + " mins";
	    return formattedDuration;
	}




}
