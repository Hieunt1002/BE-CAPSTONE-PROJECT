package com.capstoneproject.educonnect.Repository.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.DiscountDTO;
import com.capstoneproject.educonnect.Entity.Discount;

import com.capstoneproject.educonnect.Entity.Staff;

import com.capstoneproject.educonnect.Repository.DiscountQueryRepository;

import com.capstoneproject.educonnect.Repository.StaffRepository;
import com.capstoneproject.educonnect.Service.StaffService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StaffServiceImp implements StaffService {

	private final StaffRepository staffRepository;
	private final DiscountQueryRepository discountQueryRepository;

	@Override
	public List<Discount> getListDiscount() {
		return staffRepository.getListDiscount();

	}

	@Override
	public int updateDiscount(DiscountDTO discountDTO) {
		Optional<Discount> optionalDiscount = discountQueryRepository.findById(discountDTO.getDiscountid());
		if (optionalDiscount.isPresent()) {
			Discount discount = optionalDiscount.get();
			Discount discount2 = new Discount();
			discount2.setEntity(discount, discountDTO);
			Discount updatedDiscount = discountQueryRepository.save(discount2);
			return (updatedDiscount != null) ? 1 : 0;
		} 
		return 0;
	}

}
