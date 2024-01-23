package com.capstoneproject.educonnect.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.AddLinkMeetDTO;
import com.capstoneproject.educonnect.DTO.DiscountDTO;
import com.capstoneproject.educonnect.Entity.Discount;

@Service
public interface StaffService {
  public List<Discount> getListDiscount();
  
  int updateDiscount(DiscountDTO discountDTO);
}
