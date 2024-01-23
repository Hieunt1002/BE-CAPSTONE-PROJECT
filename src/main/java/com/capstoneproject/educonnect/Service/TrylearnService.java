package com.capstoneproject.educonnect.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.TrialRegistrationDTO;
import com.capstoneproject.educonnect.Entity.Trylearning;
import com.capstoneproject.educonnect.Repository.TrylearnRepository;

@Service
public class TrylearnService {

	 	@Autowired
	    private TrylearnRepository trylearnRepository;
	 	
	 	LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateString = today.format(formatter);

	    public void createTrialRegistration(TrialRegistrationDTO trialRegistrationDTO) {
	    	Trylearning trylearning = new Trylearning();
	    	trylearning.setStudentid(trialRegistrationDTO.getStudentid());
	    	trylearning.setTutorid(trialRegistrationDTO.getTutorid());
	    	trylearning.setClasscourseid(trialRegistrationDTO.getClasscourseid());
	    	trylearning.setStatus(2);
	    	trylearning.setDateregister(dateString);
	    	trylearnRepository.save(trylearning);
	    }

	    public List<TrialRegistrationDTO> getPendingTrialRegistrations() {
	        return trylearnRepository.findByStatus("Chờ xác nhận");
	    }
}
