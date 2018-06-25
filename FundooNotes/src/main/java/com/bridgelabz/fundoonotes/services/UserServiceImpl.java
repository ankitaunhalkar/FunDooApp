package com.bridgelabz.fundoonotes.services;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dao.IUserDao;
import com.bridgelabz.fundoonotes.model.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao userDao;

	@Override
	@Transactional
	public Integer register(User user) {
		int registerStatus = 0;
		
		//password encoding
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		
		//checking if mail id is already exists or not
		User userRegister = userDao.getByEmail(user.getEmail());
		
		if (userRegister != null) {
			
			return registerStatus;
			
		}
		//if not existing then save
		return registerStatus = userDao.save(user);
	}

	@Override
	@Transactional
	public boolean login(User user) {
		boolean status=false;
		
		//checking if mail id exists or not
		User loginStatus = userDao.getByEmail(user.getEmail());
				
		//if exists then matching the password 
		if ((loginStatus != null) && (BCrypt.checkpw(user.getPassword(), loginStatus.getPassword()))) {
			
			return status = true;
		}
		
		return status;
	}

}
