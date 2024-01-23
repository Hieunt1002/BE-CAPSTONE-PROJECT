package com.capstoneproject.educonnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieChartDTO {

	private String name;
	private long value;
	private String fill;
}
