package com.bridgelabz.fundoonotes.user.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.user.model.Email;

@Service
public class MailSender {

	@Autowired
	JmsTemplate jmsTemplate;
	
	public void sendMail( final Email email) {

		jmsTemplate.send(new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage(email);
				return objectMessage;
			}
		});
	}
}