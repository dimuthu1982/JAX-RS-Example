package org.dimuthu.jax_rs.registration.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dimuthu.jax_rs.registration.model.ClassRoom;
import org.dimuthu.jax_rs.registration.service.ClassRoomService;

/**
 * @author Dimuthu Abeynayake
 * Class Room Resource meant to store and return information by JSON request for Class room operations and responding 
 * on both JSON and XML.
 * Currently supports for URLs : 
 * 	GET : classrooms/<classId> : Fetches Class rooms by the given Id
 *        classrooms		   : Fetches All the class rooms Registered
 *  POST: 					   : Saves class room
 *
 */
@Path("/")
@Produces(value = {MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class ClassRoomResource {

	@GET
	@Path("/classrooms/{classId}")
	public ClassRoom getClassRoom(@PathParam("classId") int classId){
		return ClassRoomService.getInstance().getClassRoom(classId);
	}
	
	@GET
	@Path("/classrooms")
	public List<ClassRoom> getAllClassRoom(){
		return ClassRoomService.getInstance().getAllClassRoom();
	}
	
	@POST
	@Path("/classrooms")
	public ClassRoom saveClassRoom(ClassRoom classRoom){
		return ClassRoomService.getInstance().addClassRoom(classRoom);
	}

	public boolean isClassAvailableById(int classRoomId) {
		return ClassRoomService.getInstance().isClassAvailableById(classRoomId);
	}
}
