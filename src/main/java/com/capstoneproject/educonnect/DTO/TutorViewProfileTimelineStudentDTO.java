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
public class TutorViewProfileTimelineStudentDTO {
	@JsonProperty("studentid")
	private int studentid;
	
	@JsonProperty("bookid")
	private int bookId;

	@JsonProperty("timebookid")
	private int timebookid;

	@JsonProperty("timeid")
	private int timeid;

	@JsonProperty("lesson")
	private String lesson;
	
	@JsonProperty("timeline")
    private String timeline;
	
	@JsonProperty("img")
	private String img;
	
	@JsonProperty("fullname")
	private String fullname;
	
	@JsonProperty("birthdate")
	private String birthdate;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("phone")
	private String phone;

}
