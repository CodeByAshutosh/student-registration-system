package com.srs.controller;

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
import com.srs.request.StudentsRequest;
import com.srs.response.ResponseObject;
import com.srs.service.ClassesService;

@RestController
public class ClassessController {
	
	@Autowired
	private ClassesService classesService;
	
	@PostMapping(value = "/addClasses")
	public ResponseObject addClasses(@RequestBody ClassesRequest classesRequest) {
		ResponseObject response = new ResponseObject();

		response = classesService.addClasses(classesRequest);

		return response;
	}
	
	@GetMapping(value = "/showClasses")
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

	
	@GetMapping(value = "/viewClassById")
	public ResponseObject viewClassById(@RequestParam("classId") String classId) {
		ResponseObject response = new ResponseObject();

		response = classesService.viewClassById(classId);

		return response;
	}
	
	@PutMapping(value = "/editClass/{classId}")
	public ResponseObject editClass(@PathVariable String classId ,@RequestBody ClassesRequest classesRequest) {
		ResponseObject response = new ResponseObject();

		try {
			classesRequest.setClassId(classId);
			response = classesService.editClass(classesRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
}
