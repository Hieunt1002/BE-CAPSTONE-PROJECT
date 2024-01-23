package com.capstoneproject.educonnect.Entity;

import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "demo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Demo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demoid")
    private int demoId;

    @Column(name = "classcourseid")
    private int classcourseid;

    @Column(name = "demo" ,columnDefinition = "NVARCHAR(200)")
    private String demo;

    @Column(name = "files")
    private String files;
    
    @Column(name = "img")
    private String img;
    
    @OneToMany(targetEntity = Homework.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "demoid",referencedColumnName = "demoid")
    List<Homework> video;
}