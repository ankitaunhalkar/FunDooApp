package com.bridgelabz.fundoonotes.services;

import com.bridgelabz.fundoonotes.model.User;

public interface IUserService {

	Integer register(User user);
	boolean login(User user);
}
