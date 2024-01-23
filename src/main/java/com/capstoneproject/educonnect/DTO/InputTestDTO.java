package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputTestDTO {
	@JsonProperty("studentId")
	private int studentId;
	
	@JsonProperty("date")
	private String date;
	
	@JsonProperty("time")
	private String time;
}
