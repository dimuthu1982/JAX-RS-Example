package org.dimuthu.jax_rs.registration.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClassRoom {
	private int id;

	private String className;

	public ClassRoom(){}
	
	public ClassRoom(int id,String className){
		this.id = id;
		this.className = className;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
