package com.bridgelabz.fundoonotes.user.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.user.model.User;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	SessionFactory sessionFcatory;

	@Override
	public int save(User user) {
		int status = (Integer) sessionFcatory.openSession().save(user);
		return status;
	}
	
	@SuppressWarnings({ "deprecation" })
	@Override
	public User getByEmail(String email) {
		Criteria crt = sessionFcatory.getCurrentSession().createCriteria(User.class);
		crt.add(Restrictions.eq("email", email));
		User user = (User) crt.uniqueResult();
		return (user != null) ? user : null;
	}
}