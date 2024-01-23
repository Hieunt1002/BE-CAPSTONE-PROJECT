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
public class StudentViewDetailTutorDTO {
	@JsonProperty("tutorid")
	private int tutorId;

	@JsonProperty("fullname")
	private String fullname;
	
	@JsonProperty("img")
    private String img;
	
	@JsonProperty("countStudent")
	private long countStudent;
	
	@JsonProperty("experience")
	private int experience;
	
	@JsonProperty("countClasscourse")
	private long countClasscourse;
	
	@JsonProperty("ranks")
	private double ranks;
	

}
