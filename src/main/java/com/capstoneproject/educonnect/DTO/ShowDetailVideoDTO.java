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
public class ShowDetailVideoDTO {
	@JsonProperty("exerciseid")
    private int exerciseId;

    @JsonProperty("title")
    private String titleExercise;
    
    @JsonProperty("classcourseid")
	private int classCourseId;
    
    @JsonProperty("courseName")
	private String courseName;
    
    @JsonProperty("tutorid")
    private int tutorId;
    
    @JsonProperty("fullname")
    private String fullname;
    
    @JsonProperty("videoid")
    private int videoId;

	@JsonProperty("namevideo")
    private String nameVideo;

	@JsonProperty("video")
    private String video;
	
}
