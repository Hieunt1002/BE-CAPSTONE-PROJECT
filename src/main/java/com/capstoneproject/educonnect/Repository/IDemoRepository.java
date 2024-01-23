package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.Demo;

@Repository
public interface IDemoRepository extends JpaRepository<Demo, Integer>{
	Demo findDemoByDemo(String demo);
}
