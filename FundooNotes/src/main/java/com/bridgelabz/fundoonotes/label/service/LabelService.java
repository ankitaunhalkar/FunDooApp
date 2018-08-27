package com.bridgelabz.fundoonotes.label.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.label.dao.ILabelDao;
import com.bridgelabz.fundoonotes.label.model.CreateLabelDto;
import com.bridgelabz.fundoonotes.label.model.Label;
import com.bridgelabz.fundoonotes.label.model.ResponseLabelDto;
import com.bridgelabz.fundoonotes.label.model.UpdateLabelDto;
import com.bridgelabz.fundoonotes.note.dao.INoteDao;
import com.bridgelabz.fundoonotes.note.model.Note;
import com.bridgelabz.fundoonotes.note.model.ResponseNoteDto;
import com.bridgelabz.fundoonotes.note.model.UpdateNoteDto;
import com.bridgelabz.fundoonotes.user.dao.IUserDao;
import com.bridgelabz.fundoonotes.user.model.User;
import com.bridgelabz.fundoonotes.user.util.TokenUtil;

@Service
public class LabelService implements ILabelService {

	@Autowired
	IUserDao userDao;

	@Autowired
	ILabelDao labelDao;

	@Autowired
	INoteDao noteDao;

	@Override
	@Transactional
	public ResponseLabelDto createlabel(CreateLabelDto label, String token) {

		ResponseLabelDto resonselabel = null;

		long userId = TokenUtil.parseJWT(token);

		// Creating a new label
		Label newlabel = new Label();

		newlabel.setLabelName(label.getLabelname());

		newlabel.setUser(userDao.getById(userId));

		// Saved the created label
		long labelId = labelDao.savelabel(newlabel);

		// Fetching the created label
		Label labelcreated = labelDao.getById(labelId);

		if (labelcreated != null) {

			// Adding to responseDto
			resonselabel = new ResponseLabelDto(labelcreated);

		}

		return resonselabel;
	}

	@Override
	@Transactional
	public ResponseLabelDto updateLabel(UpdateLabelDto label, String token) {
		ResponseLabelDto responseNote = null;

		long userId = TokenUtil.parseJWT(token);

		Label updateLabel = labelDao.getById(label.getId());

		if (updateLabel.getUser().getId() == userId) {

			updateLabel.setLabelName(label.getLabelname());

			Label updatedLabel = labelDao.updateNote(updateLabel);

			responseNote = new ResponseLabelDto(updatedLabel);
		}

		return responseNote;

	}

	@Override
	@Transactional
	public long deleteLabel(String token, long labelId) {

		long status = 0;

		long userId = TokenUtil.parseJWT(token);

		Label label = labelDao.getById(labelId);

		if (label.getUser().getId() == userId) {
			status = labelDao.deleteNote(label.getId());
		}
		return status;

	}

	@Override
	@Transactional
	public List<ResponseLabelDto> getAllLabel(String token) {
		List<Label> labelDaoList = null;

		List<ResponseLabelDto> labelList = new ArrayList<ResponseLabelDto>();

		long userId = TokenUtil.parseJWT(token);

		User user = userDao.getById(userId);

		// Fetching all notes of particular user from db
		labelDaoList = labelDao.getLabels(user);

		for (Label label : labelDaoList) {

			ResponseLabelDto labels = new ResponseLabelDto(label);

			// adding into response dto list
			labelList.add(labels);

		}

		return labelList;
	}

	@Override
	@Transactional
	public ResponseNoteDto addlabelnote(String token, UpdateNoteDto note, long labelId) {

		ResponseNoteDto responseNote = null;

		long userId = TokenUtil.parseJWT(token);

		Note updatingnote = noteDao.getById(note.getId());

		if (updatingnote.getUser().getId() == userId) {
			Label label = labelDao.getById(labelId);

			Set<Note> labelnote = new HashSet<Note>();
			labelnote = label.getNotes();
			labelnote.add(updatingnote);
			label.setNotes(labelnote);
			labelDao.updateNote(label);

			Set<Label> labels = new HashSet<Label>();
			labels = updatingnote.getLabels();
			labels.add(label);
			updatingnote.setLabels(labels);

			Note updatednote = noteDao.updateNote(updatingnote);
			responseNote = new ResponseNoteDto(updatednote);
		}
		return responseNote;
	}

	@Override
	@Transactional
	public ResponseNoteDto removelabelnote(String token, UpdateNoteDto note, long labelId) {
		ResponseNoteDto responseNote = null;

		long userId = TokenUtil.parseJWT(token);

		Note updatingnote = noteDao.getById(note.getId());

		if (updatingnote.getUser().getId() == userId) {
			Label label = labelDao.getById(labelId);

			if(updatingnote.getLabels().contains(label))
			{
				updatingnote.getLabels().remove(label);
				Note updatednote = noteDao.updateNote(updatingnote);
				responseNote = new ResponseNoteDto(updatednote);
			}
		}
		return responseNote;
	}
}
