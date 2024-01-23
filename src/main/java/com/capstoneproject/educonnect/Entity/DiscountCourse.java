package com.capstoneproject.educonnect.Entity;

import javax.persistence.Column;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "discountcourse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCourse {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discountcourseid")
    private int discountcourseid;
	
    @Column(name = "discountid")
    private int discountid;

    @Column(name = "courseid")
    private int courseid;
}
