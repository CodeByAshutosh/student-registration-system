package com.srs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srs.request.UniqueRequest;
import com.srs.response.ResponseObject;
import com.srs.service.APIService;

@RestController
public class APIController {
	
	@Autowired
	private APIService apiService;
	
	@PostMapping(value = "/saveScore")
	public ResponseObject addScore(@RequestParam("score") String score , @RequestParam("lGrade") String lGrade) {
		ResponseObject response = new ResponseObject();

		try {
			response = apiService.saveScore(score , lGrade);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	@GetMapping(value = "/showLogs")
	public ResponseObject showLogs() {
		ResponseObject response = new ResponseObject();

		try {
			response = apiService.showLogs();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	@GetMapping(value = "/showEnrollments")
	public ResponseObject showEnrollments() {
		ResponseObject response = new ResponseObject();

		try {
			response = apiService.showEnrollments();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	
	@GetMapping(value = "/enrollStudent")
	public ResponseObject enrollStudent(@RequestParam("bNumber") String bNumber , @RequestParam("classId") String classId) {
		ResponseObject response = new ResponseObject();

		try {
			response = apiService.enrollStudent(bNumber , classId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	@GetMapping(value = "/saveEnrollments")
	public ResponseObject saveEnrollments(@RequestParam("bNumber") String bNumber , @RequestParam("classId") String classId,
			@RequestParam("score") String score) {
		ResponseObject response = new ResponseObject();

		try {
			response = apiService.saveEnrollments(bNumber , classId,score);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	
	@DeleteMapping(value = "/deleteEnrollments")
	public ResponseObject deleteEnrollments(@RequestParam("bNumber") String bNumber , @RequestParam("classId") String classId
			) {
		ResponseObject response = new ResponseObject();

		try {
			response = apiService.deleteEnrollments(bNumber , classId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	@PostMapping(value = "/uniqueData")
	public ResponseObject uniqueData(@RequestBody UniqueRequest uniqueRequest) {
		ResponseObject response = new ResponseObject();

		try {
			response = apiService.uniqueData(uniqueRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	
	@GetMapping(value = "/listStudentByClass")
	public ResponseObject listStudentByClassId(@RequestParam("classId") String classId) {
		ResponseObject response = new ResponseObject();

		try {
			response = apiService.listStudentByClassId(classId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	
	

}
