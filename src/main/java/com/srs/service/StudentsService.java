package com.srs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srs.entity.StudentsEntity;
import com.srs.repository.StudentRepository;
import com.srs.request.StudentsRequest;
import com.srs.response.ResponseObject;
import com.srs.util.CommonUtils;

@Service
public class StudentsService {
	
	@Autowired
	private StudentRepository studentRepository;

	public ResponseObject addStudents(StudentsRequest studentsRequest) {
		ResponseObject response = new ResponseObject();
		StudentsEntity studentsEntity = new StudentsEntity();
		studentsEntity.setFirstName(studentsRequest.getFirstName());
		studentsEntity.setLastName(studentsRequest.getLastName());
		studentsEntity.setEmail(studentsRequest.getEmail());
		studentsEntity.setGpa(studentsRequest.getGpa());
		studentsEntity.setbDate(CommonUtils.convertDateStringToDate_FormateYYYYMMDDSeperatedByHyphen(studentsRequest.getBdate()));
		studentsEntity.setStLevel(studentsRequest.getStLevel());
		
		studentRepository.save(studentsEntity);
		
		
		return response;
	}

}
