package com.capstoneproject.educonnect.Repository.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstoneproject.educonnect.DTO.UserDTO;
import com.capstoneproject.educonnect.Entity.User;
import com.capstoneproject.educonnect.Error.EmailAlreadyExistException;
import com.capstoneproject.educonnect.Repository.UserRepository;
import com.capstoneproject.educonnect.Service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
	
	private final UserRepository userRepository;

	@Override
	public UserDTO login(String email) {
		User user = userRepository.login(email);
		if (user != null) {
			return UserDTO.builder().email(user.getEmail()).fullname(user.getFullname()).phone(user.getPhone())
					.gender(user.getGender()).role(user.getRole()).status(user.getStatus()).password(user.getPassword()).build();
		} else {
			return null;
		}

	}

	@Override
	public User register(UserDTO userDTO) {
		User user = userRepository.login(userDTO.getEmail());
		if (user != null) {
			throw new EmailAlreadyExistException("There is an account with that email address: " + userDTO.getEmail());
		}
		User userRegister = new User();
		userRegister.setEntity(userDTO);
		return userRepository.save(userRegister);

	}

	public UserDetails findById(int userId) {
		Optional<User> user = userRepository.findById(userId);
		UserDetails userDetails = (UserDetails) user.get();
		return userDetails;
	}

}
