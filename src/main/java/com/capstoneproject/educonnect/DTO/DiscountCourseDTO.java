package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCourseDTO {
	
	@JsonProperty("discountid")
	private int discountid;
	
	@JsonProperty("courseid")
	private int courseid;
}
