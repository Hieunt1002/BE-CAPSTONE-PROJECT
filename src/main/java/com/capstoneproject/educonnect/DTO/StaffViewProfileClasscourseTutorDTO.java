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
public class StaffViewProfileClasscourseTutorDTO {
	@JsonProperty("classcourseid")
	private int classCourseId;

	@JsonProperty("coursename")
    private String courseName;
	
	@JsonProperty("classname")
	private String classname;
}
