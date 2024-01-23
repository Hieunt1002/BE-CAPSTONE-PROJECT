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
public class ListStaffByAdminDTO {

	@JsonProperty("staffid")
	private int staffid;

	@JsonProperty("fullname")
	private String fullname;

	@JsonProperty("city")
	private String city;

	@JsonProperty("wards")
	private String wards;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("createdate")
	private String createdate;

	@JsonProperty("status")
	private int status;
	
	@JsonProperty("salary")
	private float salary;
}
