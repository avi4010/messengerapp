package com.restapiyoutube.www.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.restapiyoutube.www.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		
		System.out.println("This method is executed");
		ErrorMessage errMsg = new ErrorMessage(ex.getMessage(),500,"www.goodtime.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity(errMsg)
						.type("application/json")
						.build();
	}
	
}
