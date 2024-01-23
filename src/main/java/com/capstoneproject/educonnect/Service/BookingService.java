package com.capstoneproject.educonnect.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.Config.Config;
import com.capstoneproject.educonnect.DTO.BookLesson;
import com.capstoneproject.educonnect.DTO.BookingDTO;
import com.capstoneproject.educonnect.DTO.ChangeCalenderDTO;
import com.capstoneproject.educonnect.DTO.InforDTO;
import com.capstoneproject.educonnect.DTO.ListTimelineOfTutorDTO;
import com.capstoneproject.educonnect.DTO.PaymentDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTimelineTutorDTO;
import com.capstoneproject.educonnect.DTO.TimeBookDTO;
import com.capstoneproject.educonnect.Entity.BookingEntity;
import com.capstoneproject.educonnect.Entity.ChangeCalender;
import com.capstoneproject.educonnect.Entity.TimeBook;
import com.capstoneproject.educonnect.Entity.Timeline;
import com.capstoneproject.educonnect.Entity.lesson;
import com.capstoneproject.educonnect.Repository.BookTutorRepository;
import com.capstoneproject.educonnect.Repository.BookingRepository;
import com.capstoneproject.educonnect.Repository.ChangedateRepository;
import com.capstoneproject.educonnect.Repository.LessonRepository;
import com.capstoneproject.educonnect.Repository.TimeBookRepository;
import com.capstoneproject.educonnect.Repository.TimeLineRepository;

@Service
public class BookingService {

	private final JavaMailSender mailSender;

	@Autowired
	public BookingService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookTutorRepository bookTutorRepository;

	@Autowired
	private TimeBookRepository timeBookRepository;

	@Autowired
	private TimeLineRepository timeLineRepository;

	@Autowired
	private LessonRepository lessonRepository;

	@Autowired
	private ChangedateRepository changedateRepository;

	LocalDate today = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String dateString = today.format(formatter);

	LocalDate futureDate = today.plusDays(7);
	LocalDate futureDatePlus3Months = futureDate.plusMonths(3);
	String dateStrings = futureDate.format(formatter);
	String dayString = futureDatePlus3Months.format(formatter);

	public void bookingCourse(BookingDTO bookingDTO) {
		boolean result = bookTutorRepository.checkbook(bookingDTO.getStudentId());
		if (result == false) {
			bookTutorRepository.deletetimebook(bookingDTO.getStudentId());
			bookTutorRepository.cancelbook(bookingDTO.getStudentId());
			BookingEntity book = new BookingEntity();
			book.setStudentid(bookingDTO.getStudentId());
			book.setTutorid(bookingDTO.getTutorId());
			book.setStartDate(dateStrings);
			book.setEndDate(dayString);
			book.setClasscourseid(bookingDTO.getClassCourseId());
			book.setDateregister(dateString);
			book.setStatus(0);
			bookingRepository.save(book);
		} else {
			BookingEntity book = new BookingEntity();
			book.setStudentid(bookingDTO.getStudentId());
			book.setTutorid(bookingDTO.getTutorId());
			book.setStartDate(dateStrings);
			book.setEndDate(dayString);
			book.setClasscourseid(bookingDTO.getClassCourseId());
			book.setDateregister(dateString);
			book.setStatus(0);
			bookingRepository.save(book);
		}
	}

