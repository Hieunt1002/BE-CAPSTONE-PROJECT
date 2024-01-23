package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.ScoreClassroom;

@Repository
public interface ScoreClassroomRepository extends JpaRepository<ScoreClassroom, Integer> {

}
