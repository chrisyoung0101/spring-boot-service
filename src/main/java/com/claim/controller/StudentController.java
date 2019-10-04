package com.claim.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.claim.entity.Student;
import com.claim.repository.StudentRepository;


@CrossOrigin
@RestController // Only use both for school here at Claim
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	// This configures the servlet and processes the student
	@RequestMapping(value = "/submitStudentDetails", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST)
	public void submitStudentDetails(@RequestBody Student student) {
		studentRepository.save(student);
	}

	@RequestMapping(value = "/findStudentById", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Student> findStudent(String email) {
		Student student = studentRepository.findById(email).get();
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@RequestMapping(value = "/loginStudent", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Student> loginStudent(@RequestBody Student student) {
//		System.out.println("this is the student email: " + student.getEmail());
		Student tempStudent = studentRepository.findById(student.getEmail()).get();
//		System.out.println("this is the student" + tempStudent);
		if (tempStudent.getEmail().equals(student.getEmail()) && tempStudent.getPassword().equals(student.getPassword())) {
			return new ResponseEntity<>(tempStudent, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/getAllStudents", 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.GET)
	@ResponseBody
	public List<Student> getAllStudents() {
			return studentRepository.findAll();
		}

}
