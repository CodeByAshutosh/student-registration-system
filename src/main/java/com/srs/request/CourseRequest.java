package com.srs.request;

public class CourseRequest {
	
	
	private String deptCode;
	private String course;
	private String title;
	
	
	public CourseRequest() {
		// TODO Auto-generated constructor stub
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "CourseRequest [deptCode=" + deptCode + ", course=" + course + ", title=" + title + "]";
	}
	public CourseRequest(String deptCode, String course, String title) {
		this.deptCode = deptCode;
		this.course = course;
		this.title = title;
	}
	
	

}
