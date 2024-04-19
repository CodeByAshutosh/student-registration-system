package com.srs.entity;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LOGS")
public class LogsEntity {
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOG#")
	private Long log;
	
	@Column(name = "USER_NAME")
	private Long username;
	
	@Column(name = "OP_TIME")
	private Date opTime;
	
	@Column(name = "TABLE_NAME")
	private Long tableName;
	
	@Column(name = "OPERATION")
	private Long operation;
	
	@Column(name = "TUPLE_KEYVALUE")
	private Long tupleKeyvalue;
	
	
	
	
	
	
	
	

}
