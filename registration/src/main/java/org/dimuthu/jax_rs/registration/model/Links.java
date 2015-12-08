package org.dimuthu.jax_rs.registration.model;

public class Links {
	private String ref;
	private String link;
	
	public Links(){
		
	}
	
	public Links(String ref,String link){
		this.ref = ref;
		this.link = link;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
