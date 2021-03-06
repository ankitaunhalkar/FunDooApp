package com.bridgelabz.fundoonotes.user.model;

import java.io.Serializable;

public class Mail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String to;

	private String subject;

	private String name;
	
	private String body;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
