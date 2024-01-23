package com.capstoneproject.educonnect.Repository.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.CourseDTO;
import com.capstoneproject.educonnect.DTO.DiscountCourseDTO;
import com.capstoneproject.educonnect.DTO.DiscountCourseRepository;
import com.capstoneproject.educonnect.DTO.DiscountDTO;
import com.capstoneproject.educonnect.Entity.Discount;
import com.capstoneproject.educonnect.Entity.DiscountCourse;
import com.capstoneproject.educonnect.Repository.CourseRepository;
import com.capstoneproject.educonnect.Repository.DiscountRepository;
import com.capstoneproject.educonnect.Service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {
	@Autowired
	private DiscountRepository discountRepository;
	
	@Autowired 
	private DiscountCourseRepository discountCourseRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Map<String, Object> ListAllDiscount(String title, int pageNo) {
		if (pageNo > 0 ) {
			pageNo -=1;
		}
		Map<String, Object> map = new HashMap<>();
		title = title != null ? title.trim() : null;
		int totalCount = discountRepository.totalCount(title);
		double pageCount = Math.ceil((double) totalCount / 10);
		map.put("pageCount", (int) pageCount);
		Pageable pageable = PageRequest.of(pageNo, 10, Sort.by("discountid"));
		Page<Discount> ListAllDiscount = discountRepository.ListAllDiscount(title, pageable);
		if (ListAllDiscount.hasContent()) {
			map.put("listDiscount", ListAllDiscount.getContent());
		} else {
			map.put("listDiscount", new ArrayList<Discount>());
		}

		return map;
	}

	@Override
	public Map<String, Object> saveDiscount(DiscountDTO discountDTO) {
		Map<String, Object> mapResponse = new HashMap<>();
		Discount discount = new Discount();
		int numberOldList = discountRepository.totalCountUnconditional();
		String startDate = discountDTO.getStartDate();
		String endDate = discountDTO.getEndDate();
		discountDTO.setStartDate(startDate.substring(0, 10));
		discountDTO.setEndDate(endDate.substring(0, 10));
		discount.setEntity(discountDTO);
		discountRepository.save(discount);
		int numberNewList = discountRepository.totalCountUnconditional();
		if (numberOldList < numberNewList) {
			mapResponse.put("message", "Save Successfully");
		} else {
			mapResponse.put("message", "Save Fail");
		}
		return mapResponse;
	}

	@Override
	public int deleteDiscount(List<Integer> listDeleteDiscount) {
		int numberOldList = discountRepository.totalCountUnconditional();
		int check =0;
		for (Integer integer : listDeleteDiscount) {
			Optional<Discount>discountDelete = discountRepository.findById(integer);
			if (discountDelete.isPresent()) {
				 discountRepository.deleteById(integer);
			}
		}
		int numberNewList = discountRepository.totalCountUnconditional();
		if (numberOldList > numberNewList) {
			check = 1;
		} else {
			check = 0;
		}
		return check;
	}

	@Override
	public int updateDiscount(DiscountDTO discountDTO) {
		Optional<Discount> discount = discountRepository.findById(discountDTO.getDiscountid());
		int check =0;
		if (discount.isPresent()) {
			Discount discount2 = new Discount();
			discount2.setEntity(discount.get(), discountDTO);
			Discount discountCheck = discountRepository.save(discount2);
			check = discountCheck.getDiscountid() != 0 ? 1 : 0;
		}
		return check;
	}

	@Override
	public Discount detailDiscount(int discountId) {
		Optional<Discount> discount = discountRepository.findById(discountId);
		return discount.get();
	}

	@Override
	public int adddiscourse(DiscountCourseDTO discountCourseDTO) {
		DiscountCourse result = discountCourseRepository.findDiscounCourseByDiscountidAndCourseid(discountCourseDTO.getDiscountid(), discountCourseDTO.getCourseid());
		int check = 0;
		if(result == null) {
			DiscountCourse discountCourse = new DiscountCourse();
			discountCourse.setDiscountid(discountCourseDTO.getDiscountid());
			discountCourse.setCourseid(discountCourseDTO.getCourseid());
			discountCourseRepository.save(discountCourse);
			return check = 1;
		}else {
			return check;
		}
	}

	@Override
	public List<CourseDTO> listcoursebydiscourseid(int id) {
		List<CourseDTO> result = courseRepository.listcoursebydiscountid(id);
		return result;
	}
}
