package com.bridgelabz.fundoonotes.note.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.note.dao.INoteDao;
import com.bridgelabz.fundoonotes.note.model.CreateNoteDto;
import com.bridgelabz.fundoonotes.note.model.Note;
import com.bridgelabz.fundoonotes.note.model.ResponseNoteDto;
import com.bridgelabz.fundoonotes.note.model.UpdateNoteDto;
import com.bridgelabz.fundoonotes.user.dao.IUserDao;
import com.bridgelabz.fundoonotes.user.model.User;
import com.bridgelabz.fundoonotes.user.util.TokenUtil;

@Service
public class NoteService implements INoteService {

	@Autowired
	IUserDao userDao;

	@Autowired
	INoteDao noteDao;

	@Transactional
	@Override
	public ResponseNoteDto createNote(CreateNoteDto createNote, String token) {

		long userId = TokenUtil.parseJWT(token);

		ResponseNoteDto responseNote = null;

		// Creating a new note
		Note note = new Note(createNote);

		note.setCreated_date(new Date());

		note.setModified_date(note.getCreated_date());

		note.setUser(userDao.getById(userId));

		// Saved the created note
		long noteId = noteDao.saveNote(note);

		// Fetching the created note
		Note createdNote = noteDao.getById(noteId);

		if (createdNote != null) {

			// Adding to responseDto
			responseNote = new ResponseNoteDto(createdNote);

		}

		return responseNote;
	}

	@Transactional
	@Override
	public List<ResponseNoteDto> getNotes(String token) {

		List<Note> noteDaoList = null;

		List<ResponseNoteDto> noteList = new ArrayList<ResponseNoteDto>();

		long userId = TokenUtil.parseJWT(token);

		User user = userDao.getById(userId);

		// Fetching all notes of particular user from db
		noteDaoList = noteDao.getAllNotes(user);

		for (Note note : noteDaoList) {

			ResponseNoteDto notes = new ResponseNoteDto(note);

			// adding into response dto list
			noteList.add(notes);

		}

		return noteList;
	}

	@Transactional
	@Override
	public ResponseNoteDto updateNote(String token, UpdateNoteDto updateNote) {

		ResponseNoteDto responseNote = null;

		long userId = TokenUtil.parseJWT(token);

		Note note = noteDao.getById(updateNote.getId());

		if (note.getUser().getId() == userId) {

			note.setTitle(updateNote.getTitle());
			note.setDescription(updateNote.getDescription());
			note.setModified_date(new Date());
			note.setColor(updateNote.getColor());
			note.setPin(updateNote.isPin());
			note.setTrash(updateNote.isTrash());
			note.setArchive(updateNote.isArchive());

			noteDao.updateNote(note);

			responseNote = new ResponseNoteDto(note);
		}

		return responseNote;
	}

	@Transactional
	@Override
	public boolean deleteNote(String token, long noteId) {

		boolean status = false;

		long userId = TokenUtil.parseJWT(token);

		Note note = noteDao.getById(noteId);

		if (note.getUser().getId() == userId) {
			status = noteDao.deleteNote(note);
		}
		return status;

	}

}
