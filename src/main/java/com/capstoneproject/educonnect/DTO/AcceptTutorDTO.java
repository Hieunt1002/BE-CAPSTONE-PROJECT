package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcceptTutorDTO {
	
	@JsonProperty("tutorid")
	private int tutorid;
	
	@JsonProperty("staffid")
	private int staffid;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("email")
	private String email;
}
