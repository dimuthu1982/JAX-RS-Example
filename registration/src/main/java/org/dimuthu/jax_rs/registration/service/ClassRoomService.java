package org.dimuthu.jax_rs.registration.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dimuthu.jax_rs.registration.exception.RegistrationNotFound;
import org.dimuthu.jax_rs.registration.model.ClassRoom;

public class ClassRoomService {
	private static ClassRoomService service;

	private static Map<Integer, ClassRoom> classHolder = new HashMap<>();

	private ClassRoomService() {
		classHolder.put(1, new ClassRoom(1,"1A"));
		classHolder.put(2, new ClassRoom(2,"1B"));
	}

	public static ClassRoomService getInstance() {
		if (service == null) {
			service = new ClassRoomService();
		}
		return service;
	}

	public ClassRoom addClassRoom(ClassRoom classRoom) {
		if (classRoom.getId() == 0) {
			classRoom.setId(classHolder.size() + 1);
			classHolder.put(classRoom.getId(), classRoom);
		}else{
			classHolder.put(classRoom.getId(), classRoom);
		}
		return classRoom;
	}

	public List<ClassRoom> addClassRooms(List<ClassRoom> classRooms) {
		List<ClassRoom> savedList = new ArrayList<>(classRooms.size());

		for (int i = 0; i < classRooms.size(); i++) {
			savedList.add(addClassRoom(classRooms.get(i)));
		}
		return savedList;
	}

	public ClassRoom getClassRoom(int classRoomId){

		ClassRoom classRoom = classHolder.get(classRoomId);
		if (classRoom == null) {
			throw new RegistrationNotFound("Class Room Not Found by the ClassRoom ID : "+ classRoomId);
		}
		return classRoom;
	}

	public ArrayList<ClassRoom> getAllClassRoom() {
		return new ArrayList<ClassRoom>(classHolder.values());
	}

	public boolean isClassAvailableById(int classRoomId) {
		return classHolder.containsKey(classRoomId);
	}
}
