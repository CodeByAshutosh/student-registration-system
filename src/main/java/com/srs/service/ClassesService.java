package com.srs.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.srs.request.ClassesRequest;
import com.srs.response.ClassesResponse;
import com.srs.response.CoursesResponse;
import com.srs.response.ResponseObject;

@Service
public class ClassesService {
	
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
    
	public ResponseObject addClasses(ClassesRequest classesRequest) {

		ResponseObject response = new ResponseObject();
		try {
			String addClassesQuery = "INSERT INTO CLASSES(CLASSID,DEPT_CODE,COURSE#,SECT#,YEAR,SEMESTER,LIMIT,CLASS_SIZE,ROOM) VALUES(?,?,?,?,?,?,?,?,?)";
			jdbcTemplate.update(addClassesQuery ,classesRequest.getClassId() , classesRequest.getDeptCode(),classesRequest.getCourse(),classesRequest.getSect(),classesRequest.getYear(),
					classesRequest.getSemester(),classesRequest.getLimit(),classesRequest.getClassSize(),classesRequest.getRoom());
			
			
			response.setStatus(true);
			response.setSuccessMessage("Classes data saved to database");
			
		}catch(Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}

	public ResponseObject showClasses() {
		ResponseObject response = new ResponseObject();
		
		List<ClassesResponse> list = new ArrayList<>();
		try {
			
			String showClasses = "show_classes()";

			String[] lines = oracleService.callProcedure(showClasses);

			for (String line : lines) {
				if (line != null) {

					String[] parts = line.split(","); // Split by comma
					if (parts.length == 9) {

						int classId = parts[0].trim().indexOf(':');
						int deptCode = parts[1].trim().indexOf(':');
						int course = parts[2].trim().indexOf(':');
						int sect = parts[3].trim().indexOf(':');
						int year = parts[4].trim().indexOf(':');
						int semester = parts[5].trim().indexOf(':');
						int limit = parts[6].trim().indexOf(':');
						int classSize = parts[7].trim().indexOf(':');
						int room = parts[8].trim().indexOf(':');
						
						
						if (classId != -1 && deptCode != -1 && course != -1 
								&& sect != -1  && year != -1  && semester != -1 
								&& limit != -1 
								&& classSize != -1 && room != -1 ) {

							String classIdShowClasses = parts[0].trim().substring(classId + 1).trim();
							String deptCodeShowClasses = parts[1].trim().substring(deptCode + 1).trim();
							String courseShowClasses = parts[2].trim().substring(course + 1).trim();
							
							String sectShowClasses = parts[3].trim().substring(sect + 1).trim();
							String yearShowClasses = parts[4].trim().substring(year + 1).trim();
							String semesterShowClasses = parts[5].trim().substring(semester + 1).trim();
							
							String limitShowClasses = parts[6].trim().substring(limit + 1).trim();
							String classSizeShowClasses = parts[7].trim().substring(classSize + 1).trim();
							String roomShowClasses = parts[8].trim().substring(room + 1).trim();
							
							ClassesResponse classesResponse = new ClassesResponse(classIdShowClasses,deptCodeShowClasses,courseShowClasses
									,sectShowClasses,yearShowClasses,semesterShowClasses,limitShowClasses,
									classSizeShowClasses,roomShowClasses);
							list.add(classesResponse);

						} else {
							System.out.println("Colon not found in the string.");
						}

					}
				}

			}

			response.setResponse(list);
			response.setStatus(true);
			response.setSuccessMessage("classes list");
			
		}catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}

	public ResponseObject deleteClasses(String classId) {
		ResponseObject response = new ResponseObject();
		try {
			
			String deleteCourseQuery = "DELETE CLASSES WHERE CLASSID = ?";
			jdbcTemplate.update(deleteCourseQuery , classId);
			
			response.setStatus(true);
			response.setSuccessMessage("course deleted");
			
			
		}catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		
		return response;
	}

	public ResponseObject viewClassById(String classId) {
		ResponseObject response = new ResponseObject();
		try {
			ClassesRequest classesRequest = new ClassesRequest();
			
			String viewClassByIdQuery = "SELECT * FROM classes WHERE CLASSID = ?";
			//jdbcTemplate.batchUpdate(viewClassByIdQuery , classId);
			jdbcTemplate.queryForObject(viewClassByIdQuery, new Object[] {classId},
					(rs,rowNum) -> {
						
						classesRequest.setClassId(rs.getString("CLASSID"));
						classesRequest.setDeptCode(rs.getString("DEPT_CODE"));
						classesRequest.setCourse(rs.getString("COURSE#"));
						classesRequest.setSect(rs.getString("SECT#"));
						classesRequest.setYear(rs.getString("YEAR"));
						classesRequest.setSemester(rs.getString("SEMESTER"));
						classesRequest.setLimit(rs.getString("LIMIT"));
						classesRequest.setClassSize(rs.getString("CLASS_SIZE"));
						classesRequest.setRoom(rs.getString("ROOM"));
						
						return classesRequest;
					});
			
			response.setStatus(true);
			response.setSuccessMessage("class data by classId");
			response.setResponse(classesRequest);
			
			
		}catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		
		return response;
	}

	public ResponseObject editClass(ClassesRequest classesRequest) {
		ResponseObject response = new ResponseObject();
		try {
			
			
			String editStudentQuery = "UPDATE CLASSES SET DEPT_CODE = ?, COURSE# = ?,SECT#=?,YEAR=?,SEMESTER=?,LIMIT=?,CLASS_SIZE=?,ROOM=? WHERE CLASSID=?";
			
			jdbcTemplate.update(editStudentQuery , classesRequest.getDeptCode(),classesRequest.getCourse(),classesRequest.getSect(),classesRequest.getYear(),classesRequest.getSemester(),classesRequest.getLimit(),classesRequest.getClassSize(),classesRequest.getRoom(),
					classesRequest.getClassId());
			
			response.setStatus(true);
			response.setSuccessMessage("class data successfully update");
			
		}catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		
		return response;
	}

}
