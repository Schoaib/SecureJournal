package com.secure.journal.common.security;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * The Class JwtUsernamePasswordAuthenticationFilter.
 */
public class JwtUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	/** The config. */
	private final JwtAuthenticationConfig config;
	
	/** The mapper. */
	private final ObjectMapper mapper;

	/**
	 * Instantiates a new jwt username password authentication filter.
	 *
	 * @param config the config
	 * @param authManager the auth manager
	 */
	public JwtUsernamePasswordAuthenticationFilter(JwtAuthenticationConfig config, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(config.getUrl(), "POST"));
		setAuthenticationManager(authManager);
		this.config = config;
		this.mapper = new ObjectMapper();
	}

	/**
	 * Attempt authentication.
	 *
	 * @param req the req
	 * @param rsp the rsp
	 * @return the authentication
	 * @throws AuthenticationException the authentication exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse rsp)
			throws AuthenticationException, IOException {
		User u = mapper.readValue(req.getInputStream(), User.class);
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword(), Collections.emptyList()));
	}

	/**
	 * Successful authentication.
	 *
	 * @param req the req
	 * @param rsp the rsp
	 * @param chain the chain
	 * @param auth the auth
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse rsp, FilterChain chain,
			Authentication auth) {
		Instant now = Instant.now();
		String token = Jwts.builder().setSubject(auth.getName())
				.claim("authorities",
						auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plusSeconds(config.getExpiration())))
				.signWith(SignatureAlgorithm.HS256, config.getSecret().getBytes()).compact();
		rsp.addHeader("access-control-expose-headers", "Authorization");
		rsp.addHeader(config.getHeader(), config.getPrefix() + " " + token);
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	@Getter
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	@Setter
	private static class User {
		
		/** The password. */
		private String username, password;
	}
}
