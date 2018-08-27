package com.bridgelabz.fundoonotes.label.model;

import java.util.HashSet;
import java.util.Set;

import com.bridgelabz.fundoonotes.note.model.Note;

public class ResponseLabelDto {
	
	private long id;

	private String labelName;
	
	private Set<Note> notes = new HashSet<Note>();
	public ResponseLabelDto(Label labelcreated) {
		
		this.id = labelcreated.getId();
		this.labelName = labelcreated.getLabelName();
		this.notes = labelcreated.getNotes();
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabelname() {
		return labelName;
	}

	public void setLabelname(String labelname) {
		this.labelName = labelname;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}
	
}
