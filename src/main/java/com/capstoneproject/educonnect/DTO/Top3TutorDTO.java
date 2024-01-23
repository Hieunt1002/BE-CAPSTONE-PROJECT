package com.capstoneproject.educonnect.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Top3TutorDTO {
	
    @JsonProperty("tutorid")
    private int tutorId;

    @JsonProperty("fullname")
    private String fullname;

    @JsonProperty("ranks")
    private double ranks;
    
    @JsonProperty("img")
    private String img;
}