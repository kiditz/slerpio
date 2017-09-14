package org.slerpio.oauth.security;

import org.slerpio.oauth.model.UserCredentials;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserSecurityService extends UserDetailsService {
	public UserCredentials loadUserByUsername(String username);
}
