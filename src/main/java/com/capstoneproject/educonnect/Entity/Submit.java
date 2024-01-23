package com.capstoneproject.educonnect.Entity;

import java.io.Serializable;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "submit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Submit implements Serializable{
    

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "submitid")
    private int submitid;
	
	@Column(name = "files", length = 200)
    private String files;
    
    @Column(name = "submitdate", columnDefinition = "DATE")
    private String submitdate;

    @Column(name = "score")
    private float score;
    
    @Column(name = "status", columnDefinition = "CHAR(1) DEFAULT 'N'")
    private char status;
    
    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    @PrimaryKeyJoinColumn(name = "homeworkid")
    Homework homework;
}