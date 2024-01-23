package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffStatisticsByMonthDTO {
	@JsonProperty("TotalStudents")
	private Long totalStudents;
	
	@JsonProperty("TotalTutors")
	private Long totalTutors;
	
	@JsonProperty("TotalRevenue")
	private Long totalRevenue;
}
