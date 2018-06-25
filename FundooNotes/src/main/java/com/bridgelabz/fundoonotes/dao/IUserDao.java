package com.bridgelabz.fundoonotes.dao;

import com.bridgelabz.fundoonotes.model.User;

public interface IUserDao {

	Integer save(User user);
	User login(User user);
	User getByEmail(String email);
	
}
