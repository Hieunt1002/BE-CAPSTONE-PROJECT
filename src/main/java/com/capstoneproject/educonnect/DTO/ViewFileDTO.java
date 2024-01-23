package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewFileDTO {

	@JsonProperty("fileid")
	private int fileId;
	
	@JsonProperty("exerciseid")
	private int exerciseid;
	
	@JsonProperty("nameFile")
	 private String nameFile;
	 
	@JsonProperty("files")
	 private String files;
	 
	@JsonProperty("status")
	 private int status;
}
