package com.bridgelabz.fundoonotes.note.services;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.note.model.CreateNoteDto;
import com.bridgelabz.fundoonotes.note.model.ResponseNoteDto;
import com.bridgelabz.fundoonotes.note.model.UpdateNoteDto;

public interface INoteService {

	ResponseNoteDto createNote(CreateNoteDto note, String token);

	List<ResponseNoteDto> getNotes(String token);

	long deleteNote(String token, long id);

	ResponseNoteDto updateNote(String token, UpdateNoteDto updateNote);

	String uploadImage(MultipartFile file);

	ByteArrayResource loadImage(String file);
	
}
