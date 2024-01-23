package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfileDTO {
	
	@JsonProperty("fullname")
	private String fullname;
	
	@JsonProperty("studentid")
	private int studentid;
	
	@JsonProperty("img")
	private String img;

	@JsonProperty("gender")
	private int gender;
	
	@JsonProperty("birthdate")
	private String birthdate;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("wards")
	private String wards;
	
	@JsonProperty("classId")
	private int classId;
	
	@JsonProperty("class")
	private String className;
		
}
