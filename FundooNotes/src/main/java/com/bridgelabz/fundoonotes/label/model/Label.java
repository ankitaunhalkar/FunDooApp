package com.bridgelabz.fundoonotes.label.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.bridgelabz.fundoonotes.note.model.Note;
import com.bridgelabz.fundoonotes.user.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="label")
public class Label {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String labelName;
		
	@ManyToOne(	cascade = CascadeType.ALL )
	@JsonProperty(access = Access.WRITE_ONLY)
	private User user;
	
	@ManyToMany(mappedBy = "notelabel")
	@JsonProperty(access = Access.WRITE_ONLY)
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private Set<Note> notes = new HashSet<Note>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
