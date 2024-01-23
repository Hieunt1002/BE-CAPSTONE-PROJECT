package com.capstoneproject.educonnect.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.capstoneproject.educonnect.DTO.MyUserDetailsDTO;
import com.capstoneproject.educonnect.DTO.UserDTO;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDTO userDto = userService.login(username);

		if (userDto == null) {
			throw new UsernameNotFoundException("User name not found");
		} else {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(String.valueOf(userDto.getRole()));
			authorities.add(authority);
			MyUserDetailsDTO userDetail = new MyUserDetailsDTO(userDto.getEmail(),userDto.getPassword(), authorities);
			return userDetail;
		}
		
	}

}
