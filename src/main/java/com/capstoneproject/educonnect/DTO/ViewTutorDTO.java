package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewTutorDTO {

    @JsonProperty("tutorid")
    private int tutorId;
    
    @JsonProperty("fullname")
    private String fullname;
    
    @JsonProperty("phone")
    private String phone;
    
    @JsonProperty("img")
    private String img;
    
    @JsonProperty("gender")
    private int gender;
    
    @JsonProperty("birthdate")
    private String birthdate;

    @JsonProperty("city")
    private String city;
    
    @JsonProperty("wards")
    private String wards;
    
    @JsonProperty("experience")
    private int experience;
    
    @JsonProperty("salary")
    private float salary;
}
