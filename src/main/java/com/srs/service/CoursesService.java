package com.srs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

	public ResponseObject addCourses(CourseRequest courseRequest) {
		ResponseObject response = new ResponseObject();
		try {

			String addCoursesSql = "INSERT INTO COURSES(DEPT_CODE,COURSE#,TITLE) VALUES(?,?,?)";
			jdbcTemplate.update(addCoursesSql, courseRequest.getDeptCode(), courseRequest.getCourse(),
					courseRequest.getTitle());
			
			

			response.setStatus(true);
			response.setSuccessMessage("courses data saved to database");

		} catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}

		return response;
	}

	public ResponseObject deleteCourse(String courseId,String deptCode) {
		ResponseObject response = new ResponseObject();

		try {

			String deleteCourseQuery = "DELETE COURSES WHERE course# = ? AND DEPT_CODE= ?";
			jdbcTemplate.update(deleteCourseQuery, courseId,deptCode);

			response.setStatus(true);
			response.setSuccessMessage("course deleted");

		} catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}

		return response;
	}

	public ResponseObject viewCourseById(String courseId,String deptCode) {
		ResponseObject response = new ResponseObject();
		try {
			CourseRequest courseRequest = new CourseRequest();

			String viewCourseByIdQuery = "SELECT * FROM COURSES WHERE COURSE# = ? AND DEPT_CODE=? ";
			// jdbcTemplate.batchUpdate(viewClassByIdQuery , classId);
			jdbcTemplate.queryForObject(viewCourseByIdQuery, new Object[] { courseId,deptCode }, (rs, rowNum) -> {

				courseRequest.setDeptCode(rs.getString("DEPT_CODE"));
				courseRequest.setCourse(rs.getString("COURSE#"));
				courseRequest.setTitle(rs.getString("TITLE"));

				return courseRequest;
			});

			response.setStatus(true);
			response.setSuccessMessage("course data by courseId");
			response.setResponse(courseRequest);

		} catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}

		return response;
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

						if (deptCode != -1 && course != -1 && title != -1) {

							String deptCodeShowCourses = parts[0].trim().substring(deptCode + 1).trim();
							String courseShowCourses = parts[1].trim().substring(course + 1).trim();
							String titleShowCourses = parts[2].trim().substring(title + 1).trim();

							CoursesResponse coursesResponse = new CoursesResponse(deptCodeShowCourses,
									courseShowCourses, titleShowCourses);
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

		} catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}

	public ResponseObject editCourse(CourseRequest courseRequest) {
		ResponseObject response = new ResponseObject();
		try {
			
			
			String editCourseQuery = "UPDATE COURSES SET DEPT_CODE=? ,TITLE=? WHERE COURSE# = ?";
			
			jdbcTemplate.update(editCourseQuery , courseRequest.getDeptCode(),courseRequest.getTitle(),courseRequest.getCourse());
			
			response.setStatus(true);
			response.setSuccessMessage("course data successfully update");
			
		}catch (Exception e) {
			response.setStatus(false);
			response.setErrorMessage(e.getMessage());
		}
		
		return response;
	}

}
