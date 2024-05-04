package com.srs.request;

public class UniqueRequest {
	private String module;
	private String bNumber;
	private String emailId;
	private String classId;
	private String deptCode;
	private String course;
	private String section;
	private String year;
	private String semester;
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getbNumber() {
		return bNumber;
	}
	public void setbNumber(String bNumber) {
		this.bNumber = bNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public UniqueRequest(String module, String bNumber, String emailId, String classId, String deptCode, String course,
			String section, String year, String semester) {
		this.module = module;
		this.bNumber = bNumber;
		this.emailId = emailId;
		this.classId = classId;
		this.deptCode = deptCode;
		this.course = course;
		this.section = section;
		this.year = year;
		this.semester = semester;
	}
	
	

}
