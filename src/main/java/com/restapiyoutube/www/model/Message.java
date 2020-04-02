/**
 * 
 */
package com.restapiyoutube.www.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author avinash
 *
 */
@XmlRootElement
public class Message {
	
	private long id;
	private String message;
//	private Date created;
	private String author;
	private Timestamp created;
	
	public Message() {
		
	}
	
	public Message(String message, String author) {
		this.message = message;
		this.created = new Timestamp(System.currentTimeMillis());
		this.author = author;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
	public void setCurrentTime() {
		this.created = new Timestamp(System.currentTimeMillis());
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

}
