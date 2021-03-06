package com.bridgelabz.fundoonotes.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	@Size(min = 2, max = 45)
	private String name;

	@Column
	@Email
	@NotEmpty
	private String email;

	@Column
	@Size(min = 3)
	private String password;

	@Column
	@Size(min = 10, max = 10)
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String phone;

	@Column
	private boolean isVerified;

	@Column
	private String profile;
	
	public User() {

	}

	public User(RegisterDto registerDto) {
		
		this.name = registerDto.getName();
		this.email = registerDto.getEmail();
		this.password = registerDto.getPassword();
		this.phone = registerDto.getPhone();
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
