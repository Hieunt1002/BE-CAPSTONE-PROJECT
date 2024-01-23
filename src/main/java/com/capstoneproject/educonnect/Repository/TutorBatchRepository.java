package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.Tutor;

@Repository
public interface TutorBatchRepository extends JpaRepository<Tutor, Integer>{

}
