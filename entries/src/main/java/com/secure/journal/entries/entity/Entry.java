package com.secure.journal.entries.entity;

import lombok.Data;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
@Entity
public class Entry {

	/** The id. */
	private @Id @GeneratedValue Long id;

	/** The title. */
	private String title;

	/** The description. */
	private String description;

	/** The text. */
	@Lob
	private byte[] text;

	/** The creation date. */
	@CreationTimestamp
	private LocalDate creationDate;

	/** The updated at. */
	@UpdateTimestamp
	private LocalDate updatedAt;

	/**
	 * Instantiates a new entry.
	 *
	 * @param title       the title
	 * @param description the description
	 * @param text        the text
	 */
	public Entry(String title, String description, byte[] text) {
		this.title = title;
		this.description = description;
		this.text = text;

	}

	/**
	 * Instantiates a new customer.
	 */
	public Entry() {
	}
}
