package com.capstoneproject.educonnect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.educonnect.Service.UserServiceT;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceT userService;

	@PutMapping("/changepassword")
	public void ChangePassword(@RequestParam String userid, @RequestParam String pass) {
		userService.ChangePassword(userid, pass);
	}
	
	@GetMapping("/checkpass")
	public boolean checkpass(@RequestParam int userid,@RequestParam String pass) {
		return userService.checkpass(userid, pass);
	}
}
