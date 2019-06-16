package com.secure.journal.auth.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The Class UserDetailsServiceImpl.
 */
@Service // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService {

	/** The encoder. */
	@Autowired
	private BCryptPasswordEncoder encoder;

	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final List<AppUser> users = Arrays.asList(new AppUser(1, "shoaib", encoder.encode("shoaib"), "ADMIN"));

		for (AppUser appUser : users) {
			if (appUser.getUsername().equals(username)) {

				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
						.commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());

				return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
			}
		}

		// If user not found. Throw this exception.
		throw new UsernameNotFoundException("Username: " + username + " not found");
	}

	/**
	 * The Class AppUser.
	 */
	// A (temporary) class represent the user saved in the database.
	private static class AppUser {
		
		/** The id. */
		private Integer id;
		
		/** The password. */
		private String username, password;
		
		/** The role. */
		private String role;

		/**
		 * Instantiates a new app user.
		 *
		 * @param id the id
		 * @param username the username
		 * @param password the password
		 * @param role the role
		 */
		public AppUser(Integer id, String username, String password, String role) {
			this.id = id;
			this.username = username;
			this.password = password;
			this.role = role;
		}

		/**
		 * Gets the id.
		 *
		 * @return the id
		 */
		public Integer getId() {
			return id;
		}

		/**
		 * Sets the id.
		 *
		 * @param id the new id
		 */
		public void setId(Integer id) {
			this.id = id;
		}

		/**
		 * Gets the username.
		 *
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * Sets the username.
		 *
		 * @param username the new username
		 */
		public void setUsername(String username) {
			this.username = username;
		}

		/**
		 * Gets the password.
		 *
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * Sets the password.
		 *
		 * @param password the new password
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		/**
		 * Gets the role.
		 *
		 * @return the role
		 */
		public String getRole() {
			return role;
		}

		/**
		 * Sets the role.
		 *
		 * @param role the new role
		 */
		public void setRole(String role) {
			this.role = role;
		}
	}
}