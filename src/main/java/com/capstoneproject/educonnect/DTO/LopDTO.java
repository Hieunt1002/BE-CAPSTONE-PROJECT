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
public class LopDTO {
	@JsonProperty("classid")
	public int classid;
	
	@JsonProperty("className")
	public String className;
	
	@JsonProperty("levelid")
	public int levelid;
}
