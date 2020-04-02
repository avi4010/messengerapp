/**
 * 
 */
package com.restapiyoutube.www.messanger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Avinash
 *
 */
@Path ("/helloworld")
public class HelloWorldResource {
	
	@GET
	@Produces (MediaType.TEXT_PLAIN)
	public String getMessages() {
		
		return "Hello Avinash!";
		
	}

}
