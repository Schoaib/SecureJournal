package com.secure.journal.entries.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Instantiates a new entry reponse TO.
 */
@Data
public class EntryReponseTO {

	/** The id. */
	@NotNull
	private Long id;

	/** The title. */
	@NotNull
	@Size(max = 50)
	private String title;

	/** The description. */
	@NotNull
	@Size(max = 250)
	private String description;

	/** The text. */
	@NotNull
	private String text;

	/** The word count. */
	@NotNull
	private int wordCount;

	/** The char count. */
	@NotNull
	private int charCount;

}
