package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Boolean existsByEmail(String username);
	@Query("SELECT u FROM User u WHERE u.email = :email AND u.status = 1")
	public User login(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User logincheck(@Param("email") String email);
	
	public User findByResetPasswordToken(String token);
	
	public User findUserByEmail(String email);
}
