package com.srs.response;

public class EnrollmentsResponse {
	
	private String bNumber;
	private String classId;
	private String score;
	public String getbNumber() {
		return bNumber;
	}
	public void setbNumber(String bNumber) {
		this.bNumber = bNumber;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public EnrollmentsResponse(String bNumber, String classId, String score) {
		this.bNumber = bNumber;
		this.classId = classId;
		this.score = score;
	}
	
	

}
