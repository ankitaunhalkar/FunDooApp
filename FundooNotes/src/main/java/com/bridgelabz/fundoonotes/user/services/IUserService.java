package com.bridgelabz.fundoonotes.user.services;


import com.bridgelabz.fundoonotes.user.model.LoginDto;
import com.bridgelabz.fundoonotes.user.model.Mail;
import com.bridgelabz.fundoonotes.user.model.RegisterDto;
import com.bridgelabz.fundoonotes.user.model.ResetPasswordDto;

public interface IUserService {

	long register(RegisterDto user, String url);
	boolean login(LoginDto user);
	boolean verify(String token);
	void forgotPassword(ResetPasswordDto userDto, String url);
	boolean resetPassword(ResetPasswordDto userPassword, String token);
	void mailSender(Mail mailObj);
}
