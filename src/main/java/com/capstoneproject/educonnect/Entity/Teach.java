package com.capstoneproject.educonnect.Entity;



import javax.persistence.*;


import lombok.*;

@Entity
@Table(name = "teach")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teach{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teachid")
    private int teachid;

    @Column(name = "classcourseid")
    private int classcourseid;


    @Column(name = "tutorid")
    private int tutorid;
}