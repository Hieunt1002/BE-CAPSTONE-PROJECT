package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailHomeWorkDTO {
	
	@JsonProperty("homeworkid")
	private int homeworkId;

	@JsonProperty("exerciseid")
    private int exerciseid;

	@JsonProperty("demoid")
    private int demoid;

	@JsonProperty("files")
    private String files;

	@JsonProperty("title")
    private String title;

	@JsonProperty("note")
    private String note;

	@JsonProperty("startDate")
    private String startDate;

	@JsonProperty("endDate")
    private String endDate;
}
