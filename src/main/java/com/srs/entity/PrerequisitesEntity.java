package com.srs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PREREQUISITES")
public class PrerequisitesEntity {

	
	@Column(name = "DEPT_CODE")
	private String deptCode;
	
	@Id
	@Column(name = "COURSE#")
	private String course;
	
	@Column(name = "PRE_DEPT_CODE")
	private String preDeptCode;
	
	@Column(name = "PRE_COURSE#")
	private String preCourse;
	
	
	
	
	
}
