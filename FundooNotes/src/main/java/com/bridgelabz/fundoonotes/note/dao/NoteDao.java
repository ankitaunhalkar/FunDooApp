package com.bridgelabz.fundoonotes.note.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.note.model.Note;
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

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Note> getAllNotes(User user) {

		List<Note> notelist = sessionFactory.getCurrentSession().createCriteria(Note.class)
				.add(Restrictions.eq("user", user)).list();

		return (notelist != null) ? notelist : null;
	}

	@SuppressWarnings("deprecation")
	public Note getById(long id) {

		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Note.class);

		crt.add(Restrictions.eq("id", id));

		System.out.println(id);
		
		Note note = (Note) crt.uniqueResult();

		return (note != null) ? note : null;
	}

	@Override
	public Note updateNote(Note note) {

		sessionFactory.getCurrentSession().update(note);

		return note;
	}

	public boolean deleteNote(Note note) {

		sessionFactory.getCurrentSession().delete(note);
		
		return true;

	}

}