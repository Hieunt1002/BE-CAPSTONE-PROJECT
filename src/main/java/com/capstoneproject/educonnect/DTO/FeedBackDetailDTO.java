package com.capstoneproject.educonnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBackDetailDTO {

	private int feedbackid;
	
	private String note;
	
	private float ranks;
}
