package com.capstoneproject.educonnect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.Discount;
import com.capstoneproject.educonnect.Entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
	@Query("Select d from Discount as d ")
	List<Discount> getListDiscount();	
}
