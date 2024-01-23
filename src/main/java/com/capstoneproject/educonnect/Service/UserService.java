package com.capstoneproject.educonnect.Service;

import com.capstoneproject.educonnect.DTO.UserDTO;
import com.capstoneproject.educonnect.Entity.User;

public interface UserService {
	public UserDTO login(String email);
	public User register(UserDTO userDTO);
}
