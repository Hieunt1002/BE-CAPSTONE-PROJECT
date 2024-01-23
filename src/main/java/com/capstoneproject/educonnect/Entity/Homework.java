package com.capstoneproject.educonnect.Entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "homework")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "homeworkid")
    private int homeworkId;

    @Column(name = "exerciseid")
    private int exerciseid;

    @Column(name = "demoid")
    private int demoid;

    @Column(name = "files", length = 200)
    private String files;

    @Column(name = "title",columnDefinition = "NVARCHAR(200)")
    private String title;

    @Column(name = "startdate", columnDefinition = "DATE")
    private String startDate;

    @Column(name = "enddate", columnDefinition = "DATE")
    private String endDate;
    
    @OneToOne(cascade = CascadeType.ALL, optional = true, mappedBy = "homework")
    private Submit submit;
}