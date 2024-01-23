package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VideoDTO {
	@JsonProperty("videoid")
    private int videoId;

	@JsonProperty("exerciseid")
    private int exerciseid;

	@JsonProperty("namevideo")
    private String nameVideo;

	@JsonProperty("video")
    private String video;

	@JsonProperty("status")
    private int status;

	@QueryProjection
	public VideoDTO(int videoId, int exerciseid, String nameVideo, String video, int status) {
		super();
		this.videoId = videoId;
		this.exerciseid = exerciseid;
		this.nameVideo = nameVideo;
		this.video = video;
		this.status = status;
	}
}
