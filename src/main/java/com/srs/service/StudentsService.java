package com.srs.service;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.srs.request.StudentsRequest;
import com.srs.response.ResponseObject;
import com.srs.response.StudentsResponse;

@Service
public class StudentsService {

//	@Autowired
//	private StudentRepository studentRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private OracleService oracleService;

	public ResponseObject addStudents(StudentsRequest studentsRequest) throws ParseException {
		ResponseObject response = new ResponseObject();

		// StudentsEntity students =
		// studentRepository.findByEmail(studentsRequest.getEmail());

		Random random = new Random();
		String randomNumber = String.format("%08d", Integer.valueOf(random.nextInt(10001)));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = dateFormat.parse(studentsRequest.getBdate());

		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		String procedureCall = "{call SAVE_STUDENT(?, ?,?,?,?,?,?)}";
		jdbcTemplate.update(procedureCall, "B" + randomNumber.trim(), studentsRequest.getFirstName(),
				studentsRequest.getLastName(), studentsRequest.getStLevel(), studentsRequest.getGpa(),
				studentsRequest.getEmail(), sqlDate);

		response.setStatus(true);
		response.setSuccessMessage("Data Saved");

		return response;
	}

	public ResponseObject deleteStudent(String bNumber) {
		ResponseObject response = new ResponseObject();

		String procedureCall = "{call Delete_Student(?)}";
		jdbcTemplate.update(procedureCall, bNumber);

		response.setStatus(true);
		response.setSuccessMessage("Student data deleted : " + bNumber);

		return response;
	}

	public ResponseObject showStudentData() throws ClassNotFoundException {
		ResponseObject response = new ResponseObject();
		List<StudentsResponse> list = new ArrayList<StudentsResponse>();
		String showStudent = "show_students()";

		String[] lines = oracleService.callProcedure(showStudent);

		for (String line : lines) {
			if (line != null) {

				String[] parts = line.split(","); // Split by comma
				if (parts.length == 7) {

					int bNumber = parts[0].trim().indexOf(':');
					int firstName = parts[1].trim().indexOf(':');
					int lastName = parts[2].trim().indexOf(':');
					int stLevel = parts[3].trim().indexOf(':');
					int gpa = parts[4].trim().indexOf(':');
					int email = parts[5].trim().indexOf(':');
					int bdate = parts[6].trim().indexOf(':');

					if (bNumber != -1 && firstName != -1 && lastName != -1 && stLevel != -1 && gpa != -1 && email != -1
							&& bdate != -1) {

						String bNumberStudent = parts[0].trim().substring(bNumber + 1).trim();
						String firstNameStudent = parts[1].trim().substring(firstName + 1).trim();
						String lastNameStudent = parts[2].trim().substring(lastName + 1).trim();
						String stLevelStudent = parts[3].trim().substring(stLevel + 1).trim();
						String gpaStudent = parts[4].trim().substring(gpa + 1).trim();
						String emailStudent = parts[5].trim().substring(email + 1).trim();
						String bdateStudent = parts[6].trim().substring(bdate + 1).trim();

						StudentsResponse studentsResponse = new StudentsResponse(bNumberStudent, firstNameStudent,
								lastNameStudent, stLevelStudent, gpaStudent, emailStudent, bdateStudent);
						list.add(studentsResponse);

					} else {
						System.out.println("Colon not found in the string.");
					}

				}
			}

		}

		response.setResponse(list);
		response.setStatus(true);
		response.setSuccessMessage("Student List");

		return response;
	}

	public ResponseObject studentDataByBNumber(String bNumber) {
		ResponseObject response = new ResponseObject();

		String sql = "SELECT * FROM students WHERE B# = ?";
		StudentsResponse student = jdbcTemplate.queryForObject(sql, new Object[] { bNumber },
				(rs, rowNum) -> new StudentsResponse(rs.getString("B#"), rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"), rs.getString("ST_LEVEL"), rs.getString("GPA"), rs.getString("EMAIL"),
						rs.getString("BDATE")));
		if (student != null) {
			response.setResponse(student);
			response.setStatus(true);
			response.setSuccessMessage("Student Data Info");

		} else {
			response.setErrorMessage("No student found with student number : " + bNumber);
			response.setStatus(false);
		}

		return response;
	}

}
