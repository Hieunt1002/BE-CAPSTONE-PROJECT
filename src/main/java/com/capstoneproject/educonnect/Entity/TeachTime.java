package com.capstoneproject.educonnect.Entity;



import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "teachtime")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeachTime{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teachtimeid")
    private int teachtimeid;
	
    @Column(name = "tutorid")
    private int tutorId;

    @Column(name = "lessonid")
    private int lessonid;
    
    @Column(name = "timeid")
    private int timeId;
}