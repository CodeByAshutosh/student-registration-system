package com.srs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "G_ENROLLMENTS")
public class GEnrollmentsEntity {
	
	@Column(name = "G_B#")
	private String gb;
	
	@Column(name = "CLASSID")
	private String classId;
	
	@Column(name = "SCORE")
	private Long deptCode;
	

}
