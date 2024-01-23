package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcceptTrylearnDTO {
	
	@JsonProperty("studentid")
	private int studentid;
	
	@JsonProperty("status")
	private int status;
	
	@JsonProperty("date")
	private String date;
	
	@JsonProperty("emailstudent")
	private String emailstudent;
	
	@JsonProperty("emailtutor")
	private String emailtutor;
	
	@JsonProperty("text")
	private String text;
}
