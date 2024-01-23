package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
}
