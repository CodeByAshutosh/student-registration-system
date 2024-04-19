package com.srs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "COURSE")
public class CoursesEntity {
	
	
	@Column(name = "DEPT_CODE")
	private String deptCode;
	
	@Column(name = "COURSE#")
	private Long course;
	
	@Column(name = "TITLE")
	private String Title;
	
	
	

}
