package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.Classroom;

@Repository
public interface NewClassRoomRepository extends JpaRepository<Classroom, Integer>  {

}
