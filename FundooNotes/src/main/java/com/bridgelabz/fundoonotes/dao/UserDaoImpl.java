package com.bridgelabz.fundoonotes.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.User;

@Repository
public class UserDaoImpl implements IUserDao{

	@Autowired
	SessionFactory sessionFcatory;

	@Override
	public Integer save(User user) {
		int status = (Integer) sessionFcatory.openSession().save(user);
		return status;
	}

	@Override
	public User login(User user) {
		return null;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public User getByEmail(String email) {
		Criteria crt = sessionFcatory.getCurrentSession().createCriteria(User.class);
		crt.add(Restrictions.eq("email",email));	
		List<User> user =  crt.list();
		return user.size() > 0 ? user.get(0):null;
	}	
}
