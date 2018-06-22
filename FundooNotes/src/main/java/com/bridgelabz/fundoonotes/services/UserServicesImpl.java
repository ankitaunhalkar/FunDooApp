package com.bridgelabz.fundoonotes.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.fundoonotes.dao.UserDao;
import com.bridgelabz.fundoonotes.model.User;

public class UserServicesImpl implements UserServices{

	@Autowired
	UserDao userDao;
	
	@Override
	public User register(User user) {
		return userDao.register(user);
	}

	@Override
	public User login(User user) {
		return null;
	}

}
