package com.oop.motorph.payrollsystem;

public class CalculatePhilHealth {
	public static double PhilHealth(double basicSalary) {
        if (basicSalary <= 10000) {
            return 150.00;
        } else if (basicSalary > 10000 && basicSalary < 60000) {
            return ((basicSalary * 0.03) / 2);
        } else {
            return 900.00;
        }
    }
}
