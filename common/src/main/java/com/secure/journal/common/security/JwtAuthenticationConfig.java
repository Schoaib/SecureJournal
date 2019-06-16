package com.secure.journal.common.security;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * Gets the secret.
 *
 * @return the secret
 */
@Getter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString
public class JwtAuthenticationConfig {

	/** The url. */
	@Value("${secure-journal.security.jwt.url}")
	private String url;

	/** The header. */
	@Value("${secure-journal.security.jwt.header:Authorization}")
	private String header;

	/** The prefix. */
	@Value("${secure-journal.security.jwt.prefix:Bearer}")
	private String prefix;

	/** The expiration. */
	@Value("${secure-journal.security.jwt.expiration:#{24*60*60}}")
	private int expiration; // default 24 hours

	/** The secret. */
	@Value("${secure-journal.security.jwt.secret}")
	private String secret;
}