	public PaymentDTO payment(int studentid) throws UnsupportedEncodingException {

		float price = bookTutorRepository.pricecourse(studentid);
		String id = String.valueOf(studentid);

		long pricecours = (long) price;

		String vnp_Version = "2.1.0";
		String vnp_Command = "pay";
		String orderType = "other";
		long amount = pricecours * 100;
		String bankCode = "NCB";

		String vnp_TxnRef = Config.getRandomNumber(8);
		String vnp_IpAddr = "127.0.0.1";

		String vnp_TmnCode = Config.vnp_TmnCode;

		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", vnp_Version);
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");

		vnp_Params.put("vnp_BankCode", bankCode);
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", id);
		vnp_Params.put("vnp_OrderType", orderType);

		vnp_Params.put("vnp_Locale", "vn");
		vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

		// Lấy thời gian hiện tại và đặt vào trường vnp_CreateDate
		LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
		String vnp_CreateDate = currentDateTime.format(formatter);
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		LocalDateTime expireDateTime = currentDateTime.plusMinutes(15);
		String vnp_ExpireDate = expireDateTime.format(formatter);
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;

		String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setStatus("OK");
		paymentDTO.setMessage("Successfully");
		paymentDTO.setURL(paymentUrl);
		return paymentDTO;
	}

	public void timebookcourse(TimeBookDTO timeBookDTO) {
		int bookid = bookTutorRepository.bookid(timeBookDTO.getStudentid());
		TimeBook timeBook = new TimeBook();
		timeBook.setTimeid(timeBookDTO.getTimeId());
		timeBook.setBookid(bookid);
		timeBook.setLessonid(timeBookDTO.getLessonid());
		timeBookRepository.save(timeBook);
	}

	public void payDone(int studentid) {
		String mailTutor = bookTutorRepository.mailTutorByStudent(studentid);
		String mailstudent = bookTutorRepository.mailStudent(studentid);
		InforDTO infortutor = bookTutorRepository.inforstudent(studentid);
		InforDTO inforstudent = bookTutorRepository.infortutor(studentid);
		List<StaffViewProfileTimelineTutorDTO> slot = bookTutorRepository.learntimestudent(studentid);
		if (!mailstudent.isEmpty() || mailstudent != null && inforstudent != null) {
			String course = inforstudent.getCourse() + " " + inforstudent.getLop();
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(mailstudent);
			mailMessage.setFrom("EDU-CONNECT <capstoneeduconnect@gmail.com>");
			mailMessage.setSubject("Chúc mừng bạn đã đăng ký thành công khóa học " + inforstudent.getCourse()
					+ " " + inforstudent.getLop() + " tại EDU-CONNECT");
			mailMessage.setText(getStudentCancellationEmailContent(course, inforstudent.getFullname(), slot));
			mailSender.send(mailMessage);
		}
		if (!mailTutor.isEmpty() || mailTutor != null && infortutor != null) {
			String course = infortutor.getCourse() + " " + infortutor.getLop();
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(mailTutor);
			mailMessage.setFrom("EDU-CONNECT <capstoneeduconnect@gmail.com>");
			mailMessage.setSubject("Bạn vừa có học sinh đăng ký khóa học " + infortutor.getCourse() + " "
					+ infortutor.getLop() + "");
			mailMessage.setText(getTutorCancellationEmailContent(course, infortutor.getFullname(), slot));
			mailSender.send(mailMessage);
		}
		bookTutorRepository.payDone(studentid, 1);
	}

	public void banking(int studentid, int status) {
		bookTutorRepository.payDonebank(studentid, 2);
	}

	private String getStudentCancellationEmailContent(String course, String tutor,
			List<StaffViewProfileTimelineTutorDTO> tutorDTOList) {
		StringBuilder contentBuilder = new StringBuilder();
		contentBuilder.append("Chào em,\n\n")
				.append("EDU-CONNECT Cảm ơn bạn đã tham gia khóa học của EDU-CONNECT:\n\n")
				.append("Hiện tại ngày ").append(dateString)
				.append(" bạn đã đăng ký thành công với khóa học ").append(course).append(" .\n\n")
				.append("Dạy bởi gia sư: ").append(tutor).append(".\n\n").append("Với những tiết học:\n");

		for (StaffViewProfileTimelineTutorDTO tutorDTO : tutorDTOList) {
			contentBuilder.append("- ").append(tutorDTO.getTimeline()).append("\n");
		}

		contentBuilder.append(
				"\nCảm ơn bạn đã tin tưởng đến chúng tôi chúc bạn có một trải nghiệm tốt nhất.\n\n")
				.append("Trân trọng,\n").append("EDU-CONNECT");

		return contentBuilder.toString();
	}

