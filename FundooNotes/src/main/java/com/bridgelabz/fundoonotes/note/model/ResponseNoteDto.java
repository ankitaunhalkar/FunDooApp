package com.bridgelabz.fundoonotes.note.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.bridgelabz.fundoonotes.label.model.Label;
import com.bridgelabz.fundoonotes.user.model.User;

public class ResponseNoteDto {

	private long id;
	
	private String title;
	
	private String description;
	
	private boolean pin;
	
	private boolean trash;
	
	private boolean archive;
	
	private String color;
	
	private Date create_date;
	
	private Date modified_date;

	private Date reminder;
	
	private String image;
	
	private Set<Label> notelabel;

	private Set<Url> urlInfo;
	
	private Set<User> collabrators = new HashSet<>();
	
	public ResponseNoteDto() {

	}
	public ResponseNoteDto(Note note) {
	
		this.id = note.getId();
		this.title = note.getTitle();
		this.description = note.getDescription();
		this.pin = note.isPin();
		this.trash = note.isTrash();
		this.archive = note.isArchive();
		this.color = note.getColor();
		this.create_date = note.getCreated_date();
		this.modified_date = note.getModified_date();
		this.reminder = note.getReminder();
		this.image = note.getImage();
		this.notelabel = note.getLabels();
		this.urlInfo = note.getUrls();
		this.collabrators = note.getCollaborators();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
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

	public boolean isPin() {
		return pin;
	}

	public void setPin(boolean pin) {
		this.pin = pin;
	}

	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
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
	
	public Set<Url> getUrlInfo() {
		return urlInfo;
	}
	
	public void setUrlInfo(Set<Url> urlInfo) {
		this.urlInfo = urlInfo;
	}
	
	public Set<User> getCollabrators() {
		return collabrators;
	}
	
	public void setCollabrators(Set<User> collabrators) {
		this.collabrators = collabrators;
	}
	
}
