package com.bridgelabz.fundoonotes.note.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bridgelabz.fundoonotes.user.model.User;

@Entity
@Table(name = "note")
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private Date created_date;
	
	@Column
	private Date modified_date;
	
	@Column
	private String color;
	
	@Column
	private boolean archive;

	@Column 
	private boolean trash;
	
	@Column
	private boolean pin;
	
	@ManyToOne(	cascade = CascadeType.ALL )
	private User user;
	
	public Note () {} 
	
	public Note(CreateNoteDto createNote) {
		
		this.title = createNote.getTitle();
		this.description = createNote.getDescription();
		this.modified_date = created_date;
		this.color = createNote.getColor();
		this.pin = createNote.isPin();
		this.trash = createNote.isTrash();
		this.archive = createNote.isArchive();

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public boolean isPin() {
		return pin;
	}

	public void setPin(boolean pin) {
		this.pin = pin;
	}
	
}