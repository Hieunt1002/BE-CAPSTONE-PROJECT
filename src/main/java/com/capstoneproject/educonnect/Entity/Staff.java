package com.capstoneproject.educonnect.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
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

	@Column(name = "salary")
	private float salary;

	@Column(name = "experience")
	private int experience;

	@OneToOne(cascade = CascadeType.MERGE, optional = false)
    @PrimaryKeyJoinColumn(name = "userid")
	private User user;

	@OneToMany(targetEntity = Tutor.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "staffid", referencedColumnName = "staffid")
	List<Tutor> tutors;

	@OneToMany(targetEntity = Discount.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "staffid", referencedColumnName = "staffid")
	List<Discount> discount;
}