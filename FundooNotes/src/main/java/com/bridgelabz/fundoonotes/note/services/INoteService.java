package com.bridgelabz.fundoonotes.note.services;

import java.util.List;

import com.bridgelabz.fundoonotes.note.model.CreateNoteDto;
import com.bridgelabz.fundoonotes.note.model.ResponseNoteDto;
import com.bridgelabz.fundoonotes.note.model.UpdateNoteDto;

public interface INoteService {

	ResponseNoteDto createNote(CreateNoteDto note, String token);

	List<ResponseNoteDto> getNotes(String token);

	boolean deleteNote(String token, long id);

	ResponseNoteDto updateNote(String token, UpdateNoteDto updateNote);
	
}
