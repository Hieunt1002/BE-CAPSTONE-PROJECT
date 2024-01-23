package com.capstoneproject.educonnect.Entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "studentid")
    private int studentid;
	
	@Column(name = "classid")
    private int classId;
	
	@Column(name = "img")
    private String img;

    @Column(name = "birthdate", columnDefinition = "DATE")
    private String birthdate;

    @Column(name = "city")
    private String city;

    @Column(name = "wards")
    private String wards;
    
    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    @PrimaryKeyJoinColumn(name = "userid")
    private User user;
    
    @OneToMany(targetEntity = BookingEntity.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "studentid",referencedColumnName = "studentid")
    List<BookingEntity> booking;
    
    @OneToMany(targetEntity = Trylearning.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "studentid",referencedColumnName = "studentid")
    List<Trylearning> trylearning;
}