	private String getTutorCancellationEmailContent(String course, String student,
			List<StaffViewProfileTimelineTutorDTO> tutorDTOList) {
		StringBuilder contentBuilder = new StringBuilder();
		contentBuilder.append("Chào em,\n\n").append("EDU-CONNECT Thông báo bạn vừa có một học sinh mới:\n\n")
				.append("Hiện tại ngày ").append(dateString)
				.append(" bạn đã có một học sinh đăng ký bạn với khóa học ").append(course).append(" .\n\n")
				.append("Tên học sinh: ").append(student).append(".\n\n").append("Với những tiết học:\n");

		for (StaffViewProfileTimelineTutorDTO tutorDTO : tutorDTOList) {
			contentBuilder.append("- ").append(tutorDTO.getTimeline()).append("\n");
		}

		contentBuilder.append(
				"\nCảm ơn bạn đã tin tưởng đến chúng tôi chúc bạn có một trải nghiệm tốt nhất.\n\n")
				.append("Trân trọng,\n").append("EDU-CONNECT");

		return contentBuilder.toString();
	}

	public List<ListTimelineOfTutorDTO> listTimelineOfTutor(int tutorid) {
		List<ListTimelineOfTutorDTO> result = bookTutorRepository.listTimelineOfTutor(tutorid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public int check(int studentid) {
		int check = bookTutorRepository.bookid(studentid);
		return check;
	}

	public void deletetime(int studentid) {
		bookTutorRepository.deletetimebook(studentid);
	}

	public List<Timeline> listTime() {
		return timeLineRepository.findAll();
	}

	public List<BookLesson> listtimeandlesson(int tutorid, int studentid) {
		return bookTutorRepository.listtimeandlesson(tutorid, studentid);
	}

	public void acceptcardpay(int bookid) {
		String mailTutor = bookTutorRepository.mailTutorByStudent(bookid);
		String mailstudent = bookTutorRepository.mailStudent(bookid);
		InforDTO infortutor = bookTutorRepository.inforstudent(bookid);
		InforDTO inforstudent = bookTutorRepository.infortutor(bookid);
		List<StaffViewProfileTimelineTutorDTO> slot = bookTutorRepository.learntimestudent(bookid);
		if (!mailstudent.isEmpty() || mailstudent != null && inforstudent != null) {
			String course = inforstudent.getCourse() + " " + inforstudent.getLop();
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(mailstudent);
			mailMessage.setFrom("EDU-CONNECT <capstoneeduconnect@gmail.com>");
			mailMessage.setSubject("Chúc mừng bạn đã đăng ký thành công khóa học " + inforstudent.getCourse()
					+ " " + inforstudent.getLop() + " tại EDU-CONNECT");
			mailMessage.setText(getStudentCancellationEmailContent(course, inforstudent.getFullname(), slot));
			mailSender.send(mailMessage);
		}
		if (!mailTutor.isEmpty() || mailTutor != null && infortutor != null) {
			String course = infortutor.getCourse() + " " + infortutor.getLop();
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(mailTutor);
			mailMessage.setFrom("EDU-CONNECT <capstoneeduconnect@gmail.com>");
			mailMessage.setSubject("Bạn vừa có học sinh đăng ký khóa học " + infortutor.getCourse() + " "
					+ infortutor.getLop() + "");
			mailMessage.setText(getTutorCancellationEmailContent(course, infortutor.getFullname(), slot));
			mailSender.send(mailMessage);
		}
		bookTutorRepository.acceptcardpaymet(bookid);
	}

	public List<lesson> listlesson() {
		return lessonRepository.findAll();
	}

	public void cancelcardpay(int bookid) {
		bookTutorRepository.cancelcardpay(bookid);
	}

	public void cancelbook(int studentid) {
		bookTutorRepository.deletetimebook(studentid);
		bookTutorRepository.cancelbook(studentid);
	}

	public void changecalender(ChangeCalenderDTO changeCalenderDTO) {
		LocalDate dateToCheck = LocalDate.parse(changeCalenderDTO.getDate());
		DayOfWeek dayOfWeek = dateToCheck.getDayOfWeek();
		ChangeCalender changeCalender = new ChangeCalender();
		changeCalender.setBookid(changeCalenderDTO.getBookid());
		changeCalender.setDatechange(changeCalenderDTO.getDatechange());
		changeCalender.setDatelearn(changeCalenderDTO.getDate());
		changeCalender.setTimeid(changeCalenderDTO.getTimeid());
		changeCalender.setTimechange(changeCalenderDTO.getTime());
		int date = dayOfWeek.getValue() + 1;
		if(date == 8) {
			changeCalender.setLessonline("Chủ nhật");
		}else {
			changeCalender.setLessonline("Thứ " + date);
		}
		changedateRepository.save(changeCalender);
		String emailStudent = bookTutorRepository.emailStudent(changeCalenderDTO.getBookid());
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(emailStudent);
		mailMessage.setFrom("EDU-CONNECT");
		mailMessage.setSubject("Thông báo nghỉ học");
		mailMessage.setText(changecalenderStudent(changeCalenderDTO.getSubject(), changeCalenderDTO.getNametutor(),
				changeCalenderDTO.getTime(), changeCalenderDTO.getLesson(), changeCalenderDTO.getDatechange(),
				changeCalenderDTO.getDatechange()));
		mailSender.send(mailMessage);
		String tutor = bookTutorRepository.emailTutor(changeCalenderDTO.getBookid());
		SimpleMailMessage mailMessages = new SimpleMailMessage();
		mailMessages.setTo(tutor);
		mailMessages.setFrom("EDU-CONNECT");
		mailMessages.setSubject("Thông báo nghỉ học");
		mailMessages.setText(changecalenderTutor(changeCalenderDTO.getSubject(), changeCalenderDTO.getTime(),
				changeCalenderDTO.getLesson(), changeCalenderDTO.getDatechange(), changeCalenderDTO.getDatechange()));
		mailSender.send(mailMessages);
	}

	private String changecalenderStudent(String subject, String nametutor, String time, String lesson, String date,
			String datechange) {
		return "Chào em,\n\n" + "EDU-CONNECT Thông báo :\n\n" + "Hiện tại ngày " + date
				+ " bạn đã có một tiết học " + subject + " .\n\n" + "Tên gia sư: " + nametutor + ".\n\n"
				+ "Với những tiết học " + time + ", Thứ " + lesson + " .\n\n"
				+ "Bạn được nghỉ học bởi gia sư bận việc đột xuất lịch học của bạn sẽ sắp xếp vào ngày " + datechange
				+ ".\n\n" + "Vậy vui lòng bạn bỏ qua thông báo học của tiết này.\n\n" + "Trân trọng,\n" + "EDU-CONNECT";
	}

	private String changecalenderTutor(String subject, String time, String lesson, String date, String datechange) {
		return "Chào em,\n\n" + "EDU-CONNECT Thông báo :\n\n" + "Hiện tại ngày " + date
				+ " bạn đã đăng ký nghỉ học với khóa học " + subject + " .\n\n" + "Với những tiết học " + time
				+ ", Thứ " + lesson + " .\n\n" + "Bạn đã đăng ký thành công và tiết học này được học vào ngày "
				+ datechange + ".\n\n" + "Vậy vui lòng bạn bỏ qua thông báo học của tiết này.\n\n" + "Trân trọng,\n"
				+ "EDU-CONNECT";
	}

	public void deletecalender(int timeid, int lessonid, int tutorid) {
		bookTutorRepository.deletecalender(timeid, lessonid, tutorid);
	}
}
