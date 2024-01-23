package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddLinkClassroomDTO {
	
	
	@JsonProperty("exerciseid")
	private int exerciseId;
	
	@JsonProperty("nameClassroom")
	private String nameClassroom;
	
	@JsonProperty("link")
	 private String link;
}
