package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminStatisticsByYearDTO {
	
	@JsonProperty("Month")
	private String month;

	@JsonProperty("totalamount")
	private Long totalamount;

	@JsonProperty("payfortutor")
	private Long payfortutor;

	@JsonProperty("profit")
	private Long profit;
}

