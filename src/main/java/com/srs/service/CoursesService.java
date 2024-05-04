package com.srs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.srs.request.CourseRequest;
import com.srs.response.CoursesResponse;
import com.srs.response.ResponseObject;

@Service
public class CoursesService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private OracleService oracleService;

	public ResponseObject addCourses(CourseRequest courseRequest) {
		ResponseObject response = new ResponseObject();
		try {
			
			String addCoursesSql = "INSERT INTO COURSES(DEPT_CODE,COURSE#,TITLE) VALUES(?,?,?)";
			jdbcTemplate.batchUpdate(addCoursesSql ,courseRequest.getCourse(),courseRequest.getDeptCode(),courseRequest.getTitle());
			
			response.setStatus(true);
			response.setSuccessMessage("courses data saved to database");
			
		}catch(Exception e){
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		
		return response;
	}

	public ResponseObject deleteCourse(String courseId) {
		ResponseObject response = new ResponseObject();
		
		try {
			
			String deleteCourseQuery = "DELETE COURSES WHERE course# = ?";
			jdbcTemplate.batchUpdate(deleteCourseQuery , courseId);
			
			response.setStatus(true);
			response.setSuccessMessage("course deleted");
			
		}catch(Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		
		return response;
	}

	

	public ResponseObject courseDataByCourseId(String courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseObject showCourse() {
		ResponseObject response = new ResponseObject();
		List<CoursesResponse> list = new ArrayList<>();
		try {
			
			String showCourses = "show_courses()";

			String[] lines = oracleService.callProcedure(showCourses);

			for (String line : lines) {
				if (line != null) {

					String[] parts = line.split(","); // Split by comma
					if (parts.length == 3) {

						int deptCode = parts[0].trim().indexOf(':');
						int course = parts[1].trim().indexOf(':');
						int title = parts[2].trim().indexOf(':');
						

						if (deptCode != -1 && course != -1 && title != -1 
								) {

							String deptCodeShowCourses = parts[0].trim().substring(deptCode + 1).trim();
							String courseShowCourses = parts[1].trim().substring(course + 1).trim();
							String titleShowCourses = parts[2].trim().substring(title + 1).trim();
							
							CoursesResponse coursesResponse = new CoursesResponse(deptCodeShowCourses,courseShowCourses,titleShowCourses);
							list.add(coursesResponse);

						} else {
							System.out.println("Colon not found in the string.");
						}

					}
				}

			}

			response.setResponse(list);
			response.setStatus(true);
			response.setSuccessMessage("course list");
			
		}catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}

}
