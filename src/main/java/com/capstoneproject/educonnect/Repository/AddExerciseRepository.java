package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.Exercise;

@Repository
public interface AddExerciseRepository extends JpaRepository<Exercise, Integer> {

}
