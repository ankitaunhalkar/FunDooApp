package com.bridgelabz.fundoonotes.note.services;

import java.util.List;
import java.util.Set;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.note.model.CollabratoredUser;
import com.bridgelabz.fundoonotes.note.model.CreateNoteDto;
import com.bridgelabz.fundoonotes.note.model.ResponseNoteDto;
import com.bridgelabz.fundoonotes.note.model.UpdateNoteDto;
import com.bridgelabz.fundoonotes.note.model.Url;

public interface INoteService {

	ResponseNoteDto createNote(CreateNoteDto note, String token);

	List<ResponseNoteDto> getNotes(String token);

	long deleteNote(String token, long id);

	ResponseNoteDto updateNote(String token, UpdateNoteDto updateNote);

	String uploadImage(MultipartFile file);

	ByteArrayResource loadImage(String file);
	
	Set<Url> urlinfo(String description);

	ResponseNoteDto removeurlinfo(String token, UpdateNoteDto note, long id);

	CollabratoredUser addCollaborator(long id, CollabratoredUser user, String token);
	
}
