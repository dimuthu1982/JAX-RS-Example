package org.dimuthu.jax_rs.registration.model;

public class ErrorMessage {
	
private String errorMessage;
	
	private int errorCode;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorMessage(){
	}
	
	public ErrorMessage(String errorMessage,int errorCode){
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
