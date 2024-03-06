package com.oop.motorph.payrollsystem;

//import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Employee {
	
	Scanner scan = new Scanner(System.in);
	protected EmployeeDetails[] employee = new EmployeeDetails[35];
	private String path = "csv/EmployeeDetails.csv", line = "";
	
	public void readCsvFile() {
		//Read from csv file
		try {
	        BufferedReader br = new BufferedReader(new FileReader(path));
	        int i = 0;

	        while ((line = br.readLine()) != null) {

	            String[] values = line.split(",");
	            
	         // check if any value is empty or null
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
	                    values[14], values[15], values[16], values[17], values[18], values[19]);
	            
	            i++;
	        }
	        br.close();
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
