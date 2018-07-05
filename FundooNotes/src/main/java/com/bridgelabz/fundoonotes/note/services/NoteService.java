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
import com.bridgelabz.fundoonotes.note.model.ResponseDto;
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
	public ResponseDto createNote(CreateNoteDto createNote, String token) {

		long userId = TokenUtil.parseJWT(token);

		ResponseDto responseNote = null;

		// Creating a new note
		Note note = new Note(createNote);

		note.setCreated_date(new Date());

		note.setUser(userDao.getById(userId));

		// Saved the created note
		long noteId = noteDao.saveNote(note);

		// Fetching the created note
		Note createdNote = noteDao.getById(noteId);

		if (createdNote != null) {

			// Adding to responseDto
			responseNote = new ResponseDto(createdNote);

		}

		return responseNote;
	}

	@Transactional
	@Override
	public List<ResponseDto> getNotes(String token) {

		List<Note> noteDaoList = null;

		List<ResponseDto> noteList = new ArrayList<ResponseDto>();

		long userId = TokenUtil.parseJWT(token);

		User user = userDao.getById(userId);

		// Fetching all notes of particular user from db
		noteDaoList = noteDao.getAllNotes(user);

		for (Note note : noteDaoList) {

			ResponseDto notes = new ResponseDto(note);

			// adding into response dto list
			noteList.add(notes);

		}

		return noteList;
	}

	@Transactional
	@Override
	public ResponseDto updateNote(String token, CreateNoteDto updateNote, long noteId) {

		ResponseDto responseNote = null;

		long userId = TokenUtil.parseJWT(token);

		Note note = noteDao.getById(noteId);

		if (note.getUser().getId() == userId) {

			note.setTitle(updateNote.getTitle());
			note.setDescription(updateNote.getDescription());
			note.setModified_date(new Date());
			note.setColor(updateNote.getColor());
			note.setPin(updateNote.isPin());
			note.setTrash(updateNote.isTrash());
			note.setArchive(updateNote.isArchive());

			noteDao.updateNote(note);

			responseNote = new ResponseDto(note);
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
