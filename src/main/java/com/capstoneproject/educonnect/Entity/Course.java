package com.capstoneproject.educonnect.Entity;
import java.util.List;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseid")
    private int courseId;

    @Column(name = "coursename",columnDefinition = "NVARCHAR(200)")
    private String courseName;
    
    @OneToMany(targetEntity = ClassCourse.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "courseid",referencedColumnName = "courseid")
    List<ClassCourse> classcourse;
    
    @OneToMany(targetEntity = DiscountCourse.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "courseid",referencedColumnName = "courseid")
    List<DiscountCourse> discountCourse;
}
