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
public class InforDTO {
	
	@JsonProperty("fullname")
	private String fullname;
	
	@JsonProperty("course")
	private String course;
	
	@JsonProperty("lop")
	private String lop;
}
