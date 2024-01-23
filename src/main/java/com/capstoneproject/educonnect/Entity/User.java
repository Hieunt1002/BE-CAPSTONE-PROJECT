package com.capstoneproject.educonnect.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.capstoneproject.educonnect.DTO.UserDTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Setter
@Getter
@RequiredArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;

	@Column(name = "fullname" ,columnDefinition = "NVARCHAR(200)")
	private String fullname;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "phone")
	private String phone;

	@Column(name = "createdate", columnDefinition = "DATE")
	private String createdate;

	@Column(name = "gender", columnDefinition = "BOOL")
	private int gender;

	@Column(name = "role")
	private int role;

	@Column(name = "status")
	private int status;
	
	@Column(name = "reset_password_token")
    private String resetPasswordToken;

	@OneToOne(cascade = CascadeType.ALL, optional = true, mappedBy = "user")
	private Student student;

	@OneToOne(cascade = CascadeType.ALL, optional = true, mappedBy = "user")
	private Tutor tutor;

	@OneToOne(cascade = CascadeType.ALL, optional = true, mappedBy = "user")
	private Staff staff;
	
	
	public User setEntity(UserDTO userDTO) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		this.setFullname(userDTO.getFullname());
		this.setEmail(userDTO.getEmail());
		this.setPhone(userDTO.getPhone());
		this.setGender(userDTO.getGender());
		this.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		this.setCreatedate(userDTO.getCreatedate());

		return this;

	}
}
