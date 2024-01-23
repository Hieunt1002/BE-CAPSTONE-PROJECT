package com.capstoneproject.educonnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayForTuttorDTO {
	
	private int tutorid;
	
	private String nametutor;
	
	private String email;
	
	private String phone;
	
	private float money;
	
	private String banknumber;
	
	private String bank;
	
	private String date;
}
