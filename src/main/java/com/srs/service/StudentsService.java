package com.srs.service;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.srs.request.StudentsRequest;
import com.srs.response.ResponseObject;
import com.srs.response.StudentsResponse;

import oracle.jdbc.OracleTypes;

@Service
public class StudentsService {

//	@Autowired
//	private StudentRepository studentRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private OracleService oracleService;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	public ResponseObject addStudents(StudentsRequest studentsRequest) throws ParseException {
		ResponseObject response = new ResponseObject();

		try {

			// StudentsEntity students =
			// studentRepository.findByEmail(studentsRequest.getEmail());

			// Random random = new Random();
			// String randomNumber = String.format("%08d",
			// Integer.valueOf(random.nextInt(10001)));

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = dateFormat.parse(studentsRequest.getBdate());

			java.sql.Date sqlDate = new java.sql.Date(date.getTime());

			String procedureCall = "{call SAVE_STUDENT(?, ?,?,?,?,?,?)}";
			jdbcTemplate.update(procedureCall, studentsRequest.getbNumber(), studentsRequest.getFirstName(),
					studentsRequest.getLastName(), studentsRequest.getStLevel(), studentsRequest.getGpa(),
					studentsRequest.getEmail(), sqlDate);

			response.setStatus(true);
			response.setSuccessMessage("Student data saved to database");
		} catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}

		return response;
	}

	public ResponseObject deleteStudent(String bNumber) {
		ResponseObject response = new ResponseObject();
		//String[] lines = null;
		try {

			String deleteStudents = "delete_student('" + bNumber + "')";

			String procedureCall = "{ call ashukla4_proj2_package_spc." + deleteStudents + "}";

			try (Connection connection = DriverManager.getConnection(url, username, password);
			     CallableStatement enableOutput = connection.prepareCall("BEGIN DBMS_OUTPUT.ENABLE(NULL); END;");
			     CallableStatement callableStatement = connection.prepareCall(procedureCall);
			     CallableStatement getBuffer = connection.prepareCall("BEGIN DBMS_OUTPUT.GET_LINES(?, ?); END;")) {

			    // Enable server output
			    enableOutput.execute();

			    // Call the stored procedure
			    callableStatement.execute();

			    // Prepare to retrieve the server output
			    getBuffer.registerOutParameter(1, OracleTypes.ARRAY, "DBMSOUTPUT_LINESARRAY");
			    getBuffer.registerOutParameter(2, OracleTypes.INTEGER);
			    getBuffer.execute();

			    // Retrieve the output
			    Array array = getBuffer.getArray(1);
			    if (array != null) {
			        String[] lines = (String[]) array.getArray();
			        if (lines.length != 0) {
			            response.setStatus(true);
			            response.setSuccessMessage(lines[0].trim());
			        } else {
			        	response.setStatus(true);
			            response.setSuccessMessage("student data deleted");
			        }
			    } else {
			        // Handle null array case if needed
			    }

			} catch (SQLException e) {
			    // Handle any SQL exceptions here
			    e.printStackTrace();
			}

		} catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}

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

	public ResponseObject editStudent(StudentsRequest studentsRequest) {
		ResponseObject response = new ResponseObject();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = dateFormat.parse(studentsRequest.getBdate());

			java.sql.Date sqlDate = new java.sql.Date(date.getTime());

			String editStudentQuery = "UPDATE STUDENTS SET FIRST_NAME = ? ,LAST_NAME =?,ST_LEVEL =?,GPA= ?,EMAIL=?,BDATE=? WHERE B# = ?";

			jdbcTemplate.update(editStudentQuery, studentsRequest.getFirstName(), studentsRequest.getLastName(),
					studentsRequest.getStLevel(), studentsRequest.getGpa(), studentsRequest.getEmail(), sqlDate,
					studentsRequest.getbNumber());

			response.setStatus(true);
			response.setSuccessMessage("Student data successfully update");

		} catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}

		return response;
	}

}
