package com.capstoneproject.educonnect.DTO;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class ApiKeyAuthenticationDTO extends AbstractAuthenticationToken {

	private static final long serialVersionUID = -1400687162842115021L;

	private final String apiKey;

	public ApiKeyAuthenticationDTO(String apiKey, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.apiKey = apiKey;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return apiKey;
	}
}
