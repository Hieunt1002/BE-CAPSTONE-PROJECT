package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.User;

@Repository
public interface AddUserRepository extends JpaRepository<User,Integer>{

}
