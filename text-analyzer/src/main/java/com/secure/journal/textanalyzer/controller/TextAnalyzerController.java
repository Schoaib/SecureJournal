package com.secure.journal.textanalyzer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secure.journal.common.dto.TextAnalyze;

// TODO: Auto-generated Javadoc
/**
 * The Class TextAnalyzerController.
 */
@RestController
@RequestMapping("/")
public class TextAnalyzerController {

	/** The env. */
	@Autowired
	private Environment env;

	/**
	 * Ping.
	 *
	 * @return the string
	 */
	@GetMapping("/ping")
	public String ping() {
		return "Hello 1 from Text Analyzer Service running at port: " + env.getProperty("local.server.port");
	}

	/**
	 * Analyze.
	 *
	 * @param text the text
	 * @return the text analyze
	 */
	@PostMapping("/analyze")
	public TextAnalyze analyze(@RequestBody @Valid String text) {
		TextAnalyze analyzeReponse = new TextAnalyze();
		String words[] = text.split("\\s");
		analyzeReponse.setWordCount(words.length);
		analyzeReponse.setCharCount(text.length());
		return analyzeReponse;
	}
}
