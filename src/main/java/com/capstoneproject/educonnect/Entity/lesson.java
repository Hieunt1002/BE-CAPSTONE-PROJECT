package com.capstoneproject.educonnect.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lesson")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class lesson {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lessonid")
    private int lessonId;

    @Column(name = "lessonline")
    private String lessonline;
    
    @Column(name = "dayofweek")
    private String dayofweek;
    
    @OneToMany(targetEntity = TeachTime.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "lessonid",referencedColumnName = "lessonid")
    List<TeachTime> teachtimes;
    
    @OneToMany(targetEntity = TimeBook.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "lessonid",referencedColumnName = "lessonid")
    List<TimeBook> TimeBook;
    
}
