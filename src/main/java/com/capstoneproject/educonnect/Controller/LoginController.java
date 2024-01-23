package com.capstoneproject.educonnect.Controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.capstoneproject.educonnect.DTO.ChangepassDTO;
import com.capstoneproject.educonnect.Entity.Student;
import com.capstoneproject.educonnect.Entity.Tutor;
import com.capstoneproject.educonnect.Entity.User;
import com.capstoneproject.educonnect.Repository.StudentRepository;
import com.capstoneproject.educonnect.Repository.TutorBatchRepository;
import com.capstoneproject.educonnect.Repository.UserRepository;
import com.capstoneproject.educonnect.Security.AuthenticateRequest;
import com.capstoneproject.educonnect.Security.AuthenticateResponse;
import com.capstoneproject.educonnect.Security.AuthenticateTokenUtil;
import com.capstoneproject.educonnect.Service.FileService;
import com.capstoneproject.educonnect.Service.MyUserDetailsService;
import com.capstoneproject.educonnect.Service.UserServiceT;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;

@RestController
@RequestMapping("/edu")
@AllArgsConstructor
public class LoginController {

	private final FileService fileService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthenticateTokenUtil authenticateTokenUtil;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserServiceT userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TutorBatchRepository tutorBatchRepository;

	private final JavaMailSender mailSender;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticateRequest authenticateRequest)
			throws Exception {
		try {
			authenticate(authenticateRequest.getUsername(), authenticateRequest.getPassword());

			final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticateRequest.getUsername());

			User userDto = userRepository.login(authenticateRequest.getUsername());

			final String token = authenticateTokenUtil.generateToken(userDetails, userDto.getRole(), userDto.getUserid());

			return ResponseEntity.ok(new AuthenticateResponse(token, "Đăng nhập thành công"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthenticateResponse(null, "Tài khoản hoặc mật khẩu của bạn không chính xác!"));
		}
	}

	@PutMapping("/changepass")
	public ResponseEntity<?> changepass(@RequestBody ChangepassDTO authenticateRequest) {
		try {
			authenticate(authenticateRequest.getUsername(), authenticateRequest.getPassword());
			userService.ChangePassword(authenticateRequest.getUsername(),
					passwordEncoder.encode(authenticateRequest.getNewpass()));
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (DisabledException e) {
			return new ResponseEntity<>("User is disabled.", HttpStatus.UNAUTHORIZED);
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>("Invalid credentials.", HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestParam String fullname, @RequestParam String email,
			@RequestParam String password, @RequestParam String phone, @RequestParam int role,
			@RequestParam int classentity, @RequestPart(value = "file", required = false) MultipartFile file) throws UnsupportedEncodingException, MessagingException {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateString = today.format(formatter);
		if (userRepository.existsByEmail(email)) {
			return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user.setFullname(fullname);
		user.setPhone(phone);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode((password)));
		user.setRole(role);
		int status = role == 1 ? 1 : 3;
		user.setStatus(status);
		user.setCreatedate(dateString);
		userRepository.save(user);
		User users = userRepository.logincheck(email);
		if (role == 2) {
			String message = "";
			try {
				Tutor tutor = new Tutor();
				fileService.saveUser(file, users.getUserid());
				tutor.setTutorId(users.getUserid());
				tutor.setUser(users);
				tutor.setCv(file.getOriginalFilename());
				tutor.setSalary(0f);
				tutor.setExperience(0);
				tutor.setStaffid(2);
				tutor.setBirthdate(dateString);
				tutorBatchRepository.save(tutor);
				weelcometutor(email);
				message = "User registered success!";
				return new ResponseEntity<>(message, HttpStatus.OK);
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
				return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
			}
		} else if (role == 1) {
			Student student = new Student();
			student.setStudentid(users.getUserid());
			student.setUser(users);
			student.setClassId(classentity);
			student.setBirthdate(dateString);
			studentRepository.save(student);
			weelcomestudent(email);
		}
		return new ResponseEntity<>("User registered success!", HttpStatus.OK);
	}

	@GetMapping("/checkmail")
	public boolean checkmail(String email) {
		User users = userRepository.login(email);
		if (users == null) {
			return false;
		} else {
			return true;
		}
	}

	@PostMapping("/forgotpassword")
	public ResponseEntity<String> forgotpassword(@RequestParam String email)
			throws UnsupportedEncodingException, MessagingException {
		String token = RandomString.make(30);
		userService.updateResetPasswordToken(token, email);
		String resetPasswordLink = "http://educonnect.site" + "/resetpassword/" + token;
		sendEmail(email, resetPasswordLink);
		return new ResponseEntity<>("success!", HttpStatus.OK);
	}

	@PutMapping("/resetpassword")
	public ResponseEntity<String> resetpassword(String token, String password) {
		User user = userService.getByResetPasswordToken(token);
		if (user == null) {
			return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
		} else {
			userService.updatePassword(user, password);
			return new ResponseEntity<>("success!", HttpStatus.OK);
		}
	}

	@GetMapping("/checktoken")
	public boolean checktoken(String token) {
		User user = userService.getByResetPasswordToken(token);
		if (user == null) {
			return false;
		}
		return true;
	}

	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("capstoneeduconnect@gmail.com", "EDU-CONNECT");
		helper.setTo(recipientEmail);

		String subject = "Đây là liên kết để đặt lại mật khẩu của bạn";

		String content = "<p>Xin chào bạn,</p>" + "<p>Bạn đã yêu cầu đặt lại mật khẩu của mình.</p>"
				+ "<p>Nhấp vào liên kết bên dưới để thay đổi mật khẩu của bạn:</p>" + "<p><a href=\"" + link
				+ "\">Thay đổi mật khẩu của tôi</a></p>" + "<br>"
				+ "<p>Bỏ qua email này nếu bạn nhớ mật khẩu của mình, " + "hoặc bạn chưa thực hiện yêu cầu.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}
	
	public void weelcomestudent(String recipientEmail) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("capstoneeduconnect@gmail.com", "EDU-CONNECT");
		helper.setTo(recipientEmail);

		String subject = "Chào mừng bạn đến với EDU-CONNECT";

		String content = "<p>Xin chào học sinh,</p>"
			    + "<p>Chào mừng bạn đến với EDU-CONNECT!</p>"
			    + "<p>Chúng tôi rất vui mừng được chào đón bạn trở thành thành viên của chúng tôi.</p>"
			    + "<p>EDU-CONNECT là một nền tảng giáo dục trực tuyến nổi tiếng, chúng tôi cam kết cung cấp cho bạn trải nghiệm học tập tốt nhất và đáng tin cậy.</p>"
			    + "<p>Hãy sẵn sàng khám phá các khóa học thú vị, tham gia vào cộng đồng học tập và trò chuyện với các giảng viên và bạn bè!</p>"
			    + "<br>"
			    + "<p>Trân trọng,</p>"
			    + "<p>EDU-CONNECT Team</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}
	
	public void weelcometutor(String recipientEmail) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("capstoneeduconnect@gmail.com", "EDU-CONNECT");
		helper.setTo(recipientEmail);

		String subject = "Chào mừng bạn đến với EDU-CONNECT";

		String content = "<p>Xin chào gia sư,</p>"
			    + "<p>Chào mừng bạn đến với EDU-CONNECT!</p>"
			    + "<p>Chúng tôi rất vui mừng được chào đón bạn trở thành một gia sư trong đội ngũ của chúng tôi.</p>"
			    + "<p>EDU-CONNECT là một nền tảng giáo dục trực tuyến nổi tiếng, và bạn sẽ có cơ hội chia sẻ kiến thức và kinh nghiệm của mình với các học viên.</p>"
			    + "<p>Chúng tôi sẽ gửi cho bạn các thông báo và cập nhật quan trọng qua email, vì vậy hãy nhớ kiểm tra mail thường xuyên để không bỏ lỡ bất kỳ thông tin nào từ chúng tôi.</p>"
			    + "<p>Trong thời gian tới, chúng tôi sẽ gửi cho bạn thông tin về các buổi phỏng vấn và các khóa huấn luyện sẽ diễn ra. Hãy chuẩn bị sẵn sàng cho các bước tiếp theo!</p>"
			    + "<br>"
			    + "<p>Trân trọng,</p>"
			    + "<p>EDU-CONNECT Team</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}
}
