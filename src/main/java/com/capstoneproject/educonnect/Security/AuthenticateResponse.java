package com.capstoneproject.educonnect.Security;

public class AuthenticateResponse {

	private final String jwttoken;
	
	private String message;

	public AuthenticateResponse(String jwttoken, String successMessage) {
		this.jwttoken = jwttoken;
		this.message = successMessage;
	}

	public String getToken() {
		return this.jwttoken;
	}
	public String getMessage() {
		return this.message;
	}
}
