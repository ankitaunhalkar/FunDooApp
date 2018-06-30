package com.bridgelabz.fundoonotes.user.services;

import com.bridgelabz.fundoonotes.user.model.LoginDto;
import com.bridgelabz.fundoonotes.user.model.RegisterDto;

public interface IUserService {

	int register(RegisterDto user);
	boolean login(LoginDto user);
}