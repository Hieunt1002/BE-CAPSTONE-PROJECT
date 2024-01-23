package com.capstoneproject.educonnect.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "timebook")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeBook{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timebookid")
    private int timebookid;

    @Column(name = "bookid")
    private int bookid;
    
    @Column(name = "timeid")
    private int timeid;
    
    @Column(name = "lessonid")
    private int lessonid;
}