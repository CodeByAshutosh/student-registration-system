package com.srs.request;

public class ClassesRequest {
	
	private String classId;
	private String deptCode;
	private String course;
	private String sect;
	private String year;
	private String semester;
	private String limit;
	private String classSize;
	private String room;
	
	
	public ClassesRequest() {
		// TODO Auto-generated constructor stub
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
	public String getSect() {
		return sect;
	}
	public void setSect(String sect) {
		this.sect = sect;
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
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getClassSize() {
		return classSize;
	}
	public void setClassSize(String classSize) {
		this.classSize = classSize;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	@Override
	public String toString() {
		return "ClassesRequest [classId=" + classId + ", deptCode=" + deptCode + ", course=" + course + ", sect=" + sect
				+ ", year=" + year + ", semester=" + semester + ", limit=" + limit + ", classSize=" + classSize
				+ ", room=" + room + "]";
	}
	public ClassesRequest(String classId, String deptCode, String course, String sect, String year, String semester,
			String limit, String classSize, String room) {
		this.classId = classId;
		this.deptCode = deptCode;
		this.course = course;
		this.sect = sect;
		this.year = year;
		this.semester = semester;
		this.limit = limit;
		this.classSize = classSize;
		this.room = room;
	}
	
	

}
