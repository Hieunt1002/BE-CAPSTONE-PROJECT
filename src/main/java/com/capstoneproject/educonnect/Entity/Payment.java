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
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentid")
    private int paymentid;
	
	@Column(name = "tutorid")
    private int tutorid;
	
	@Column(name = "money")
    private float money;
	
	@Column(name = "datepay", columnDefinition = "DATE")
    private String datepay;
	
	@Column(name = "banknumber")
    private String banknumber;
	
	@Column(name = "bank")
    private String bank;
}
