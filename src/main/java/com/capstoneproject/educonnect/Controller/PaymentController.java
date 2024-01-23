package com.capstoneproject.educonnect.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capstoneproject.educonnect.DTO.BookLesson;
import com.capstoneproject.educonnect.DTO.BookingDTO;
import com.capstoneproject.educonnect.DTO.InforuserDTO;
import com.capstoneproject.educonnect.DTO.ListTimelineOfTutorDTO;
import com.capstoneproject.educonnect.DTO.PaymentDTO;
import com.capstoneproject.educonnect.DTO.TimeBookDTO;
import com.capstoneproject.educonnect.Entity.Timeline;
import com.capstoneproject.educonnect.Entity.lesson;
import com.capstoneproject.educonnect.Repository.SchedulesRepostory;
import com.capstoneproject.educonnect.Service.BookingService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/book")
public class PaymentController {

	private final JavaMailSender mailSender;

	@Autowired
	public PaymentController(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	@Autowired
	private BookingService bookingService;

	@Autowired
	private SchedulesRepostory schedulesRepostory;

	@GetMapping("/createpayment")
	public PaymentDTO getPay(@RequestParam int studentid) throws UnsupportedEncodingException {
		return bookingService.payment(studentid);
	}

	@GetMapping("/vnpay-return")
	public String handleVnpayReturn(@RequestParam(value = "vnp_Amount") String amount,
			@RequestParam(value = "vnp_BankCode") String BankCode,
			@RequestParam(value = "vnp_OrderInfo") String OrderInfo,
			@RequestParam(value = "vnp_ResponseCode") String ResponseCode) {
		String url = "";
		int id = Integer.parseInt(OrderInfo);
		int check = bookingService.check(id);
		if(ResponseCode.equals("00")) {
			if(check > 0) {
				bookingService.payDone(id);
			}
			url = "OK";
		}else {
			if(check > 0) {
				bookingService.deletetime(id);
				bookingService.cancelbook(id);
			}
			url = "NO";
		}
		return url;
	}
	
	@PostMapping("/banking")
	public void banking(@RequestParam int studentid, @RequestParam("file") MultipartFile file, @RequestParam String email) throws MessagingException {
		try {
			byte[] fileBytes = file.getBytes();
			MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setTo("nguyenanh0978638@gmail.com");
	        helper.setSubject("Hóa đơn");
	        helper.setText("Xin chào,\n\nBạn nhận được một hóa đơn đính kèm từ người dùng.");
	        helper.setFrom("capstoneeduconnect@gmail.com");
	        helper.addAttachment(file.getOriginalFilename(), new ByteArrayResource(fileBytes));
	        mailSender.send(message);
	        bookingService.banking(studentid, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// timeline của tutor
	@GetMapping("/timeline/{tutorid}")
	public List<ListTimelineOfTutorDTO> listTimelineOfTutor(@PathVariable int tutorid) {
		return bookingService.listTimelineOfTutor(tutorid);
	}

	@PostMapping("/bookcourse")
	public void bookcourse(@RequestBody BookingDTO bookingDTO) {
		bookingService.bookingCourse(bookingDTO);
	}

	@RequestMapping(value = "/timebook", method = RequestMethod.POST)
	public void timebook(@RequestBody TimeBookDTO timeBookDTO) {
		bookingService.timebookcourse(timeBookDTO);
	}
	
	@DeleteMapping("/deletetimeerror/{id}")
	public void deletetimeerror(@PathVariable("id") int studentid) {
		bookingService.deletetime(studentid);
	}

	@GetMapping("/paydone")
	public boolean paydone(@RequestParam int studentid) {
		bookingService.payDone(studentid);
		return true;
	}

	@GetMapping("/test")
	public List<InforuserDTO> test(@RequestParam int studentId, @RequestParam String date) {
		return schedulesRepostory.sendmailclosecousestudent(studentId, date);
	}

	@GetMapping("/timeline")
	public List<Timeline> listTimeline() {
		return bookingService.listTime();
	}

	@GetMapping("/timeandlesson")
	public List<BookLesson> timeandlesson(@RequestParam int tutorid, @RequestParam int studentid) {
		return bookingService.listtimeandlesson(tutorid, studentid);
	}
	
	@DeleteMapping("/cancelcardpay/{id}")
	public ResponseEntity<String> cancelcardpay(@PathVariable("id") int bookid){
		String message = "";
		try {
			bookingService.cancelcardpay(bookid);
			message = "Hủy thành công";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}catch (Exception e) {
			message = "Xin vui lòng liên hệ với IT EDU-CONNECT " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
	@GetMapping("/acceptcardpay/{id}")
	public ResponseEntity<String> acceptcardpay(@PathVariable("id") int bookid){
		String message = "";
		try {
			bookingService.acceptcardpay(bookid);
			message = "Bạn đã chấp nhận hóa đơn thành công";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}catch (Exception e) {
			message = "Không thể chấp nhận " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@GetMapping("/lesson")
	public List<lesson> lesson() {
		return bookingService.listlesson();
	}

	@DeleteMapping("/cancelbook")
	public void cancelbook(@RequestParam int studentid) {
		bookingService.cancelbook(studentid);
	}
}
