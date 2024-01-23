package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeCalenderDTO {
	
	@JsonProperty("bookid")
	private int bookid;
	
	@JsonProperty("datechange")
	private String datechange;
	
	@JsonProperty("timeid")
	private int timeid;
	
	@JsonProperty("subject")
	private String subject;
	
	@JsonProperty("nametutor")
	private String nametutor;
	
	@JsonProperty("time")
	private String time;
	
	@JsonProperty("lesson")
	private String lesson;
	
	@JsonProperty("date")
	private String date;
}
