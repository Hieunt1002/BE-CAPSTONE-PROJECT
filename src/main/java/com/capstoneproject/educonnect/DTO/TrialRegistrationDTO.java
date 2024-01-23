package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrialRegistrationDTO {
	
	@JsonProperty("studentid")
	private int studentid;
	
	@JsonProperty("tutorid")
	private int tutorid;
	
	@JsonProperty("classcourseid")
    private int classcourseid;
}
