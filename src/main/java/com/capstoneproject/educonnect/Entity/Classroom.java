package com.capstoneproject.educonnect.Entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "classroom")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroomid")
    private int classroomId;

    @Column(name = "exerciseid")
    private int exerciseid;

    @Column(name = "nameclassroom", length = 200)
    private String nameClassroom;

    @Column(name = "link", length = 500)
    private String link;
    
    @Column(name = "submitdate", columnDefinition = "DATE")
    private String submitdate;
    
    @OneToOne(cascade = CascadeType.ALL, optional = true, mappedBy = "classroom")
    private ScoreClassroom scoreClassroom;
}