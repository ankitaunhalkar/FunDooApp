package com.bridgelabz.fundoonotes.note.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.note.model.Note;
import com.bridgelabz.fundoonotes.note.model.Url;
import com.bridgelabz.fundoonotes.user.model.User;

@Repository
public class NoteDao implements INoteDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public long saveNote(Note note) {

		long id = (Long) sessionFactory.getCurrentSession().save(note);

		return id;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Note> getAllNotes(User user) {
		
		String querySelect = "from Note where user_id =: user";
		Query<?> query = sessionFactory.getCurrentSession().createQuery(querySelect);
		query.setParameter("user", user);
		List notelist =  query.list();
		
		return (notelist != null) ? notelist : null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Note getById(long id) {

		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Note.class);

		crt.add(Restrictions.eq("id", id));
		
		Note note = (Note) crt.uniqueResult();

		return (note != null) ? note : null;
	}

	@Override
	public Note updateNote(Note note) {

		sessionFactory.getCurrentSession().update(note);

		return note;
	}

	@Override
	public long deleteNote(long noteId) {
		
		String queryDelete = "delete from Note where id =: noteId";
		Query<?> query = sessionFactory.getCurrentSession().createQuery(queryDelete);
		query.setParameter("noteId", noteId);
		long result = query.executeUpdate();
		return result;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Url getByUrlId(long id) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Url.class);

		crt.add(Restrictions.eq("id", id));
		
		Url url = (Url) crt.uniqueResult();

		return (url != null) ? url : null;
	}

	@Override
	public long deleteUrl(long id) {

		String queryDelete = "delete from Url where id =: urlId";
		Query<?> query = sessionFactory.getCurrentSession().createQuery(queryDelete);
		query.setParameter("urlId", id);
		long result = query.executeUpdate();
		return result;
	}

}
