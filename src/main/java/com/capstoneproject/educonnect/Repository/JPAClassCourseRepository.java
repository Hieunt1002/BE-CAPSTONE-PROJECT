package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.ClassCourse;

@Repository
public interface JPAClassCourseRepository extends JpaRepository<ClassCourse, Integer> {
}
