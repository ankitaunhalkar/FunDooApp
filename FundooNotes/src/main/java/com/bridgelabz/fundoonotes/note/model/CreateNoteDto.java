package com.bridgelabz.fundoonotes.note.model;

import java.util.Date;
import java.util.Set;

import com.bridgelabz.fundoonotes.label.model.Label;

public class CreateNoteDto {
		
	private String title;
	
	private String description;
		
	private String color;
	
	private boolean archive;
	
	private boolean pin;
	
	private Date reminder;

	private String image;
	
	private Set<Label> notelabel;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public boolean isPin() {
		return pin;
	}

	public void setPin(boolean pin) {
		this.pin = pin;
	}

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<Label> getNotelabel() {
		return notelabel;
	}

	public void setNotelabel(Set<Label> notelabel) {
		this.notelabel = notelabel;
	}
	
}
