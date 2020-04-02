package com.restapiyoutube.www.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.restapiyoutube.www.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		
		System.out.println("This method is executed");
		ErrorMessage errMsg = new ErrorMessage(ex.getMessage(),404,"www.errornotfound.com");
		return Response.status(Status.NOT_FOUND)
						.entity(errMsg)
						.build();
	}
	
}
