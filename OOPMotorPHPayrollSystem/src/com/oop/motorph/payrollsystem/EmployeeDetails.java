package com.oop.motorph.payrollsystem;

public class EmployeeDetails {
	
	//employee personal information
	private String firstName;
	private String lastName;
	private String birthday;
	private String address;
	private String phoneNumber;
	
	private String employeeStatus;
	private String departmentID;
	private String positionID;
	private String immediateSupervisorID;
	
	//employee tax details
	private String sssNo;
	private String phicNo;
	private String tinNo;
	private String hdmfNo;
	
	//employee salary details
	private String basicSalary;
	private String riceSubsidy;
	private String phoneAllowance;
	private String clothingAllowance;
	private String hourlyRate;
	
	//employee account details
	private String accessRole;
	private String employeeID;
	protected String password;
	
	//Initialize attributes
	public EmployeeDetails(String employeeID, String firstName, String lastName, String birthday, String address, 
			String phoneNumber, String employeeStatus, String departmentID, String positionID,
			String immediateSupervisorID, String sssNo, String phicNo, String tinNo, String hdmfNo,
			String basicSalary, String riceSubsidy, String phoneAllowance, String clothingAllowance, 
			String hourlyRate, String accessRole, String password) {
	
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.employeeStatus = employeeStatus;
		this.departmentID = departmentID;
		this.positionID = positionID;
		this.immediateSupervisorID = immediateSupervisorID;
		this.sssNo = sssNo;
		this.phicNo = phicNo;
		this.tinNo = tinNo;
		this.hdmfNo = hdmfNo;
		this.basicSalary = basicSalary;
		this.riceSubsidy = riceSubsidy;
		this.phoneAllowance = phoneAllowance;
		this.clothingAllowance = clothingAllowance;
		this.hourlyRate = hourlyRate;
		this.accessRole = accessRole;
		this.password = password;
		
	}

	//Getter methods
	public String getEmployeeID() {
		return employeeID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getEmployeeStatus() {
		return employeeStatus;
	}
	
	public String getDepartmentID() {
		return departmentID;
	}
	
	public String getPositionID() {
		return positionID;
	}
	
	public String getimmediateSupervisorID() {
		return immediateSupervisorID;
	}
	
	public String getSSSNo() {
		return sssNo;
	}
	
	public String getPhilHealthNo() {
		return phicNo;
	}
	
	public String getTinNo() {
		return tinNo;
	}
	
	public String getPagibigNo() {
		return hdmfNo;
	}
	
	public String getBasicSalary() {
		return basicSalary;
	}
	
	public String getRiceSubsidy() {
		return riceSubsidy;
	}
	
	public String getPhoneAllowance() {
		return phoneAllowance;
	}
	
	public String getClothingAllowance() {
		return clothingAllowance;
	}
	
	public String getHourlyRate() {
		return hourlyRate;
	}
	
	public String getAccessRole() {
		return accessRole;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	//Setter methods
	
	public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
	
	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
	
	public void setLastName(String lastName) {
        this.lastName = lastName;
    }
	
	public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
	
	public void setAddress(String address) {
        this.address = address;
    }
	
	public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
	
	public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
	
	public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }
	
	public void setPositionID(String positionID) {
        this.positionID = positionID;
    }
	
	public void setImmediateSupervisorID(String immediateSupervisorID) {
        this.immediateSupervisorID = immediateSupervisorID;
    }
	
	public void setSSSNo(String sssNo) {
        this.sssNo = sssNo;
    }
	
	public void setPhilHealthNo(String phicNo) {
        this.phicNo = phicNo;
    }
	
	public void setTINNo(String tinNo) {
        this.tinNo = tinNo;
    }
	
	public void setPagibigNo(String hdmfNo) {
        this.hdmfNo = hdmfNo;
    }
	
	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}
	
	public void setRiceSubsidy(String riceSubsidy) {
        this.riceSubsidy = riceSubsidy;
    }
	
	public void setPhoneAllowance(String phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
    }
	
	public void setClothingAllowance(String clothingAllowance) {
        this.clothingAllowance = clothingAllowance;
    }
	
	public void setHourlyRate(String hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
	
	public void setAccessRole(String accessRole) {
        this.accessRole = accessRole;
    }
	
	public void setPassword(String password) {
		this.password = password;
	}
}
