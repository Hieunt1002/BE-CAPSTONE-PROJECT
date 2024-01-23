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
public class AddClassRoomDTO {

	@JsonProperty("exerciseid")
    private int exerciseid;

	@JsonProperty("nameclassroom")
    private String nameClassroom;

	@JsonProperty("link")
    private String link;
}