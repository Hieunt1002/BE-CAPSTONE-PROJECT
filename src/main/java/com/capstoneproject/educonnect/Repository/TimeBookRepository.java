package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.TimeBook;

@Repository
public interface TimeBookRepository extends JpaRepository<TimeBook, Integer>{

}
