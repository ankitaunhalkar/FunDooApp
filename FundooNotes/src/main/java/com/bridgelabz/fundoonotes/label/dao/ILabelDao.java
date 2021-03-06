package com.bridgelabz.fundoonotes.label.dao;

import java.util.List;

import com.bridgelabz.fundoonotes.label.model.Label;
import com.bridgelabz.fundoonotes.user.model.User;

public interface ILabelDao {

	long savelabel(Label newlabel);

	Label getById(long labelId);

	long deleteNote(long id);

	List<Label> getLabels(User user);

	Label updateNote(Label label);

}
