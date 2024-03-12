package com.oop.motorph.payrollsystem;

import java.time.LocalDate;

public class HoursWorkedDetails {
	
	String employeeID;
	LocalDate attendanceDate;
	long hoursWorked;
	
	public HoursWorkedDetails(String employeeID, LocalDate attendanceDate, long hoursWorked) {
		
		this.employeeID = employeeID;
		this.attendanceDate = attendanceDate;
		this.hoursWorked = hoursWorked;
	}
	
	
}
