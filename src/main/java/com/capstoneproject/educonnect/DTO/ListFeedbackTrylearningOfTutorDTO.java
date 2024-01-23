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
public class ListFeedbackTrylearningOfTutorDTO {
	@JsonProperty("tutorid")
	private int tutorid;
	
	@JsonProperty("studentid")
	private int studentid;
	
	@JsonProperty("fullname")
	private String studentName;
	
	@JsonProperty("classcourseid")
	private int classcourseid;
	
	@JsonProperty("coursename")
	private String coursename;
	
	@JsonProperty("classname")
	public String classname;
	
	@JsonProperty("img")
	private String img;
	
	@JsonProperty("dateregister")
	private String dateregister;
	
	@JsonProperty("feedbackid")
	private int feedbackid;
	
	@JsonProperty("notes")
	private String notes;
	
	@JsonProperty("ranks")
	private float ranks;
}
