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
public class BookLesson {
	
	@JsonProperty("timeId")
	private int timeId;
	
	@JsonProperty("lessonid")
	private int lessonid;
	
	@JsonProperty("timeline")
	private String timeline;
	
	@JsonProperty("lessonline")
	private String lessonline;
}
