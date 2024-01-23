package com.capstoneproject.educonnect.Entity;

import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "exercise")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exerciseid")
    private int exerciseId;

    @Column(name = "bookid")
    private int bookid;

    @Column(name = "title",columnDefinition = "NVARCHAR(200)")
    private String title;
    
    @OneToMany(targetEntity = Files.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "exerciseid",referencedColumnName = "exerciseid")
    List<Files> files;
    
    @OneToMany(targetEntity = Video.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "exerciseid",referencedColumnName = "exerciseid")
    List<Video> video;
    
    @OneToMany(targetEntity = Homework.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "exerciseid",referencedColumnName = "exerciseid")
    List<Homework> homework;
    
    @OneToMany(targetEntity = Classroom.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "exerciseid",referencedColumnName = "exerciseid")
    List<Classroom> classroom;
}