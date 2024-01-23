package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcceptFileDTO {

	@JsonProperty("fileid")
	private int fileid;
	
	@JsonProperty("status")
	private int status;
}
