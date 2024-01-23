package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddLinkMeetDTO {

	@JsonProperty("bookid")
	private int bookid;
	
	@JsonProperty("linkmeet")
	private String linkmeet;
}
