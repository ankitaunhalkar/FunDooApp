package com.bridgelabz.fundoonotes.label.controller;

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

import com.bridgelabz.fundoonotes.label.model.CreateLabelDto;
import com.bridgelabz.fundoonotes.label.model.ResponseLabelDto;
import com.bridgelabz.fundoonotes.label.model.UpdateLabelDto;
import com.bridgelabz.fundoonotes.label.service.ILabelService;
import com.bridgelabz.fundoonotes.note.model.ResponseNoteDto;
import com.bridgelabz.fundoonotes.note.model.UpdateNoteDto;
import com.bridgelabz.fundoonotes.user.model.StatusDto;

@RestController
public class LabelController {

	@Autowired
	ILabelService labelService;
	
	@RequestMapping(value = "/createlabel", method = RequestMethod.POST)
	public ResponseEntity<?> createLabel(@RequestBody CreateLabelDto label, HttpServletRequest request) {

		String token = request.getHeader("Authorization");

		ResponseLabelDto labelCreated = labelService.createlabel(label,token);

		if (labelCreated != null) {

			return new ResponseEntity<ResponseLabelDto>(labelCreated, HttpStatus.CREATED);

		}

		return new ResponseEntity<ResponseLabelDto>(HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value = "/updatelabel", method = RequestMethod.PUT)
	public ResponseEntity<?> updateLabel(@RequestBody UpdateLabelDto label, HttpServletRequest request)
	{
		String token = request.getHeader("Authorization");

		ResponseLabelDto noteUpdated = labelService.updateLabel(label, token);

		if (noteUpdated != null) {

			return new ResponseEntity<ResponseLabelDto>(noteUpdated, HttpStatus.CREATED);

		}

		return new ResponseEntity<ResponseNoteDto>(HttpStatus.BAD_REQUEST);

	}
	
	@RequestMapping(value = "/deletelabel/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<StatusDto> deleteLabel(@PathVariable long id, HttpServletRequest request) {

		String token = request.getHeader("Authorization");

		long noteDeleted = labelService.deleteLabel(token, id);

		if (noteDeleted > 0) {

			StatusDto res = new StatusDto();
			res.setCode(200);
			res.setMessage("Label Deleted Succesfully");
			return new ResponseEntity<StatusDto>(res, HttpStatus.OK);

		}

		return new ResponseEntity<StatusDto>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/getlabels", method = RequestMethod.GET)
	public ResponseEntity<List<ResponseLabelDto>> getNotes(HttpServletRequest request) {

		String token = request.getHeader("Authorization");

		List<ResponseLabelDto> labelList = labelService.getAllLabel(token);

		if (labelList != null) {

			return new ResponseEntity<List<ResponseLabelDto>>(labelList, HttpStatus.OK);

		}

		return new ResponseEntity<List<ResponseLabelDto>>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/addlabel/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseNoteDto> addLabel(@RequestBody UpdateNoteDto note, @PathVariable long id,HttpServletRequest request){

		String token = request.getHeader("Authorization");

		ResponseNoteDto labelednote = labelService.addlabelnote(token, note, id);
		if (labelednote != null) {

			return new ResponseEntity<ResponseNoteDto>(labelednote, HttpStatus.OK);

		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		
	}
	
	@RequestMapping(value = "/removelabel/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseNoteDto> removeLabel(@RequestBody UpdateNoteDto note, @PathVariable long id,HttpServletRequest request){
		String token = request.getHeader("Authorization");

		ResponseNoteDto labelednote = labelService.removelabelnote(token, note, id);
		if (labelednote != null) {

			return new ResponseEntity<ResponseNoteDto>(labelednote, HttpStatus.OK);

		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
}
