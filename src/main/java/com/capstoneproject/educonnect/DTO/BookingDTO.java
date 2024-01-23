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
public class BookingDTO {
	
	@JsonProperty("studentId")
	private int studentId;
	
	@JsonProperty("tutorId")
	private int tutorId;
	
	@JsonProperty("classCourseId")
	private int classCourseId;
}
