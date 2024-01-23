package com.capstoneproject.educonnect.Repository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryT {

	public void ChangePassword(String email, String pass);
	
	public String checkpass(int id, String pass);
}
