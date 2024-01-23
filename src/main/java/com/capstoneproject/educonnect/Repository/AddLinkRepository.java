package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.Classroom;

@Repository
public interface AddLinkRepository extends JpaRepository<Classroom, Integer> {

}
