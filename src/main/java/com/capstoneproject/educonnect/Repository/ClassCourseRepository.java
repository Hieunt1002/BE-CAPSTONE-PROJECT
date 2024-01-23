package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.ClassCourse;

@Repository
public interface ClassCourseRepository extends JpaRepository<ClassCourse, Integer> {
	ClassCourse findClassCourseByClassidAndCourseid(int classid, int courseid);
}
