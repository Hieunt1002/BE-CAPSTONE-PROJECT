package com.capstoneproject.educonnect.DTO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstoneproject.educonnect.Entity.DiscountCourse;

public interface DiscountCourseRepository extends JpaRepository<DiscountCourse, Integer> {
	
	DiscountCourse findDiscounCourseByDiscountidAndCourseid(int discountid, int courseid);
}
