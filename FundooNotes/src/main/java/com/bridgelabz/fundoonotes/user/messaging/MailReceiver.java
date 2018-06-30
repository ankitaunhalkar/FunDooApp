package com.bridgelabz.fundoonotes.user.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.user.model.Email;

@Service
public class MailReceiver  {

	@Autowired
	JavaMailSender emailSender;
	
	@JmsListener(destination = "email-queue")
	public void receiveMail(Email emailObj) {
		
		System.out.println(emailObj.getName());
		System.out.println(emailObj.getEmailId());
		System.out.println(emailObj.getToken());
		System.out.println(emailObj.getEmailId());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emailObj.getEmailId());
		message.setSubject("FundoNotes Verification");
		message.setText("Dear "+emailObj.getName()+",");
		message.setText("http://localhost://8080"+emailObj.getToken());
		
		emailSender.send(message);
	}	
}