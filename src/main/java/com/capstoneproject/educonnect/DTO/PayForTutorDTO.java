package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayForTutorDTO {
	
	@JsonProperty("tutorid")
	private int tutorid;
	
	@JsonProperty("money")
	private float money;
	
	@JsonProperty("banknumber")
	private String banknumber;
	
	@JsonProperty("bank")
	private String bank;
	
	@JsonProperty("date")
	private String date;
}
