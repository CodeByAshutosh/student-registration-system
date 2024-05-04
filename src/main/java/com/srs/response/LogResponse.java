package com.srs.response;

public class LogResponse {
	
	private String log;
	private String username;
	private String opTime;
	private String tablename;
	private String operation;
	private String tupleKeyValue;
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getTupleKeyValue() {
		return tupleKeyValue;
	}
	public void setTupleKeyValue(String tupleKeyValue) {
		this.tupleKeyValue = tupleKeyValue;
	}
	public LogResponse(String log, String username, String opTime, String tablename, String operation,
			String tupleKeyValue) {
		this.log = log;
		this.username = username;
		this.opTime = opTime;
		this.tablename = tablename;
		this.operation = operation;
		this.tupleKeyValue = tupleKeyValue;
	}
	
	

}
