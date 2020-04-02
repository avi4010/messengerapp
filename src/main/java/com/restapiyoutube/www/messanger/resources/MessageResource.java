package com.restapiyoutube.www.messanger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.restapiyoutube.www.model.Message;
import com.restapiyoutube.www.service.MessageService;

@Path("/messages")
public class MessageResource {
	
	MessageService msgService = new MessageService();
	
/*	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getAllMessages(){
		return msgService.getAllMessages();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getAllMessagesFromMap(){
		return msgService.getAllMessagesFromMap();
	}
*/	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getAllMessagesFromMap2(@QueryParam("year") int year, @QueryParam("start") int start, @QueryParam("size") int size){
		
		if(year>0){
			return msgService.getAllMessagesForYear(year);
		}
		if(start >0 && size >0){
			return msgService.getAllMessagesPaginated(start, size);
		}
		
		return msgService.getAllMessagesFromMap();
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessage(@PathParam("messageId") long msgId){
		
		Message newMsg = msgService.getMessage(msgId);
		
		return Response.status(Status.OK)
						   .entity(newMsg)
						   .build();
		
//		return msgService.getMessage(msgId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessagesFromMap(Message message, @Context UriInfo uriInfo){
		
		Message newMsg = msgService.addMessage(message);
		String newMessageId = String.valueOf(newMsg.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newMessageId).build();
//		return Response.status(Status.CREATED)
		
		return Response.created(uri)
					   .entity(newMsg)
					   .build();

//		return msgService.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMessagesFromMap(@PathParam("messageId") long msgId, Message message, @Context UriInfo uriInfo){
		
		message.setId(msgId);
		Message newMsg = msgService.updateMessage(message);
		URI uri = uriInfo.getAbsolutePath();
		
		return Response.seeOther(uri)
				   .status(Status.OK)
				   .entity(newMsg)
				   .build();
//		return msgService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMessagesFromMap(@PathParam("messageId") long msgId, @Context UriInfo uriInfo){
		
		Message newMsg = msgService.removeMessage(msgId);
		URI uri = uriInfo.getAbsolutePath();
		
		return Response.seeOther(uri)
				   .status(Status.OK)
				   .entity(newMsg)
				   .build();
//		return msgService.removeMessage(msgId);
	}

}
