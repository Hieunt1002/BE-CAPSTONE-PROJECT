package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UClassRoomDTO {
	
	@JsonProperty("classroomid")
    private int classroomId;

    @JsonProperty("nameclassroom")
    private String nameClassroom;

    @JsonProperty("link")
    private String link;
}