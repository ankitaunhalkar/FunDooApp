package com.bridgelabz.fundoonotes.label.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.label.model.Label;
import com.bridgelabz.fundoonotes.user.model.User;

@Repository
public class LabelDao implements ILabelDao{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public long savelabel(Label newlabel) {
		long id = (Long) sessionFactory.getCurrentSession().save(newlabel);

		return id;	
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Label getById(long labelId) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Label.class);

		crt.add(Restrictions.eq("id", labelId));
		
		Label label = (Label) crt.uniqueResult();

		return (label != null) ? label : null;
	}

	@Override
	public long deleteNote(long labelId) {
		
		String queryDelete = "delete from Label where id =: labelId";
		Query<?> query = sessionFactory.getCurrentSession().createQuery(queryDelete);
		query.setParameter("labelId", labelId);
		long result = query.executeUpdate();
		return result;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Label> getLabels(User user) {
		List<Label> labelList = sessionFactory.getCurrentSession().createCriteria(Label.class)
				.add(Restrictions.eq("user", user)).list();

		return (labelList != null) ? labelList : null;
	}
	
	@Override
	public Label updateNote(Label label) {

		sessionFactory.getCurrentSession().update(label);

		return label;
	}
}
