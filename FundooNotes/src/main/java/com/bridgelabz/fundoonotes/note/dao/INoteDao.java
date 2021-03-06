package com.bridgelabz.fundoonotes.note.dao;

import java.util.List;

import com.bridgelabz.fundoonotes.note.model.Note;
import com.bridgelabz.fundoonotes.note.model.Url;
import com.bridgelabz.fundoonotes.user.model.User;

public interface INoteDao {
	
	long saveNote(Note note);

	List<Note> getAllNotes(User userId);

	Note updateNote(Note note);

	Note getById(long id);
	
	long deleteNote(long note);

	Url getByUrlId(long id);

	long deleteUrl(long id);
}
