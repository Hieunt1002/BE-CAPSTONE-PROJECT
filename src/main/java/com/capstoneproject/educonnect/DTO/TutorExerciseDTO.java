package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TutorExerciseDTO {
	
	@JsonProperty("tutorid")
	private int tutorid;
	
	@JsonProperty("fullname")
	public String fullname;
	
	@JsonProperty("course")
	public String course;
	
	@JsonProperty("classname")
	public String classname;
	
	@JsonProperty("startdate")
	public String startdate;
	
	@JsonProperty("enddate")
	public String enddate;
}
