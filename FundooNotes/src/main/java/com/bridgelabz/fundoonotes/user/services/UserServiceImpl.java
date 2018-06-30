package com.bridgelabz.fundoonotes.user.services;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.user.dao.IUserDao;
import com.bridgelabz.fundoonotes.user.messaging.MailSender;
import com.bridgelabz.fundoonotes.user.model.Email;
import com.bridgelabz.fundoonotes.user.model.LoginDto;
import com.bridgelabz.fundoonotes.user.model.RegisterDto;
import com.bridgelabz.fundoonotes.user.model.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao userDao;
	
	@Autowired
	User user;
	
	@Autowired
	MailSender mailSender;
	
	@Autowired
	Email email;
	
	
	@Override
	@Transactional
	public int register(RegisterDto registrationUser) {
		
		int registeredStatus = 0;
		
		//setting into user model
		user.setName(registrationUser.getName());
		user.setEmail(registrationUser.getEmail());
		user.setPassword(registrationUser.getPassword());
		
		//checking if mail id is already exists or not
		User registerDaoStatus = userDao.getByEmail(registrationUser.getEmail());
				
		if (registerDaoStatus != null) {
			
			return registeredStatus;
			
		}
		
		//password encrypting
		registrationUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		
		//if not existing then save
		registeredStatus = userDao.save(user);
	
		//setting to email model
		email.setName(registrationUser.getName());
		email.setEmailId(registrationUser.getEmail());
		email.setToken("djbsdskfbf");
		
		//mail sending
		mailSender.sendMail(email);
		
		return registeredStatus;
	}

	@Override
	@Transactional
	public boolean login(LoginDto loginUser) {
		
		boolean status=false;
		
		//checking if mail id exists or not
		User loginDaoStatus = userDao.getByEmail(loginUser.getEmail());
				
		//if exists then matching the password 
		if ((loginDaoStatus != null) && (BCrypt.checkpw(loginUser.getPassword(), loginDaoStatus.getPassword()))) {
			
			return status = true;
		}
		
		return status;
	}

}