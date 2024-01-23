package com.capstoneproject.educonnect.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class PaymentDTO implements Serializable {

	private String status;
	private String message;
	private String URL;
}
