package com.bridgelabz.fundoonotes.note.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.note.model.CreateNoteDto;
import com.bridgelabz.fundoonotes.note.model.ResponseDto;
import com.bridgelabz.fundoonotes.note.services.INoteService;

@RestController
public class NoteController {

	@Autowired
	INoteService noteService;

	@RequestMapping(value = "/createnote", method = RequestMethod.POST)
	public ResponseEntity<ResponseDto> createNote(@RequestBody CreateNoteDto note, HttpServletRequest request) {

		String token = request.getHeader("uid");

		ResponseDto noteCreated = noteService.createNote(note, token);

		if (noteCreated != null) {

			return new ResponseEntity<ResponseDto>(noteCreated, HttpStatus.CREATED);

		}

		return new ResponseEntity<ResponseDto>(HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/updatenote/{id}", method = RequestMethod.POST)
	public ResponseEntity<ResponseDto> updateNote(@PathVariable long id, @RequestBody CreateNoteDto note,
			HttpServletRequest request) {

		String token = request.getHeader("uid");

		ResponseDto noteUpdated = noteService.updateNote(token, note, id);

		if (noteUpdated != null) {

			return new ResponseEntity<ResponseDto>(noteUpdated, HttpStatus.CREATED);

		}

		return new ResponseEntity<ResponseDto>(HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/getnotes", method = RequestMethod.GET)
	public ResponseEntity<List<ResponseDto>> getNotes(HttpServletRequest request) {

		String token = request.getHeader("uid");

		List<ResponseDto> notelist = noteService.getNotes(token);

		if (notelist != null) {

			return new ResponseEntity<List<ResponseDto>>(notelist, HttpStatus.OK);

		}

		return new ResponseEntity<List<ResponseDto>>(HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/deletenote/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteNote(@PathVariable long id, HttpServletRequest request) {

		String token = request.getHeader("uid");

		boolean noteDeleted = noteService.deleteNote(token, id);

		if (noteDeleted) {

			return new ResponseEntity<String>("Note Deleted Succesfully", HttpStatus.OK);

		}

		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);

	}
}