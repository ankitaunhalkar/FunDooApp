package com.bridgelabz.fundoonotes.dao;

import com.bridgelabz.fundoonotes.model.User;

public interface UserDao {


	User register(User user);
	User login(User user);
	
}
