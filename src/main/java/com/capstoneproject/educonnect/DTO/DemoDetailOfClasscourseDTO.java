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
public class DemoDetailOfClasscourseDTO {
	@JsonProperty("demoid")
    private int demoId;

    @JsonProperty("demo")
    private String demo;

    @JsonProperty("files")
    private String files;
    
    @JsonProperty("classcourseid")
   	private int classCourseId;
}
