package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UVideoDTO {
	@JsonProperty("videoid")
    private int videoId;

	@JsonProperty("namevideo")
    private String nameVideo;

	@JsonProperty("video")
    private String video;
}