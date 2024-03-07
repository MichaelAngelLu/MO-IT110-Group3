package com.oop.motorph.payrollsystem;

/**
*
* @author Tone
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;



public class EmployeeLeave {

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
                        System.out.println("Merge Check");
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
       System.out.println("Apply Leave");
       AccessLeave();
   }
   
}
