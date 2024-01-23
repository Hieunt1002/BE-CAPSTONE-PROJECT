package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListTimelineOfTutorDTO {
	@JsonProperty("timeid")
	private int timeId;
	
	@JsonProperty("timeline")
	private String timeline;
	
	@JsonProperty("tutorid")
	private int tutorid;
}
