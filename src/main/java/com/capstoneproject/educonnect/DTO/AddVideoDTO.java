package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddVideoDTO {
	
	@JsonProperty("exerciseid")
    private int exerciseid;

    @JsonProperty("namevideo")
    private String nameVideo;

    @JsonProperty("video")
    private String video;
    
}
