package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class USalaryTutorDTO {

	@JsonProperty("tutorid")
	private int tutorid;
	
	@JsonProperty("price")
	private float price;
}
