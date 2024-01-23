package com.capstoneproject.educonnect.Entity;

import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "timeline")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timeline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeid")
    private int timeId;

    @Column(name = "timeline")
    private String timeline;
    
    @Column(name = "endtime")
    private String endtime;
    
    @OneToMany(targetEntity = TimeBook.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "timeid",referencedColumnName = "timeid")
    List<TimeBook> timebook;
    
    @OneToMany(targetEntity = TimeBook.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "timeid",referencedColumnName = "timeid")
    List<TeachTime> TeachTime;
    
    @OneToMany(targetEntity = ChangeCalender.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "timeid",referencedColumnName = "timeid")
    List<ChangeCalender> changeCalender;
}