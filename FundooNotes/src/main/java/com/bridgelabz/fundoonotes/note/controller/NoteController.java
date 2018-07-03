package com.bridgelabz.fundoonotes.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.note.model.Note;
import com.bridgelabz.fundoonotes.user.services.IUserService;

public class NoteController {
	@RestController
	public class UserController {

		// private static final Logger logger =
		// LoggerFactory.getLogger(UserController.class);

		@Autowired
		IUserService userService;
		
		@RequestMapping(name="/createnote", method = RequestMethod.POST)
		public ResponseEntity<String> createNote(@RequestBody Note note)
		{
			
			return new ResponseEntity<String>("Note Created",HttpStatus.CREATED);
		}
	}
}