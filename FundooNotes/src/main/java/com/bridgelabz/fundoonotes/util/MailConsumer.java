package com.bridgelabz.fundoonotes.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.user.model.Mail;
import com.bridgelabz.fundoonotes.user.services.IUserService;

@Service
public class MailConsumer {

	@Autowired
	IUserService userService;

	@JmsListener(destination = "email-queue")
	public void receiveMail(final Mail mailObj) {

		userService.mailSender(mailObj);
		
	}
}
