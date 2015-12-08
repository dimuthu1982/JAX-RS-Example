package org.dimuthu.jax_rs.registration.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
	private int id;
	
	private int classId;
	
	private String name;
	
	private List<Links> links = new ArrayList<>();
	
	public List<Links> getLinks() {
		return links;
	}

	public void setLinks(List<Links> links) {
		this.links = links;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
