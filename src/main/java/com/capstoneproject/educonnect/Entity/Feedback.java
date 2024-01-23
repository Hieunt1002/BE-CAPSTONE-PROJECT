package com.capstoneproject.educonnect.Entity;

import java.io.Serializable;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback implements Serializable{
    

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "feedbackid")
    private int feedbackid;
	
	@Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "ranks")
    private float ranks;
    
    
    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    @PrimaryKeyJoinColumn(name = "bookid")
    BookingEntity booking;
}