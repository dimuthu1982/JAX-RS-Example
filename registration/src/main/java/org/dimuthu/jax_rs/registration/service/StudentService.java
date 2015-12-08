package org.dimuthu.jax_rs.registration.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.dimuthu.jax_rs.registration.exception.RegistrationNotFound;
import org.dimuthu.jax_rs.registration.model.Student;

public class StudentService {
private static StudentService service;
	
	private static Map<Integer, Student> studentHolder = new HashMap<>();
	
	private StudentService(){
	}
	
	public static StudentService getInstance() {
		if (service == null) {
			service = new StudentService();
		}
		return service;
	}
	
	public Student addStudent(Student student) {
		if (student.getId() == 0) {
			student.setId(studentHolder.size() + 1);
			studentHolder.put(student.getId(), student);
		}else{
			studentHolder.put(student.getId(), student);
		}
		return student;
	}
	
	public Student removeStudent(int studentId){
		if(isAvailable(studentId) == false){
			throw new RegistrationNotFound("Unable to Delete : Student Not Found by the Student ID : "	+ studentId);
		}
		
		return studentHolder.remove(studentId);
	}
	
	public Student getStudent(int studentId){
		
		Student student = studentHolder.get(studentId);
		if (student == null) {
			throw new RegistrationNotFound("Student Not Found by the Student ID : "	+ studentId);
		}
		return student;
	}

	public ArrayList<Student> getStudents() {
		return new ArrayList<Student>(studentHolder.values());
	}
	
	public boolean isAvailable(int studentId){
		return studentHolder.containsKey(studentId);
	}
}
