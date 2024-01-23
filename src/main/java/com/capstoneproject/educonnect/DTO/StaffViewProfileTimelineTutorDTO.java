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
public class StaffViewProfileTimelineTutorDTO {
	@JsonProperty("teachtimeid")
    private int teachtimeid;
	
	@JsonProperty("timeline")
    private String timeline;
	
	@JsonProperty("lesson")
	private String lesson;
}
