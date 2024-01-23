package com.capstoneproject.educonnect.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.Discount;

@Repository
public interface DiscountRepository extends PagingAndSortingRepository<Discount, Integer> {

	@Query("Select D from Discount as D where (:title IS NULL OR UPPER(D.title) like CONCAT('%', :title, '%') ) ")
	Page<Discount> ListAllDiscount(@Param("title") String title, Pageable pageable);

	@Query("Select COUNT(D.discountid) from Discount as D where (:title IS NULL OR UPPER(D.title) like CONCAT('%', :title, '%') ) ")
	int totalCount(@Param("title") String title);

	@Query("Select COUNT(D.discountid) from Discount as D ")
	int totalCountUnconditional();

	
}
