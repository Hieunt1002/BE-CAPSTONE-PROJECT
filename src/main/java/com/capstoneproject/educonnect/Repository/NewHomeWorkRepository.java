package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.Homework;

@Repository
public interface NewHomeWorkRepository extends JpaRepository<Homework, Integer> {
	
}
