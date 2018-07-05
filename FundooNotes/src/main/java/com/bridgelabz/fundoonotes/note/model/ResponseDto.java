package com.bridgelabz.fundoonotes.note.model;

import java.util.Date;

public class ResponseDto {

	private long id;
	
	private String title;
	
	private String description;
	
	private boolean pin;
	
	private boolean trash;
	
	private boolean archive;
	
	private String color;
	
	private Date create_date;
	
	private Date modified_date;

	public ResponseDto() {

	}
	public ResponseDto(Note note) {
	
		this.id = note.getId();
		this.title = note.getTitle();
		this.description = note.getDescription();
		this.pin = note.isPin();
		this.trash = note.isTrash();
		this.archive = note.isArchive();
		this.color = note.getColor();
		this.create_date = note.getCreated_date();
		this.modified_date = note.getModified_date();
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
		
}