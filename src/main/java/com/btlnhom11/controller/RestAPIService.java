package com.btlnhom11.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btlnhom11.model.Student;

@RestController
@RequestMapping(value = {"/api/v1"})
public class RestAPIService {
	
	private List<Student> students = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		students.add(null);
		students.add(new Student(1,"Nguyen A",3.5f));
		students.add(new Student(2,"Nguyen B",2.5f));
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Student>> getAll() {
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/add")
	public ResponseEntity add(@RequestBody Student newStudent) {
		if (newStudent.getId() == null) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Id is null");
					
		} else {
			students.add(newStudent);
			return new ResponseEntity<Student>(newStudent, HttpStatus.OK);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/update/{id}")
	public ResponseEntity update(@PathVariable Integer id, @RequestBody Student newStudent) {
		for (Student student : students) {
			if (student == null) continue;
			if (student.getId().equals(id)) {
				student.setName(newStudent.getName());
				student.setCpa(newStudent.getCpa());
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(student);
			}
		}
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("student not found");
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		for (Student student : students) {
			if (student == null) continue;
			if (student.getId().equals(id)) {
				Student deleted = student;
				students.remove(student);
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(deleted);
			}
		}
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("student not found");
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/exception/{id}")
	public ResponseEntity testexception(@PathVariable Integer id, @RequestBody Student newStudent) {
		try {
			if (newStudent.getCpa() <= 0 || newStudent.getCpa() > 4) throw new ArithmeticException();
			for (Student student : students) {
				if (student == null) continue;
				if (student.getId().equals(id)) {
					student.setName(newStudent.getName());
					student.setCpa(newStudent.getCpa());
					return ResponseEntity
							.status(HttpStatus.OK)
							.body(student);
				}
			}
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("student not found");
		} catch (ArithmeticException e) {
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("has exception");
		}
	}
	
}
