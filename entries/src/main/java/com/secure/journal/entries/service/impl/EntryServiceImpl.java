package com.secure.journal.entries.service.impl;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secure.journal.entries.dto.EntryReponseTO;
import com.secure.journal.entries.dto.EntryRequestTO;
import com.secure.journal.entries.entity.Entry;
import com.secure.journal.entries.exception.EntryException;
import com.secure.journal.entries.repository.EntryRepository;
import com.secure.journal.entries.service.EntryService;

// TODO: Auto-generated Javadoc
/**
 * The Class EntryServiceImpl.
 */
@Service
public class EntryServiceImpl implements EntryService {

	/** The entry repository. */
	@Autowired
	private EntryRepository entryRepository;

	/**
	 * Save.
	 *
	 * @param entryRequest the entry request
	 * @return the entry reponse TO
	 * @throws EntryException the entry exception
	 */
	@Override
	public EntryReponseTO save(EntryRequestTO entryRequest) throws EntryException {
		Entry entry = new Entry();
		BeanUtils.copyProperties(entryRequest, entry);
		entry.setText(entryRequest.getText().getBytes());
		entry = entryRepository.save(entry);

		EntryReponseTO entryReponse = new EntryReponseTO();
		BeanUtils.copyProperties(entry, entryReponse);
		entryReponse.setText(new String(entry.getText(), StandardCharsets.UTF_8));
		// call stats api
		return entryReponse;

	}

}
