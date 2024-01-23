package com.capstoneproject.educonnect.Service;

import java.util.List;
import java.util.Map;

import com.capstoneproject.educonnect.DTO.CourseDTO;
import com.capstoneproject.educonnect.DTO.DiscountCourseDTO;
import com.capstoneproject.educonnect.DTO.DiscountDTO;
import com.capstoneproject.educonnect.Entity.Discount;

public interface DiscountService {
	
	Map<String, Object> ListAllDiscount(String title, int pageNo);
	Map<String, Object> saveDiscount(DiscountDTO discountDTO);
	int deleteDiscount(List<Integer> listDeleteDiscount);
	int updateDiscount(DiscountDTO discountDTO);
	Discount detailDiscount(int discountId);
	int adddiscourse(DiscountCourseDTO discountCourseDTO);
	List<CourseDTO> listcoursebydiscourseid(int id);
}
