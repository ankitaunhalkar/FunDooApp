package com.bridgelabz.fundoonotes.user.model;

import java.io.Serializable;

public class Email implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	
	private String emailId;
	
	private String token;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}