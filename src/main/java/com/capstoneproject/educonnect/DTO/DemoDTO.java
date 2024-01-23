package com.capstoneproject.educonnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoDTO {

	private String title;
	
	private String file;

	private int demoid;
	
	private String startDate;
	
	private String endDate;
}
