package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UFeedbackDTO {
	@JsonProperty("feedbackid")
    private int feedbackid;
	
	@JsonProperty("notes")
    private String notes;

	@JsonProperty("ranks")
    private float ranks;
}
