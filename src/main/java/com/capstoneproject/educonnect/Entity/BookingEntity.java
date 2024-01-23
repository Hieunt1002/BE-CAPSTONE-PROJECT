package com.capstoneproject.educonnect.Entity;

import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookid")
    private int bookId;

    @Column(name = "studentid")
    private int studentid;

    @Column(name = "tutorid")
    private int tutorid;

    @Column(name = "classcourseid")
    private int classcourseid;
    
    @Column(name = "linkmeet")
    private String linkmeet;
    
    @Column(name = "dateregister", columnDefinition = "DATE") 
    private String dateregister;

    @Column(name = "startdate", columnDefinition = "DATE") 
    private String startDate;

    @Column(name = "enddate", columnDefinition = "DATE")
    private String endDate;
    
    @Column(name = "datepay", columnDefinition = "DATE")
    private String datePay;
    
    @Column(name = "status")
    private int status;
    
    @OneToOne(cascade = CascadeType.ALL, optional = true, mappedBy = "booking")
	private Feedback feedback;
    
    @OneToMany(targetEntity = TimeBook.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid",referencedColumnName = "bookid")
    List<TimeBook> timebook;
    
    @OneToMany(targetEntity = Exercise.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid",referencedColumnName = "bookid")
    List<Exercise> exercise;
    
    @OneToMany(targetEntity = ChangeCalender.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid",referencedColumnName = "bookid")
    List<ChangeCalender> changeCalender;
}