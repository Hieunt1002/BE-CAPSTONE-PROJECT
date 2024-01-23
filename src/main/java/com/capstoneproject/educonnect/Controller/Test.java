package com.capstoneproject.educonnect.Controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/test")
public class Test {
	BCryptPasswordEncoder enocde = new BCryptPasswordEncoder();
	@GetMapping()
	public void BCryptPasswordEncoder () {
		System.out.println("password: "+enocde.encode("123456"));
	}
}
