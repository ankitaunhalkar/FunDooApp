package com.bridgelabz.fundoonotes.user.dao;

import com.bridgelabz.fundoonotes.user.model.User;

public interface IUserDao {

	int save(User user);
	User getByEmail(String email);
	
}