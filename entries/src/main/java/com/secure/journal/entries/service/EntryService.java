package com.secure.journal.entries.service;

import com.secure.journal.entries.dto.EntryReponseTO;
import com.secure.journal.entries.dto.EntryRequestTO;
import com.secure.journal.entries.exception.EntryException;

/**
 * The Interface EntryService.
 */
public interface EntryService {

	/**
	 * Save.
	 *
	 * @param entryRequest the entry request
	 * @return the entry reponse TO
	 * @throws EntryException the entry exception
	 */
	EntryReponseTO save(EntryRequestTO entryRequest) throws EntryException;
}
