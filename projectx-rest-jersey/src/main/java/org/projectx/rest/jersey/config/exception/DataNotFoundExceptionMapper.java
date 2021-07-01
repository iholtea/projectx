package org.projectx.rest.jersey.config.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ionuth.data.DataNotFoundException;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errMsg = new ErrorMessage(ex.getMessage(), "404", "no documentation available");
		return Response.status(Status.NOT_FOUND).entity(errMsg).build();
	}
	
}
