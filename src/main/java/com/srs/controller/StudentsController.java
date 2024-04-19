package com.srs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.srs.request.StudentsRequest;
import com.srs.response.ResponseObject;
import com.srs.service.StudentsService;


@RestController
public class StudentsController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentsController.class);
	
	@Autowired
	private StudentsService studentsService;
	
	@PostMapping(name = "addStudent")
	public ResponseObject addStudent(@RequestHeader StudentsRequest studentsRequest){
		ResponseObject response = new ResponseObject();
		
		response = studentsService.addStudents(studentsRequest);
		
		return response;
	}
		
	

}
