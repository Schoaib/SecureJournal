package com.secure.journal.entries.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Instantiates a new entry request TO.
 */
@Data
public class EntryRequestTO {

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

}
