package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddExerciseDTO {
	
	@JsonProperty("bookid")
	private int bookid;

	@JsonProperty("title")
    private String title;
}
