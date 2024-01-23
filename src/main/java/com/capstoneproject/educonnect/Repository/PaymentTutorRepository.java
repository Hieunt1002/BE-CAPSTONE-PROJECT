package com.capstoneproject.educonnect.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.PayForTutorDTO;

@Repository
public interface PaymentTutorRepository {
	
	public void paymenttutor(int tutorid, float amount);
	
	PayForTutorDTO showbank(int tutorid);
	
	List<PayForTutorDTO> historypayment(int tutorid);
}
