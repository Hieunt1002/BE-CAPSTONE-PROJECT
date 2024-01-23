package com.capstoneproject.educonnect.Entity;

import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "classcourse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classcourseid")
    private int classCourseId;

    @Column(name = "courseid")
    private int courseid;

    @Column(name = "classid")
    private int classid;

    @Column(name = "img")
    private String img;
    
    @OneToMany(targetEntity = Teach.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "classcourseid",referencedColumnName = "classcourseid")
    List<Teach> teach;
    
    @OneToMany(targetEntity = BookingEntity.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "classcourseid",referencedColumnName = "classcourseid")
    List<BookingEntity> booking;
    
    @OneToMany(targetEntity = Demo.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "classcourseid",referencedColumnName = "classcourseid")
    List<Demo> demo;
    
    @OneToMany(targetEntity = Trylearning.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "classcourseid",referencedColumnName = "classcourseid")
    List<Trylearning> trylearning;
}
