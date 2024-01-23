package com.capstoneproject.educonnect.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trylearning")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trylearning {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trylearningid")
    private int trylearningid;

    @Column(name = "studentid")
    private int studentid;

    @Column(name = "tutorid")
    private int tutorid;

    @Column(name = "classcourseid")
    private int classcourseid;

    @Column(name = "dateregister", columnDefinition = "DATE") 
    private String dateregister;
    
    @Column(name = "datelearn", columnDefinition = "DATE") 
    private String datelearn;
    
    @Column(name = "status")
    private int status;
}
