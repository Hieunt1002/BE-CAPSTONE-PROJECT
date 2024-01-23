package com.capstoneproject.educonnect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.TrialRegistrationDTO;
import com.capstoneproject.educonnect.Entity.Trylearning;

@Repository
public interface TrylearnRepository  extends JpaRepository<Trylearning, Integer>{

	List<TrialRegistrationDTO> findByStatus(String status);
}
