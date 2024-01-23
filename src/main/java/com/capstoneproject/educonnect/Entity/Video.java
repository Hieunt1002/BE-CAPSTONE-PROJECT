package com.capstoneproject.educonnect.Entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "video")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "videoid")
    private int videoId;

    @Column(name = "exerciseid")
    private int exerciseid;

    @Column(name = "namevideo", length = 200)
    private String nameVideo;

    @Column(name = "video", length = 500)
    private String video;

    @Column(name = "status")
    private int status;
}