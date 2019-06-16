package com.secure.journal.entries.exception;

/**
 * The Class EntryException.
 */
public class EntryException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 272275072123723695L;


	/**
	 * Instantiates a new entry exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public EntryException(String message, Throwable e) {
		super(message, e);
	}


	/**
	 * Instantiates a new entry exception.
	 *
	 * @param message the message
	 */
	public EntryException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new entry exception.
	 *
	 * @param e the e
	 */
	public EntryException(Throwable e) {
		super(e);
	}
}
