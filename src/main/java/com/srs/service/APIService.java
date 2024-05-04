package com.srs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.srs.request.UniqueRequest;
import com.srs.response.EnrollmentsResponse;
import com.srs.response.LogResponse;
import com.srs.response.ResponseObject;

@Service
public class APIService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private OracleService oracleService;

	public ResponseObject saveScore(String score, String lGrade) {
		ResponseObject response= new ResponseObject();
		try {
			String addScoreQuery = "Insert INTO SCORE_GRADE(SCORE , LGRADE) VALUES(?,?)";
			jdbcTemplate.update(addScoreQuery , score,lGrade);

			response.setStatus(true);
			response.setSuccessMessage("Score and grade data saved");
			
		}catch(Exception e) {
			response.setErrorMessage(e.getMessage());
			response.setStatus(false);
		}
		
		
		return response;
	}

	public ResponseObject showLogs() {
		ResponseObject response = new ResponseObject();
		List<LogResponse> list = new ArrayList<>();
		try {
			
			String showLogs = "show_logs()";

			String[] lines = oracleService.callProcedure(showLogs);

			for (String line : lines) {
				if (line != null) {

					String[] parts = line.split(","); // Split by comma
					if (parts.length == 6) {

						int log = parts[0].trim().indexOf(':');
						int username = parts[1].trim().indexOf(':');
						int opTime = parts[2].trim().indexOf(':');
						int tablename = parts[3].trim().indexOf(':');
						int operation = parts[4].trim().indexOf(':');
						int tupleKeyValue = parts[5].trim().indexOf(':');
						
						

						if (log != -1 && username != -1 && opTime != -1 && tablename != -1 
								&& operation != -1 && tupleKeyValue != -1) {

							String logShowLog = parts[0].trim().substring(log + 1).trim();
							String usernameShowLog = parts[1].trim().substring(username + 1).trim();
							String opTimeShowLog = parts[2].trim().substring(opTime + 1).trim();
							String tablenameShowLog = parts[3].trim().substring(tablename + 1).trim();
							String operationShowLog = parts[4].trim().substring(operation + 1).trim();
							String tupleKeyValueShowLog = parts[5].trim().substring(tupleKeyValue + 1).trim();

							LogResponse logResponse = new LogResponse(logShowLog, usernameShowLog,
									opTimeShowLog, tablenameShowLog, operationShowLog, tupleKeyValueShowLog);
							list.add(logResponse);

						} else {
							System.out.println("Colon not found in the string.");
						}

					}
				}

			}

			response.setResponse(list);
			response.setStatus(true);
			response.setSuccessMessage("log list");
			
		}catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}

	public ResponseObject showEnrollments() {
		ResponseObject response = new ResponseObject();
		List<EnrollmentsResponse> list = new ArrayList<>();
		try {
			
			String showEnrollments = "show_g_enrollments()";

			String[] lines = oracleService.callProcedure(showEnrollments);

			for (String line : lines) {
				if (line != null) {

					String[] parts = line.split(","); // Split by comma
					if (parts.length == 3) {

						int bNumber = parts[0].trim().indexOf(':');
						int classId = parts[1].trim().indexOf(':');
						int score = parts[2].trim().indexOf(':');
						
						
						

						if (bNumber != -1 && classId != -1 && score != -1) {

							String bNumberShowEnrollments = parts[0].trim().substring(bNumber + 1).trim();
							String classIdShowEnrollments = parts[1].trim().substring(classId + 1).trim();
							String scoreShowEnrollments = parts[2].trim().substring(score + 1).trim();
							
							EnrollmentsResponse logResponse = new EnrollmentsResponse(bNumberShowEnrollments, classIdShowEnrollments,
									scoreShowEnrollments);
							list.add(logResponse);

						} else {
							System.out.println("Colon not found in the string.");
						}

					}
				}

			}

			response.setResponse(list);
			response.setStatus(true);
			response.setSuccessMessage("enrollments list");
			
		}catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}

	public ResponseObject enrollStudent(String bNumber, String classId) {
		ResponseObject response = new ResponseObject();
		
		try {
			
			
			
		}catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		
		return response;
	}

	public ResponseObject saveEnrollments(String bNumber, String classId, String score) {
		ResponseObject response = new ResponseObject();
		try {
			
			String addScoreQuery = "Insert INTO G_ENROLLMENTS(SCORE , LGRADE) VALUES(?,?,?)";
			jdbcTemplate.update(addScoreQuery , bNumber,classId,score);

			response.setStatus(true);
			response.setSuccessMessage("Score and grade data saved");
			
			
			
		}catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		
		return response;
	}

	public ResponseObject deleteEnrollments(String bNumber, String classId) {
		ResponseObject response = new ResponseObject();
		try {
			
			
			
			
			
			
		}catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}

	public ResponseObject uniqueData(UniqueRequest uniqueRequest) {
		ResponseObject response = new ResponseObject();
		try {
			
		}catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		
		return response;
	}

}
