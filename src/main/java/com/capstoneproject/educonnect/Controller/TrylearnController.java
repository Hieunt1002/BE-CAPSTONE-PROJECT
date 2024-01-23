package com.capstoneproject.educonnect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.educonnect.DTO.TrialRegistrationDTO;
import com.capstoneproject.educonnect.Service.TrylearnService;

@RestController
@RequestMapping("/trylearn")
public class TrylearnController {

	@Autowired
	private TrylearnService trylearnService;
	
	@PostMapping("/booktrylearn")
    public void createTrialRegistration(@RequestBody TrialRegistrationDTO trialRegistrationDTO) {
           trylearnService.createTrialRegistration(trialRegistrationDTO);
    }
}
