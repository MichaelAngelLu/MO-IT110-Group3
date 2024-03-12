package com.oop.motorph.payrollsystem;

public class AttendanceDetails {
	
	 String attendanceID;
	 String employeeID;
	 String attendanceDate;
	 String timeIn;
	 String breakOut;
	 String breakIn;
	 String timeOut;
	
	public AttendanceDetails(String attendanceID, String employeeID, String attendanceDate, String timeIn,
			String breakOut, String breakIn, String timeOut) {

		this.attendanceID = attendanceID;
		this.employeeID = employeeID;
		this.attendanceDate = attendanceDate;
		this.timeIn = timeIn;
		this.breakOut = breakOut;
		this.breakIn = breakIn;
		this.timeOut = timeOut;

	}
	
}
