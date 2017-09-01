package org.slerp.oauth.model;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.slerp.oauth.entity.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

public class UserCredentials implements UserDetails, CredentialsContainer, Cloneable {
	private static final long serialVersionUID = 1L;
	private UserPrincipal principal;
	private List<UserAuthority> authorities = new ArrayList<>();
	Logger log = LoggerFactory.getLogger(getClass());

	public UserCredentials(UserPrincipal userPrincipal) {
		this.principal = userPrincipal;
		List<org.slerp.oauth.entity.UserAuthority> autorityListDomain = userPrincipal.getUserAuthorityList();
		autorityListDomain.forEach(item -> {
			String authority = item.getAuthority();
			authorities.add(new UserAuthority(authority));
		});
	}

	@Override
	public void eraseCredentials() {
		this.principal.setHashedPassword(null);
	}

	@Override
	public List<UserAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return new String(principal.getHashedPassword(), StandardCharsets.UTF_8);
	}

	@Override
	public String getUsername() {
		return principal.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return principal.getAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return principal.getAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return principal.getAccountNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return principal.getEnabled();
	}

}
