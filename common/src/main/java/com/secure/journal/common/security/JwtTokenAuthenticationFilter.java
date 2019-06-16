package com.secure.journal.common.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * The Class JwtTokenAuthenticationFilter.
 */
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

	/** The config. */
	private final JwtAuthenticationConfig config;

	/**
	 * Instantiates a new jwt token authentication filter.
	 *
	 * @param config the config
	 */
	public JwtTokenAuthenticationFilter(JwtAuthenticationConfig config) {
		this.config = config;
	}

	/**
	 * Do filter internal.
	 *
	 * @param req the req
	 * @param rsp the rsp
	 * @param filterChain the filter chain
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp, FilterChain filterChain)
			throws ServletException, IOException {
		String token = req.getHeader(config.getHeader());
		if (token != null && token.startsWith(config.getPrefix() + " ")) {
			token = token.replace(config.getPrefix() + " ", "");
			try {
				Claims claims = Jwts.parser().setSigningKey(config.getSecret().getBytes()).parseClaimsJws(token)
						.getBody();
				String username = claims.getSubject();
				@SuppressWarnings("unchecked")
				List<String> authorities = claims.get("authorities", List.class);
				if (username != null) {
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
							authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			} catch (Exception ignore) {
				SecurityContextHolder.clearContext();
			}
		}
		filterChain.doFilter(req, rsp);
	}
}
