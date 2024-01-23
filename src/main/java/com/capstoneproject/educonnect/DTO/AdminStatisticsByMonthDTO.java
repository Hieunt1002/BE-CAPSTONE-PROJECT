package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminStatisticsByMonthDTO {
	@JsonProperty("TotalStudents")
	private Long totalStudents;
	
	@JsonProperty("TotalTutors")
	private Long totalTutors;
	
	@JsonProperty("TotalStaff")
	private Long totalStaff;
	
	@JsonProperty("TotalRevenue")
	private Long totalRevenue;
}
