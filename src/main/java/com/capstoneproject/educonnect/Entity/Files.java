package com.capstoneproject.educonnect.Entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileid")
    private int fileId;

    @Column(name = "exerciseid")
    private int exerciseid;

    @Column(name = "namefile",columnDefinition = "NVARCHAR(200)")
    private String nameFile;

    @Column(name = "files", length = 200)
    private String files;

    @Column(name = "status")
    private int status;
}