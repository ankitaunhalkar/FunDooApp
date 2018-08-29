package com.bridgelabz.fundoonotes.util;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.user.model.Mail;

@Service
public class MailProducer {

	@Autowired
	JmsTemplate jmsTemplate;
	
	public void sendMail( final Mail email ) {

		jmsTemplate.send(new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage(email);
				return objectMessage;
			}
		});
	}
}
