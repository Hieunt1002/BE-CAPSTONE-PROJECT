package com.capstoneproject.educonnect.DTO;

public class ChangepassDTO {
	private String username;
	private String password;
	private String newpass;

	public ChangepassDTO() {

	}

	public ChangepassDTO(String username, String password, String newpass) {
		this.username = username;
		this.password = password;
		this.newpass = newpass;
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
