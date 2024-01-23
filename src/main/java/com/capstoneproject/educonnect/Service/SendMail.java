package com.capstoneproject.educonnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SendMail {
	
	private final JavaMailSender mailSender;
	
	@Autowired
    public SendMail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
	
	public void senmail(String mail, String subject, String text) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailSender.send(mailMessage);
	}
}
