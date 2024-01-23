package com.capstoneproject.educonnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountTutorAndStudentByClassDTO {

	private String name;
	
	private long hoc_sinh;
	
	private long gia_su;
}
