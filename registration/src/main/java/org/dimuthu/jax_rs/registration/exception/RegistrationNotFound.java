package org.dimuthu.jax_rs.registration.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.dimuthu.jax_rs.registration.model.ErrorMessage;

/**
 * @author Dimuthu Abeynayake
 * This is a descended of the javax.ws.rs.NotFoundException which use to report no data found exceptions to the client in a JSON format 
 * incorporated to ErrorMessage custom object
 */
public class RegistrationNotFound extends NotFoundException{

	private static final long serialVersionUID = 1L;

	public RegistrationNotFound(String message){
		super(Response.status(Status.NOT_FOUND).entity(new ErrorMessage(message,404)).build());
	}
}
