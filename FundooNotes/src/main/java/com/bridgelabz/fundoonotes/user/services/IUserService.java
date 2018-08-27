package com.bridgelabz.fundoonotes.user.services;


import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.fundoonotes.user.model.LoginDto;
import com.bridgelabz.fundoonotes.user.model.Mail;
import com.bridgelabz.fundoonotes.user.model.RegisterDto;
import com.bridgelabz.fundoonotes.user.model.ResetPasswordDto;

public interface IUserService {

	long register(RegisterDto user, String url);
	LoginDto login(LoginDto user);
	boolean verify(String token);
	long forgotPassword(ResetPasswordDto userDto, String url);
	boolean resetPassword(String token, HttpServletResponse response);
	boolean changePassword(ResetPasswordDto userPassword, String token);
	void mailSender(Mail mailObj);
	LoginDto updateProfile(String token, LoginDto profile);
}
