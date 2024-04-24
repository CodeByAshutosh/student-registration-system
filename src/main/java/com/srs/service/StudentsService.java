package com.srs.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.srs.request.StudentsRequest;
import com.srs.response.ResponseObject;

@Service
public class StudentsService {

//	@Autowired
//	private StudentRepository studentRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String CHARACTERS = "0123456789";

	public ResponseObject addStudents(StudentsRequest studentsRequest) throws ParseException {
		ResponseObject response = new ResponseObject();

		// StudentsEntity students =
		// studentRepository.findByEmail(studentsRequest.getEmail());

		Random random = new Random();
		String randomNumber = String.format("%07d", Integer.valueOf(random.nextInt(10001)));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = dateFormat.parse(studentsRequest.getBdate());

		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		String procedureCall = "{call SAVE_STUDENT(?, ?,?,?,?,?,?)}";
		jdbcTemplate.update(procedureCall, "B_"+randomNumber.trim(), studentsRequest.getFirstName(),
				studentsRequest.getLastName(), studentsRequest.getStLevel(), studentsRequest.getGpa(),
				studentsRequest.getEmail(), sqlDate);

		return response;
	}

	public ResponseObject deleteStudent(String bNumber) {
		ResponseObject response = new ResponseObject();
		
		String procedureCall = "{call Delete_Student(?)}";
		jdbcTemplate.update(procedureCall , bNumber);
		
		return response;
	}

}
