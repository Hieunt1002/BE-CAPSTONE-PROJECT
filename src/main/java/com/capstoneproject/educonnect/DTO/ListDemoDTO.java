package com.capstoneproject.educonnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListDemoDTO {
	
	private int classcourseid;
	
	private String coursename;
	
	private String classname;
	
	private int demoid;
	
	private String img;
	
	private String demoname;
	
	private String linkdemo;
}
