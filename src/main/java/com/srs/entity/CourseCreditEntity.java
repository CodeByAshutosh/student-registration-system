package com.srs.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COURSE_CREDIT")
public class CourseCreditEntity {
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COURSE#")
	private Long course;
	
	@Column(name = "CREDITS")
	private Long credits;
	
	
	
	
	

}
