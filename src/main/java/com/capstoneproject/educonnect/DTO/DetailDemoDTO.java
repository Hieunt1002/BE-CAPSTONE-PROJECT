package com.capstoneproject.educonnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailDemoDTO {

	private int demoid;
	
	private int classid;
	
	private int courseid;
	
	private String demo;
	
	private String linkdemo;
	
	private String img;
}
