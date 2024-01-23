package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTutorDTO {
	
	@JsonProperty("tutorId")
	private int tutorId;
	
	@JsonProperty("fullname")
	private String fullname;
	
	@JsonProperty("img")
	private String img;
	
	@JsonProperty("coursename")
	private String coursename;
	
	@JsonProperty("className")
	private String className;
	
	@JsonProperty("price")
	private float price;
	
	@JsonProperty("classcourseid")
	private int classcourseid;
	
	@JsonProperty("ranks")
	private double ranks;
	
	@JsonProperty("count")
	private long count;
	
	@JsonProperty("status")
	private int status;
}
