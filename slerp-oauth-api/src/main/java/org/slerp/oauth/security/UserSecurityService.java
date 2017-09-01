package org.slerp.oauth.security;

import org.slerp.oauth.model.UserCredentials;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserSecurityService extends UserDetailsService {
	public UserCredentials loadUserByUsername(String username);
}
