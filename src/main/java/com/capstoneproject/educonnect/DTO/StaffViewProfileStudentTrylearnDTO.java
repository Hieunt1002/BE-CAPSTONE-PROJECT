package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffViewProfileStudentTrylearnDTO {

	@JsonProperty("studentid")
	private int studentid;

	@JsonProperty("studentname")
	private String studentname;

	@JsonProperty("img")
	private String img;

	@JsonProperty("birthdate")
	private String birthdate;

	@JsonProperty("city")
	private String city;

	@JsonProperty("wards")
	private String wards;

	@JsonProperty("email")
	private String email;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("class")
	private String className;

	@JsonProperty("coursename")
	private String courseName;

	@JsonProperty("tutorid")
	private int tutorid;

	@JsonProperty("tutorname")
	private String tutorname;

	@JsonProperty("staffid")
	private int staffid;
}
