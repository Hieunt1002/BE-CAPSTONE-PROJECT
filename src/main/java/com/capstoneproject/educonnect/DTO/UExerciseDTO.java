package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UExerciseDTO {

	@JsonProperty("exerciseid")
	private int exerciseId;

	@JsonProperty("title")
	private String title;
}
