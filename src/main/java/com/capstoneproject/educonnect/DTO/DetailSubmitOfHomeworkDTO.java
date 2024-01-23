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
public class DetailSubmitOfHomeworkDTO {
	@JsonProperty("fullname")
	private String fullname;

	@JsonProperty("tutorid")
	private int tutorId;

	@JsonProperty("submitid")
	private int submitid;

	@JsonProperty("submitdate")
	private String submitdate;

	@JsonProperty("files")
	private String files;

	@JsonProperty("score")
	private float score;

}
