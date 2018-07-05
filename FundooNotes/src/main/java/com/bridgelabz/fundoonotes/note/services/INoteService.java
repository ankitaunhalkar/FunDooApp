package com.bridgelabz.fundoonotes.note.services;

import java.util.List;

import com.bridgelabz.fundoonotes.note.model.CreateNoteDto;
import com.bridgelabz.fundoonotes.note.model.ResponseDto;

public interface INoteService {

	ResponseDto createNote(CreateNoteDto note, String token);

	List<ResponseDto> getNotes(String token);

	ResponseDto updateNote(String token, CreateNoteDto note, long noteId);

	boolean deleteNote(String token, long id);
	
}
