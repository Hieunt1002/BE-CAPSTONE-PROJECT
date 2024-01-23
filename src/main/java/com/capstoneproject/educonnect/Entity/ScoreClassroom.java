package com.capstoneproject.educonnect.Entity;

import java.io.Serializable;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "scoreclassroom")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreClassroom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "scoreid")
	private int scoreid;

	@Column(name = "score")
	private float score;

	@Column(name = "status", columnDefinition = "CHAR(1) DEFAULT 'N'")
	private char status;

	@OneToOne(cascade = CascadeType.MERGE, optional = false)
	@PrimaryKeyJoinColumn(name = "classroomid")
	Classroom classroom;
}