package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{
}
