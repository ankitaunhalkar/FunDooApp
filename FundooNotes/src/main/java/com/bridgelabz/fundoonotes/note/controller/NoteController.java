package com.bridgelabz.fundoonotes.note.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.note.model.CreateNoteDto;
import com.bridgelabz.fundoonotes.note.model.ResponseNoteDto;
import com.bridgelabz.fundoonotes.note.model.UpdateNoteDto;
import com.bridgelabz.fundoonotes.note.services.INoteService;
import com.bridgelabz.fundoonotes.user.model.StatusDto;

@RestController
public class NoteController {

	@Autowired
	INoteService noteService;

	@RequestMapping(value = "/createnote", method = RequestMethod.POST)
	public ResponseEntity<ResponseNoteDto> createNote(@RequestBody CreateNoteDto note, HttpServletRequest request) {

		String token = request.getHeader("Authorization");

		ResponseNoteDto noteCreated = noteService.createNote(note, token);

		if (noteCreated != null) {

			return new ResponseEntity<ResponseNoteDto>(noteCreated, HttpStatus.CREATED);

		}

		return new ResponseEntity<ResponseNoteDto>(HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/updatenote", method = RequestMethod.PUT)
	public ResponseEntity<ResponseNoteDto> updateNote(@RequestBody UpdateNoteDto note, HttpServletRequest request) {

		String token = request.getHeader("Authorization");

		ResponseNoteDto noteUpdated = noteService.updateNote(token, note);

		if (noteUpdated != null) {

			return new ResponseEntity<ResponseNoteDto>(noteUpdated, HttpStatus.CREATED);

		}

		return new ResponseEntity<ResponseNoteDto>(HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/getnotes", method = RequestMethod.GET)
	public ResponseEntity<List<ResponseNoteDto>> getNotes(HttpServletRequest request) {

		String token = request.getHeader("Authorization");

		List<ResponseNoteDto> notelist = noteService.getNotes(token);

		if (notelist != null) {

			return new ResponseEntity<List<ResponseNoteDto>>(notelist, HttpStatus.OK);

		}

		return new ResponseEntity<List<ResponseNoteDto>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/deletenote/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteNote(@PathVariable long id, HttpServletRequest request) {

		String token = request.getHeader("Authorization");

		long noteDeleted = noteService.deleteNote(token, id);

		if (noteDeleted > 0) {

			StatusDto res = new StatusDto();
			res.setCode(200);
			res.setMessage("Note Deleted Succesfully");
			return new ResponseEntity<>(res, HttpStatus.OK);

		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/uploadimage", method = RequestMethod.POST)
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {

		String status = noteService.uploadImage(file);
		// System.out.println(status);
		StatusDto res = new StatusDto();
		res.setCode(200);
		res.setMessage(status);
		return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/getimage/{filename:.+}", method = RequestMethod.GET)
	public ResponseEntity<?> downloadImage(@PathVariable String filename, HttpServletRequest request,
			HttpServletResponse response) {
		
		ByteArrayResource resource = noteService.loadImage(filename);
	
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@RequestMapping(value="/removeurlinfo/{id}", method= RequestMethod.PUT)
	public ResponseEntity<?> removeUrl(@RequestBody UpdateNoteDto note, @PathVariable long id,HttpServletRequest request){
		String token = request.getHeader("Authorization");

		ResponseNoteDto labelednote = noteService.removeurlinfo(token, note, id);
		if (labelednote != null) {

			return new ResponseEntity<ResponseNoteDto>(labelednote, HttpStatus.OK);

		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
}