package com.bridgelabz.fundoonotes.user.dao;

import com.bridgelabz.fundoonotes.user.model.User;

public interface IUserDao {

	long save(User user);
	User getByEmail(String email);
	User getById(long id);
	boolean update(User user);
}
