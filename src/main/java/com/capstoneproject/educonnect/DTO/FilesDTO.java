package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilesDTO {

    @JsonProperty("fileid")
    private int fileId;

    @JsonProperty("exerciseid")
    private int exerciseid;

    @JsonProperty("namefile")
    private String nameFile;

    @JsonProperty("files")
    private String files;

    @JsonProperty("status")
    private int status;
}
