package com.bridgelabz.fundoonotes.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterDto {

	@Size(min = 2, max = 45)
	private String name;

	@Email
	@NotEmpty
	private String email;

	@Size(min = 3)
	private String password;

	@Size(min = 10, max = 10)
	@Pattern(regexp="(^$|[0-9]{10})")
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
