package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListTutorForStudentDTO {
	
	@JsonProperty("tutorid")
	private int tutorId;
	
	@JsonProperty("img")
	private String img;

	@JsonProperty("fullname")
	private String fullname;
	
	@JsonProperty("experience")
	private int experience;
	
	@JsonProperty("timeid")
	private int timeId;
	
	@JsonProperty("price")
	private float price;
	
	@JsonProperty("CountStudent")
	private long CountStudent;
	
	
}
