package com.secure.journal.common.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Instantiates a new text analyze.
 */
@Data
public class TextAnalyze {

	/** The word count. */
	@NotNull
	private int wordCount;

	/** The char count. */
	@NotNull
	private int charCount;

}
