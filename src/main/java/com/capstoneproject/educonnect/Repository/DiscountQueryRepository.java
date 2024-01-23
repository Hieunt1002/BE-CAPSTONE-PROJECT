package com.capstoneproject.educonnect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.Discount;

@Repository
public interface DiscountQueryRepository extends JpaRepository<Discount, Integer> {
	
	@Query("SELECT d FROM Discount d WHERE d.startDate <= CURRENT_DATE AND d.endDate >= CURRENT_DATE")
    List<Discount> findAllDiscountloestartdateAndgoeenddate();
	
	@Query("SELECT d FROM Discount d WHERE d.endDate >= CURRENT_DATE")
    List<Discount> findAllDiscountforcourse();
}
