package com.capstoneproject.educonnect.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "tutor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tutor implements Serializable {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "tutorid")
    private int tutorId;

	@Column(name = "staffid")
    private int staffid;

    @Column(name = "img")
    private String img;

    @Column(name = "birthdate", columnDefinition = "DATE")
    private String birthdate;

    @Column(name = "city")
    private String city;

    @Column(name = "wards")
    private String wards;

    @Column(name = "cv")
    private String cv;

    @Column(name = "experience")
    private int experience;

    @Column(name = "salary")
    private float salary;


    @Column(name = "price")
    private float price;
    
    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    @PrimaryKeyJoinColumn(name = "userid")
    private User user;
    
    @OneToMany(targetEntity = TeachTime.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "tutorid",referencedColumnName = "tutorid")
    List<TeachTime> teachtimes;
    
    @OneToMany(targetEntity = Teach.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "tutorid",referencedColumnName = "tutorid")
    List<Teach> teach;
    
    @OneToMany(targetEntity = BookingEntity.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "tutorid",referencedColumnName = "tutorid")
    List<BookingEntity> booking;
    
    @OneToMany(targetEntity = Trylearning.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "tutorid",referencedColumnName = "tutorid")
    List<Trylearning> trylearning;
    
    @OneToMany(targetEntity = Payment.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "tutorid",referencedColumnName = "tutorid")
    List<Payment> Payment;
    
}