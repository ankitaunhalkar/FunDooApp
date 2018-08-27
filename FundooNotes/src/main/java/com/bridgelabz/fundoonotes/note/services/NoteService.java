package com.bridgelabz.fundoonotes.note.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
			note.setReminder(updateNote.getReminder());
			note.setImage(updateNote.getImage());
			note.setLabels(updateNote.getNotelabel());
			Note updatednote = noteDao.updateNote(note);

			responseNote = new ResponseNoteDto(updatednote);
		}

		return responseNote;
	}

	@Transactional
	@Override
	public long deleteNote(String token, long noteId) {

		long status = 0;

		long userId = TokenUtil.parseJWT(token);

		Note note = noteDao.getById(noteId);

		if (note.getUser().getId() == userId) {
			status = noteDao.deleteNote(note.getId());
		}
		return status;

	}

	@Override
	public String uploadImage(MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("/home/bridgeit/eclipse-workspace/fundooApp/FundooNotes/src/main/java/com/bridgelabz/fundoonotes/images/"
								+ file.getOriginalFilename())));
				stream.write(bytes);
				stream.close();
				
				return "http://localhost:8080/fundoonotes/getimage/"+file.getOriginalFilename();
			} catch (Exception e) {
				return "You failed to upload " + e.getMessage();
			}
		} else {
			return "You failed to upload because the file was empty.";
		}
	}

	@Override
	public ByteArrayResource loadImage(String filename) {
		String downloadFolder = "/home/bridgeit/eclipse-workspace/fundooApp/FundooNotes/src/main/java/com/bridgelabz/fundoonotes/images/";
		Path file = Paths.get(downloadFolder, filename);
		
		byte[] data = null;
		ByteArrayResource resource = null;
		try {
			data = Files.readAllBytes(file);
			resource = new ByteArrayResource(data);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return resource;
	}

}
