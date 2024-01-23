package com.capstoneproject.educonnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.ChangePasswordDTO;
import com.capstoneproject.educonnect.Entity.User;
import com.capstoneproject.educonnect.Repository.UserRepository;
import com.capstoneproject.educonnect.Repository.UserRepositoryT;

@Service
public class UserServiceT {

	@Autowired
	private UserRepositoryT userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository uRepository;

	public void ChangePassword(String email, String pass) {
		userRepository.ChangePassword(email, pass);
	}
	
	public boolean checkpass(int userid, String pass) {
        String encodedPassword = passwordEncoder.encode(pass);
        String result = userRepository.checkpass(userid, encodedPassword);
        if(!result.isEmpty()) {
        	return true;
        }else {
        	return false;
        }
	}

	public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
		User customer = uRepository.login(email);
		if (customer != null) {
			customer.setResetPasswordToken(token);
			uRepository.save(customer);
		} else {
			throw new UsernameNotFoundException("Could not find any customer with the email " + email);
		}
	}
	public User getByResetPasswordToken(String token) {
        return uRepository.findByResetPasswordToken(token);
    }
	
	public void updatePassword(User customer, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(encodedPassword);
         
        customer.setResetPasswordToken(null);
        uRepository.save(customer);
    }
}
