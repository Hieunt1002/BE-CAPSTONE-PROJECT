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
public class ListClassroomByTutorDTO {
	@JsonProperty("tutorid")
	private int tutorId;

	@JsonProperty("fullname")
	private String fullname;

	@JsonProperty("coursename")
	private String courseName;

	@JsonProperty("class")
	private String className;

	@JsonProperty("exerciseid")
	private int exerciseid;

	@JsonProperty("title")
	private String title;
	
	@JsonProperty("classroomid")
	private int classroomId;

	@JsonProperty("nameclassroom")
	private String nameClassroom;

	@JsonProperty("link")
	private String link;
	
	@JsonProperty("scoreid")
    private int scoreid;
	
	@JsonProperty("score")
    private float score;
}
