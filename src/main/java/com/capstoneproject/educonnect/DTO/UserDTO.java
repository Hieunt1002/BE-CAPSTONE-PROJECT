package com.capstoneproject.educonnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UserDTO {
	private String fullname;

	private String email;

	private String password;

	private String phone;

	private String createdate;

	private int gender;

	private int role;

	private int status;
}
