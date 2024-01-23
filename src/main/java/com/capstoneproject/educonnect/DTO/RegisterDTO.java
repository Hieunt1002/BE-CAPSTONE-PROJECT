package com.capstoneproject.educonnect.DTO;

public class RegisterDTO {
	private String fullname;
	private String email;
	private String passwork;
	private String phone;
	private int role;
	private String option;

	public RegisterDTO() {
	}

	public RegisterDTO(String fullname, String email, String passwork, String phone, int role,
			String option) {
		this.fullname = fullname;
		this.email = email;
		this.passwork = passwork;
		this.phone = phone;
		this.role = role;
		this.option = option;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswork() {
		return passwork;
	}

	public void setPasswork(String passwork) {
		this.passwork = passwork;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

}
