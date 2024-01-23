package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstoneproject.educonnect.Entity.lesson;

public interface LessonRepository extends JpaRepository<lesson, Integer> {

}
