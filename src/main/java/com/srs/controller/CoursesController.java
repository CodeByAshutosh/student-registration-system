package com.srs.controller;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srs.request.ClassesRequest;
import com.srs.request.CourseRequest;
import com.srs.response.ResponseObject;
import com.srs.service.CoursesService;

@RestController
public class CoursesController {
	
	private static final Logger logger = LoggerFactory.getLogger(CoursesController.class);

	@Autowired
	private CoursesService coursesService;
	
	@PostMapping(value = "/addCourses")
	public ResponseObject addCourses(@RequestBody CourseRequest courseRequest) {
		ResponseObject response = new ResponseObject();

		response = coursesService.addCourses(courseRequest);

		return response;
	}

	@DeleteMapping(value = "/deleteCourse")
	public ResponseObject deleteCourse(@RequestParam("courseId") String courseId
			,@RequestParam("deptCode") String deptCode) {

		ResponseObject response = new ResponseObject();

		try {
			response = coursesService.deleteCourse(courseId,deptCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;

	}

	@GetMapping(value = "/showCourse")
	public ResponseObject showCourse() {
		ResponseObject response = new ResponseObject();

		try {
			response = coursesService.showCourse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@GetMapping(value = "/viewCourseById")
	public ResponseObject viewCourseById(@RequestParam("courseId") String courseId
			, @RequestParam("deptCode") String deptCode) {
		ResponseObject response = new ResponseObject();

		try {
			response = coursesService.viewCourseById(courseId , deptCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@PutMapping(value = "/editCourse/{courseId}")
	public ResponseObject editCourse(@PathVariable String courseId ,@RequestBody CourseRequest courseRequest) {
		ResponseObject response = new ResponseObject();

		try {
			courseRequest.setCourse(courseId);
			response = coursesService.editCourse(courseRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	

	
	

}
