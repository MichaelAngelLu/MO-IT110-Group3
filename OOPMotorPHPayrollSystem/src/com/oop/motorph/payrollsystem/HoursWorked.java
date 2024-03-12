package com.oop.motorph.payrollsystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.opencsv.CSVReader;

public class HoursWorked {
	
        String path = "csv/attendance.csv";
        AttendanceDetails[] attendance = new AttendanceDetails[1000];
        HoursWorkedDetails[] hw = new HoursWorkedDetails[1000];
        String dateFormat = "MM/dd/yyyy";
        
        public void readAttendanceCsv() {
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
    	            
    	            attendance[i] = new AttendanceDetails(values[0], values[1], values[2], values[3], values[4], values[5],
    	                    values[6]);

    	            i++;
    	        }
    	    } catch (FileNotFoundException e) {
    	        e.printStackTrace();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	}
        
        public long calculateHoursWorked(String employeeID, String cutoffStart, String cutoffEnd) {
        	readAttendanceCsv();
        	LocalDate start, end; 
        	start = LocalDate.parse(cutoffStart, DateTimeFormatter.ofPattern(dateFormat));
        	end = LocalDate.parse(cutoffEnd, DateTimeFormatter.ofPattern(dateFormat));
        	long sum = 0;
        	for (AttendanceDetails attendanceDetail : attendance) {
        		
        		if (attendanceDetail == null) {
        			// Ignore empty records
        			continue;
        		}
        		
        		if (!attendanceDetail.employeeID.equals(employeeID)) {
        			continue;
        		}
        		
        		try {
                    LocalDate date = LocalDate.parse(attendanceDetail.attendanceDate, DateTimeFormatter.ofPattern(dateFormat));
//                    int i = 0;
                    if (isWithinCutOffPeriod(date, start, end)) {
//                        System.out.println(attendanceDetail.employeeID + " was present for work on " + date + " ID: " + attendanceDetail.attendanceID);
                     
                        // Calculate the total hours worked for the employee
                        long totalHoursWorked = calculateHoursWorked(parseTime(attendanceDetail.timeIn), parseTime(attendanceDetail.breakOut), 
                        		parseTime(attendanceDetail.breakIn), parseTime(attendanceDetail.timeOut)); 
//                        System.out.println("You have worked for " + totalHoursWorked + " hours");
//                        hw[i] = new HoursWorkedDetails(attendanceDetail.employeeID,date,totalHoursWorked);
                        sum+=totalHoursWorked;
                    }
                } catch (DateTimeParseException e) {
                    // Ignore values that are not dates
                    continue;
                }
        	}
        	return sum;
        }
        
     // Method to check if a date falls within a specified pay period
        private boolean isWithinCutOffPeriod(LocalDate date, LocalDate startDate, LocalDate endDate) {
            return !date.isBefore(startDate) && !date.isAfter(endDate);
        }
        
     // Method to calculate the total hours worked between timeIn and timeOut
        private long calculateHoursWorked(LocalTime timeIn, LocalTime breakOut, LocalTime breakIn, LocalTime timeOut) {
            long workHours = timeIn.until(timeOut, java.time.temporal.ChronoUnit.HOURS);
        	long breakTime = breakOut.until(breakIn, java.time.temporal.ChronoUnit.HOURS);
            long total = workHours - breakTime;
        			
        	return total;
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

}
