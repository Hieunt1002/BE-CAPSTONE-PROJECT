package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class USalaryStaffDTO {
	@JsonProperty("staffid")
	private int staffid;
	
	@JsonProperty("salary")
	private float salary;
}
