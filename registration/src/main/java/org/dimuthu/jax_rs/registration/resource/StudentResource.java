package org.dimuthu.jax_rs.registration.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.dimuthu.jax_rs.registration.exception.RegistrationNotFound;
import org.dimuthu.jax_rs.registration.model.ClassRoom;
import org.dimuthu.jax_rs.registration.model.Links;
import org.dimuthu.jax_rs.registration.model.Student;
import org.dimuthu.jax_rs.registration.service.ClassRoomService;
import org.dimuthu.jax_rs.registration.service.StudentService;

/**
 * 
 * @author Dimuthu Abeynayake
 * Student Resource meant to handle all the requested intended for as JSON request for Student operations and responding 
 * on both JSON and XML.
 * Currently supports for URLs : 
 * 	GET : students/<StudentId> : Fetches Students by the given Id
 *        students			   : Fetches All the students Registered
 *  POST: 					   : Saves Student
 *  PUT : <StudentId>/classrooms/{ClasRoomID} : Assign existing Students class rooms
 *  DELETE: students/<StudentId> : Remove registered students 
 */
@Path("/students")
@Produces(value = {MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

	private ClassRoomResource classResource = new ClassRoomResource();

	@GET
	@Path("/{studentId}")
	public Student getStudent(@PathParam("studentId") int studentId,@Context UriInfo uriInfor) {
		Student student = StudentService.getInstance().getStudent(studentId);
		addLinksToStudent(student,uriInfor);
		return student;
	}

	private void addLinksToStudent(Student student, UriInfo uriInfor) {
		
		List<Links> links = new ArrayList<>();
		
		UriBuilder uri = uriInfor.getBaseUriBuilder()
				.path(StudentResource.class)
				.path(String.valueOf(student.getId()));
		
		
		links.add(new Links("self", uri.build().toString()));
		
		if(student.getClassId() > 0){
		uri = uriInfor.getBaseUriBuilder()
		.path(StudentResource.class)
		.path(StudentResource.class, "getClassRoomService")
		.path(String.valueOf(student.getClassId()))
		.resolveTemplate("studentId", student.getId());
		
		links.add(new Links("classRoom", uri.build().toString()));
		}else{
			links.add(new Links("classRoom", "Not assigned"));
		}
		
		student.setLinks(links);
	}

	@GET
	public ArrayList<Student> getStudent(@Context UriInfo uriInfor){
		ArrayList<Student> students = StudentService.getInstance().getStudents();
		for (int i = 0; i < students.size(); i++) {
			addLinksToStudent(students.get(i),uriInfor);
		}
		return students;
	}

	@POST
	public Student saveStudent(Student student){
		return StudentService.getInstance().addStudent(student);
	}

	@PUT
	public Student updateStudent(Student student){
		return StudentService.getInstance().addStudent(student);
	}

	/**
	 * Assign a registered student a valid class which have already registered by the given id's
	 * @param studentID the number of the student which the class is been allocated
	 * @param classRoomId the number of the class room which the student been allocated
	 * @return Student object after class has been allocated
	 */
	@PUT
	@Path("/{studentId}/classrooms/{classroomId}")
	public Student assignStudentToClassRoom(@PathParam("studentId") int studentID, @PathParam("classroomId") int classRoomId){

		Student student = StudentService.getInstance().getStudent(studentID);

		if(classResource.isClassAvailableById(classRoomId) == false){
			throw new RegistrationNotFound("Class Not Found by the Class room ID : "	+ classRoomId);
		}
		student.setClassId(classRoomId);
		return student;
	}

	/**
	 * Fetches the class room by the link of student
	 * @param studentID
	 * @param classRoomId
	 * @return
	 */
	@GET
	@Path("/{studentId}/classrooms/{classroomId}")
	public ClassRoom getClassRoomByStudent(@PathParam("studentId") int studentID, @PathParam("classroomId") int classRoomId){
		Student student = StudentService.getInstance().getStudent(studentID);
		return ClassRoomService.getInstance().getClassRoom(student.getClassId());
	}
	
	@DELETE
	@Path("/{studentId}")
	public Student removeStudent(@PathParam("studentId") int studentId){
		return StudentService.getInstance().removeStudent(studentId);
	}

	@POST
	@Path("/{studentId}/classrooms")
	public ClassRoomResource getClassRoomService(){
		return classResource;
	}
}
