package com.bridgelabz.fundoonotes.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	SessionFactory sessionFcatory;

	@Override
	public User register(User user) {
		sessionFcatory.getCurrentSession().save(user);
		return user;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
