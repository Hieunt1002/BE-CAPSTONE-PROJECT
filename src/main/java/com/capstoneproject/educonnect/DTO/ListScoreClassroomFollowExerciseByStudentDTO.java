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
public class ListScoreClassroomFollowExerciseByStudentDTO {
	@JsonProperty("tutorid")
	private int tutorId;

	@JsonProperty("studentid")
	private int studentid;

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

	@JsonProperty("score")
	private float score;
	
	@JsonProperty("submitdate")
	private String submitdate;
	
	@JsonProperty("status")
	private char status;
}
