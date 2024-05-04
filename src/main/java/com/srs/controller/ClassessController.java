package com.srs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.srs.request.ClassesRequest;
import com.srs.response.ResponseObject;
import com.srs.service.ClassesService;

public class ClassessController {
	
	@Autowired
	private ClassesService classesService;
	
	@PostMapping(value = "/addClasses")
	public ResponseObject addClasses(@RequestBody ClassesRequest classesRequest) {
		ResponseObject response = new ResponseObject();

		response = classesService.addClasses(classesRequest);

		return response;
	}
	
	@PostMapping(value = "/showClasses")
	public ResponseObject showClasses() {
		ResponseObject response = new ResponseObject();

		response = classesService.showClasses();

		return response;
	}
	
	@DeleteMapping(value = "/deleteClasses")
	public ResponseObject deleteClasses(@RequestParam("classId") String classId) {
		ResponseObject response = new ResponseObject();

		response = classesService.deleteClasses(classId);

		return response;
	}

}
