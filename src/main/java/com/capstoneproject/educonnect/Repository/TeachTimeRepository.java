package com.capstoneproject.educonnect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstoneproject.educonnect.Entity.TeachTime;

public interface TeachTimeRepository extends JpaRepository<TeachTime, Integer> {
	List<TeachTime> findAllByTutorId(int tutorid);
}
