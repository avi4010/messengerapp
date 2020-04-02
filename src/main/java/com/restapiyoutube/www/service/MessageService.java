package com.restapiyoutube.www.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.restapiyoutube.www.database.DBConnection;
import com.restapiyoutube.www.database.DatabaseClass;
import com.restapiyoutube.www.exception.DataNotFoundException;
import com.restapiyoutube.www.model.Message;

public class MessageService {
	
	private Map<Long,Message> messages = DatabaseClass.getMessages();
	
/*	public MessageService(){
		
		  messages.put(1L, new Message(1, "Hello Karthik", "Karthik"));
		  messages.put(2L, new Message(2, "Hello Vinay", "Vinay")); 
		  messages.put(3L, new Message(3, "Hello AV", "AV")); 
		  messages.put(4L, new Message(4, "Hello Avinash", "Avinash"));	 
		
	}
	
	public List<Message> getAllMessages(){
		
		Message m1 = new Message(1L, "Hello Karthik", "Karthik");
		Message m2 = new Message(2L, "Hello Vinay", "Vinay");
		Message m3 = new Message(3L, "Hello AV", "AV");
		Message m4 = new Message(4L, "Hello Pandu", "Pandu");
		
		List<Message> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		list.add(m3);
		list.add(m4);
		
		return list;
		
	}
*/	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year){
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if(start + size > list.size())
			return new ArrayList<Message>();
		return list.subList(start, start+size);
	}
	
	public List<Message> getAllMessagesFromMap(){
		
		try {
			String query = "select * from messages";
			Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Message> list = new ArrayList<Message>();
			
			while(resultSet.next()) {
				Message msg = new Message();
				msg.setId(resultSet.getLong(1));
				msg.setMessage(resultSet.getString(2));
				msg.setAuthor(resultSet.getString(3));
				msg.setCreated(resultSet.getTimestamp(4));
				list.add(msg);
			}
			
			return list;
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("Error has occured while process the request localized message is "+e.getLocalizedMessage());
		}
		return null;

	}
	
	public Message getMessage(long id) {
		
		Message msg = new Message();
		try {
			String query = "select * from messages where messageId = "+id;
			Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				msg.setId(resultSet.getLong(1));
				msg.setMessage(resultSet.getString(2));
				msg.setAuthor(resultSet.getString(3));
				msg.setCreated(resultSet.getTimestamp(4));
			}
			
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("Error has occured while process the GET request localized message is "+e.getLocalizedMessage());
		}

		return msg;
	}
	
	public Message addMessage(Message message){
		
		message.setCurrentTime();
		try {
			String query = "INSERT INTO messages (messageInfo, author, date) VALUES ("
							+"'"+message.getMessage()+"',"
							+"'"+message.getAuthor()+"',"
							+"'"+message.getCreated()+"')";
			Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			int status = preparedStatement.executeUpdate();
			if(status > 0) {
				String lastEntry = "select messageId from messages ORDER BY messageId DESC LIMIT 1";
				preparedStatement = connection.prepareStatement(lastEntry);
				ResultSet resultSet = preparedStatement.executeQuery();
				if(resultSet.next())
					message.setId(resultSet.getLong(1));
			}
			
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("Error has occured while process the POST request localized message is "+e.getLocalizedMessage());
		}
		
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId() <= 0)
			return null;
		try {
				String query = "UPDATE messages SET "
								+"messageInfo="+"'"+message.getMessage()+"',"
								+"author="+"'"+message.getAuthor()+"'"
								+" WHERE messageId ="+message.getId();
				Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				int status = preparedStatement.executeUpdate();
				if(status > 0) {
					return message;
				}
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("Error has occured while process the PUT request localized message is "+e.getLocalizedMessage());
		}
		return null;
	}
	
	public Message removeMessage(long id){
		
		Message message = new Message();
		message.setId(id);
		try {
			String query = "delete from messages where messageId = "+id;
			Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			int status = preparedStatement.executeUpdate();
			if(status > 0) {
				return message;
			}
			
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("Error has occured while process the DELETE request localized message is "+e.getLocalizedMessage());
		}
		return null;
	}

}
