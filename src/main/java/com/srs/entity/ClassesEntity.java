package com.srs.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLASSES")
public class ClassesEntity {
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLASSID")
	private String classId;
	
	@Column(name = "DEPT_CODE")
	private String deptCode;
	
	@Column(name = "COURSE#")
	private String course;
	
	@Column(name = "SECT#")
	private String sect;
	
	@Column(name = "YEAR")
	private Long year;
	
	@Column(name = "SEMESTER")
	private String semester;
	
	@Column(name = "LIMIT")
	private Long limit;
	
	@Column(name = "CLASS_SIZE")
	private Long classSize;
	
	@Column(name = "ROOM")
	private String room;
	
	
	
	
	
	
	
	
	
	
	
	

}
