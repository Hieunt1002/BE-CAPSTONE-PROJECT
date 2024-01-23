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
public class StaffViewProfileTimelineStudentDTO {

	@JsonProperty("timebookid")
	private int timebookid;

	@JsonProperty("timeline")
	private String timeline;
	
	@JsonProperty("classcourseid")
	private int classCourseId;
	
	@JsonProperty("coursename")
    private String courseName;
	
	@JsonProperty("lesson")
	private String lesson;
}
