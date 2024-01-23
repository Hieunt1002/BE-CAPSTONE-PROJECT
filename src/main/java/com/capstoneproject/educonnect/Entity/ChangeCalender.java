package com.capstoneproject.educonnect.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "changecalender")
@Getter
@Setter
@NoArgsConstructor
public class ChangeCalender {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "changecalenderid")
    private int changecalenderid;
	
	@Column(name = "bookid")
    private int bookid;
	
	@Column(name = "datelearn", columnDefinition = "DATE") 
    private String datelearn;
	
	@Column(name = "datechange", columnDefinition = "DATE") 
    private String datechange;
	
	@Column(name = "timeid")
    private int timeid;
	
	@Column(name = "timechange")
    private String timechange;
	
	@Column(name = "lessonline")
    private String lessonline;
}
