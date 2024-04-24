package com.srs.controller;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srs.request.StudentsRequest;
import com.srs.response.ResponseObject;
import com.srs.service.StudentsService;

@RestController
public class StudentsController {

	private static final Logger logger = LoggerFactory.getLogger(StudentsController.class);

	@Autowired
	private StudentsService studentsService;

	@PostMapping(value = "/addStudent")
	public ResponseObject addStudent(@RequestBody StudentsRequest studentsRequest) {
		ResponseObject response = new ResponseObject();

		try {
			response = studentsService.addStudents(studentsRequest);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return response;
	}

	@DeleteMapping(value = "/deleteStudent")
	public ResponseObject deleteStudent(@RequestParam("bNumber") String bNumber) {

		ResponseObject response = new ResponseObject();

		try {
			response = studentsService.deleteStudent(bNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;

	}

}
