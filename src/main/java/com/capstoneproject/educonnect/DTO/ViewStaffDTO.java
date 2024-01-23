package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewStaffDTO {

	@JsonProperty("staffid")
	private int staffid;
	
	@JsonProperty("fullname")
	private String fullName;
	
	@JsonProperty("img")
	private String img;
	
	@JsonProperty("birthdate")
	private String birthdate;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("wards")
	private String wards;
	
	@JsonProperty("salary")
	private float salary;
	
	@JsonProperty("experience")
	private int experience;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("gender")
	private int gender;
}
