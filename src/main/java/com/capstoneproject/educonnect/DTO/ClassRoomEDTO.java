package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomEDTO {

	@JsonProperty("classroomid")
	private int classroomId;

	@JsonProperty("exerciseid")
    private int exerciseid;

	@JsonProperty("nameclassroom")
    private String nameClassroom;

	@JsonProperty("link")
    private String link;
	 
	@JsonProperty("submitdate")
	private String submitdate;
}