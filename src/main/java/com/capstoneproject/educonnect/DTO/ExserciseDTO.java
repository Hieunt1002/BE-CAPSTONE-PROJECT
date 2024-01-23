package com.capstoneproject.educonnect.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class ExserciseDTO {
	
	@JsonProperty("exerciseid")
    private int exerciseId;

    @JsonProperty("bookid")
    private int bookid;

    @JsonProperty("title")
    private String title;
    
    @QueryProjection
	public ExserciseDTO(int exerciseId, int bookid, String title) {
		super();
		this.exerciseId = exerciseId;
		this.bookid = bookid;
		this.title = title;
	}
}
