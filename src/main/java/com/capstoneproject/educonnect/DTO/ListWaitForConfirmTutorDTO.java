package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListWaitForConfirmTutorDTO {
	@JsonProperty("fullName")
	private String fullName;

	@JsonProperty("email")
	private String email;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("gender")
	private int gender;

	@JsonProperty("status")
	private int status;

	@JsonProperty("tutorid")
	private int tutorId;

	@JsonProperty("cv")
	private String cv;

	@JsonProperty("experience")
	private int experience;

	@JsonProperty("salary")
	private Float salary;

	@JsonProperty("price")
	private Float price;
}
