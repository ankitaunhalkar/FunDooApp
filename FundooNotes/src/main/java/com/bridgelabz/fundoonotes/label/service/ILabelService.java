package com.bridgelabz.fundoonotes.label.service;

import java.util.List;

import com.bridgelabz.fundoonotes.label.model.CreateLabelDto;
import com.bridgelabz.fundoonotes.label.model.ResponseLabelDto;
import com.bridgelabz.fundoonotes.label.model.UpdateLabelDto;
import com.bridgelabz.fundoonotes.note.model.ResponseNoteDto;
import com.bridgelabz.fundoonotes.note.model.UpdateNoteDto;

public interface ILabelService {

	ResponseLabelDto createlabel(CreateLabelDto label, String token);
		
	ResponseLabelDto updateLabel(UpdateLabelDto label, String token);
	
	List<ResponseLabelDto> getAllLabel(String token);

	long deleteLabel(String token, long id);

	ResponseNoteDto addlabelnote(String token, UpdateNoteDto note, long id);

	ResponseNoteDto removelabelnote(String token, UpdateNoteDto note, long id);
	
}
