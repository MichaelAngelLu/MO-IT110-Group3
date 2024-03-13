package com.oop.motorph.payrollsystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

import com.opencsv.CSVReader;

public class Payslip extends Employee {
	
	// Create a DecimalFormat object to format numbers with commas
	DecimalFormat formatter = new DecimalFormat("#,###.00");
	protected PayslipDetails[] payslip = new PayslipDetails[500];
	private HoursWorked hours = new HoursWorked();
	private String path = "csv/Payslip.csv";
	
	public void readPayslipCsv() {
	    try (CSVReader reader = new CSVReader(new FileReader(this.path))) {
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
	            payslip[i] = new PayslipDetails(values[0], values[1], values[2], values[3], values[4], values[5],
	                    values[6], values[7], values[8], values[9], values[10], values[11]);

	            i++;
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void cutOffViewer() {
		//Read and initialize payslip details from csv
		readPayslipCsv();
		readEmployeeCsv();
		System.out.println("Select payslip cut-off date: ");
		System.out.println("1. March 09, 2024");//temp code
		System.out.println("2. Back");//To add option to view previous records
		System.out.print("\nEnter selection: ");
		allowInput();
	}
	
	public void allowInput() {
		String i = scan.next();
//		String i = "1";
		redirectFromCutOff(i);
	}
	
	public void redirectFromCutOff(String i) {
		switch(i) {
		case "1":
            payslipViewer();
			break;
		case "2":
			displayRequestMenu();
			break;
		}
	}
	
	public void payslipViewer() {
		System.out.println("\n----------------------------------------------------------------------------------------");
		System.out.println("|                                   EMPLOYEE PAYSLIP                                   |");
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.printf("| %-18.18s | %-19.19s | %-18.18s | %-20.20s |\n", "Payslip ID", payslip[0].getPayslipID(), 
				"Cut-Off Date", payslip[0].getCutoffDate());
		System.out.printf("| %-18.18s | %-19.19s | %-18.18s | %-20.20s |\n", "Employee ID", employee[PayrollHomepage.currentUser - 10001].getEmployeeID(), 
				"Department", employee[PayrollHomepage.currentUser - 10001].getDepartmentID());
		System.out.printf("| %-18.18s | %-19.19s | %-18.18s | %-20.20s |\n", "Employee Name", employee[PayrollHomepage.currentUser - 10001].getFullName(), 
				"Position", employee[PayrollHomepage.currentUser - 10001].getPositionID());
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("|  EARNINGS                                                                            |");
		System.out.println("----------------------------------------------------------------------------------------");
		
		double basicSalary = Float.parseFloat(employee[PayrollHomepage.currentUser - 10001].getBasicSalary());
		double daysWorked = (double) hours.calculateHoursWorked("10001", "03/01/2024", "03/15/2024") / 8;
		double hourlyRate = Float.parseFloat(employee[PayrollHomepage.currentUser - 10001].getHourlyRate());
		
		System.out.printf("| %-20s | PHP %57.2f |\n", "Basic Salary", (double) basicSalary);
		System.out.printf("| %-20s | PHP %57.2f |\n", "Daily Rate", hourlyRate * 8);
		System.out.printf("| %-20s | %61.2f |\n", "Days Worked", (double) hours.calculateHoursWorked("10001", "03/01/2024", "03/15/2024") / 8);
		System.out.printf("| %-20s | %61.61s |\n", "Overtime", "0");
		System.out.printf("| %-20s | PHP %57.2f |\n", "GROSS INCOME", daysWorked * (hourlyRate * 8));
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("|  BENEFITS                                                                            |");
		System.out.println("----------------------------------------------------------------------------------------");
		
		float rice = Float.parseFloat(employee[PayrollHomepage.currentUser - 10001].getRiceSubsidy());
		float phone = Float.parseFloat(employee[PayrollHomepage.currentUser - 10001].getPhoneAllowance());
		float clothing = Float.parseFloat(employee[PayrollHomepage.currentUser - 10001].getClothingAllowance());
		
		System.out.printf("| %-20s | PHP %57.2f |\n", "Rice Subsidy", rice);
		System.out.printf("| %-20s | PHP %57.2f |\n", "Phone Allowance", phone);
		System.out.printf("| %-20s | PHP %57.2f |\n", "Clothing Allowance", clothing);
		System.out.printf("| %-20s | PHP %57.2f |\n", "TOTAL BENEFITS", rice + phone + clothing);
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("|  DEDUCTIONS                                                                          |");
		System.out.println("----------------------------------------------------------------------------------------");
		
		double sss = (double) CalculateSSS.SSS(basicSalary);
		double phic = (double) CalculatePhilHealth.PhilHealth(basicSalary);
		double hdmf = (double) CalculatePagIbig.PagIbig(basicSalary);
		double tin = (double) CalculateWithholdingTax.WithholdingTax(basicSalary, sss, phic, hdmf);
		double total = sss + phic + hdmf + tin;
		
		System.out.printf("| %-20s | PHP %57.2f |\n", "SSS", sss);
		System.out.printf("| %-20s | PHP %57.2f |\n", "PhilHealth", phic);
		System.out.printf("| %-20s | PHP %57.2f |\n", "Pag-Ibig", hdmf);
		System.out.printf("| %-20s | PHP %57.2f |\n", "Withholding Tax", tin);
		System.out.printf("| %-20s | PHP %57.2f |\n", "TOTAL DEDUCTIONS", total);
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.printf("| %-20s | PHP %57.2f |\n", "TAKE HOME PAY", (daysWorked * (hourlyRate * 8)) - total);
		System.out.println("----------------------------------------------------------------------------------------");
		scan.nextLine();
		System.out.println("\nPRESS ENTER TO EXIT >>");
		scan.nextLine();
		PayrollHomepage home = new PayrollHomepage();
		home.displayHomepage();
	}
	
}
