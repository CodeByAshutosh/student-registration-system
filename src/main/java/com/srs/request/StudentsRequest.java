package com.srs.request;

public class StudentsRequest {
	
	private String bNumber;
	private String firstName;
	private String lastName;
	private String stLevel;
	private String gpa;
	private String email;
	private String bdate;
	
	public String getbNumber() {
		return bNumber;
	}
	public void setbNumber(String bNumber) {
		this.bNumber = bNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStLevel() {
		return stLevel;
	}
	public void setStLevel(String stLevel) {
		this.stLevel = stLevel;
	}
	public String getGpa() {
		return gpa;
	}
	public void setGpa(String gpa) {
		this.gpa = gpa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public StudentsRequest(String bNumber, String firstName, String lastName, String stLevel, String gpa, String email,
			String bdate) {
		this.bNumber = bNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.stLevel = stLevel;
		this.gpa = gpa;
		this.email = email;
		this.bdate = bdate;
	}
	
	
	
	
	
	

}
