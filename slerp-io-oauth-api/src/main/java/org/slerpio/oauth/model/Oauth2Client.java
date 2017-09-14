package org.slerpio.oauth.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slerpio.oauth.entity.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

public class Oauth2Client implements ClientDetails {

	private static final long serialVersionUID = 1L;
	private Client client;

	Collection<GrantedAuthority> authorities = new ArrayList<>();

	public Oauth2Client(Client client) {
		this.client = client;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return 3600;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		Map<String, Object> map = new HashMap<>();
		map.put("company", "Slerp Studio");
		return map;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return client.getAuthorizedGrantTypes();
	}

	@Override
	public String getClientId() {
		return client.getClientId();
	}

	@Override
	public String getClientSecret() {
		return client.getClientSecret();
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return 3600;
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return client.getRegisteredRedirectUri();
	}

	@Override
	public Set<String> getResourceIds() {
		Set<String> resourcesId = new HashSet<>();
		resourcesId.add("slerp");
		return resourcesId;
	}

	@Override
	public Set<String> getScope() {
		return client.getScope();
	}

	@Override
	public boolean isAutoApprove(String arg0) {
		return true;
	}

	@Override
	public boolean isScoped() {
		return true;
	}

	@Override
	public boolean isSecretRequired() {
		return true;
	}

}
