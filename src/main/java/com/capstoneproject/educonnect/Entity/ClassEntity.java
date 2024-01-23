package com.capstoneproject.educonnect.Entity;
import java.util.List;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "class")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classid")
    private int classId;

    @Column(name = "class")
    private String className;
    
    @Column(name = "levelid")
    private int levelid;
    
    @OneToMany(targetEntity = Student.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "classid",referencedColumnName = "classid")
    List<Student> students;
    
    @OneToMany(targetEntity = ClassCourse.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "classid",referencedColumnName = "classid")
    List<ClassCourse> classcourse;
}
