package com.secure.journal.entries.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.secure.journal.common.dto.TextAnalyze;
import com.secure.journal.entries.dto.EntryReponseTO;
import com.secure.journal.entries.dto.EntryRequestTO;
import com.secure.journal.entries.exception.EntryException;
import com.secure.journal.entries.service.EntryService;

/**
 * The Class EntryController.
 */
@RestController
@RequestMapping("/")
public class EntryController {

	/** The env. */
	@Autowired
	private Environment env;

	/** The entry service. */
	@Autowired
	private EntryService entryService;

	/** The rest template. */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Ping.
	 *
	 * @return the string
	 */
	@GetMapping("/ping")
	public String ping() {
		return "Hello 1 from Entry Service running at port: " + env.getProperty("local.server.port");
	}

	/**
	 * Creates the.
	 *
	 * @param entryRequest the entry request
	 * @return the entry reponse TO
	 * @throws EntryException the entry exception
	 */
	@PostMapping("/create")
	public EntryReponseTO create(@RequestBody @Valid EntryRequestTO entryRequest) throws EntryException {

		EntryReponseTO entryReponseTO = entryService.save(entryRequest);
		TextAnalyze analyzeReponse = restTemplate.postForObject("http://analyzer/analyze/", entryRequest.getText(),
				TextAnalyze.class);
		entryReponseTO.setCharCount(analyzeReponse.getCharCount());
		entryReponseTO.setWordCount(analyzeReponse.getWordCount());
		return entryReponseTO;
	}
}
