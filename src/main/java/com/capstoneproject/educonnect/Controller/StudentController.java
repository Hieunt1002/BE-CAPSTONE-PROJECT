package com.capstoneproject.educonnect.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.capstoneproject.educonnect.DTO.DetailFeedbackDTO;
import com.capstoneproject.educonnect.DTO.FeedBackDetailDTO;
import com.capstoneproject.educonnect.DTO.FeedbackDTO;
import com.capstoneproject.educonnect.DTO.ListFeedbackBookingOfTutorDTO;
import com.capstoneproject.educonnect.DTO.ListFeedbackDTO;
import com.capstoneproject.educonnect.DTO.LopDTO;
import com.capstoneproject.educonnect.DTO.StudentDTO;
import com.capstoneproject.educonnect.DTO.StudentProfileDTO;
import com.capstoneproject.educonnect.DTO.UFeedbackDTO;
import com.capstoneproject.educonnect.Entity.ClassEntity;
import com.capstoneproject.educonnect.Service.FileService;
import com.capstoneproject.educonnect.Service.StudentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

	private final FileService fileService;
	
	@Autowired
	private StudentService studentService;

	@GetMapping("/viewstudent")
	public StudentProfileDTO listInfomationStudent(@RequestParam int email) {
		return studentService.listStudent(email);
	}

	@PutMapping("/updatestudent")
	public void UpdateStudent(@RequestParam String fullname, @RequestParam int studentid,
			@RequestPart(value = "file", required = false) MultipartFile file, @RequestParam int gender,
			@RequestParam String birthdate, @RequestParam String phone, @RequestParam String city,
			@RequestParam String wards, @RequestParam int classentity) {
		if(file == null || file.isEmpty()) {
			StudentProfileDTO student = studentService.listStudent(studentid);
			studentService.UpdateStudent(fullname, studentid, student.getImg() == null ? null : student.getImg(), gender, birthdate, phone, city, wards, classentity);
		}else {
			StudentProfileDTO student = studentService.listStudent(studentid);
			if(student.getImg() == null) {
				fileService.saveUser(file, studentid);
				studentService.UpdateStudent(fullname, studentid, file.getOriginalFilename(), gender, birthdate, phone, city, wards, classentity);
			}else {
				fileService.deleteUser(student.getImg(), studentid);
				fileService.saveUser(file, studentid);
				studentService.UpdateStudent(fullname, studentid, file.getOriginalFilename(), gender, birthdate, phone, city, wards, classentity);
			}
		}
	}

	@GetMapping("/feedback/{id}")
	public List<ListFeedbackDTO> listFeedback(@PathVariable("id") int studentid) {
		return studentService.listFeedback(studentid);
	}
	
	@GetMapping("/detailfeeback/{id}")
	public FeedBackDetailDTO detailfeeback(@PathVariable("id") int feebackid) {
		return studentService.detailfeedback(feebackid);
	}
	
	@GetMapping("/feedbacktutor/{id}")
	public List<ListFeedbackDTO> listFeedbacktutor(@PathVariable("id") int studentid) {
		return studentService.listFeedbacktutor(studentid);
	}
	
	@GetMapping("/checkstudent")
	public boolean checkstudent(@RequestParam int studentid) {
		return studentService.checkstudent(studentid);
	}

	// add feedback
	@PostMapping("/addfeedback")
	public void addfeedback(@RequestBody FeedbackDTO feedbackDTO) {
		studentService.addFeedback(feedbackDTO);
	}

	// detail feedback
	@GetMapping("/feedback/detail/{feedbackid}/{studentid}")
	public DetailFeedbackDTO detailFeedbackDTO(
			@PathVariable("feedbackid") int feedbackid, 
			@PathVariable("studentid") int studentid) {
		return studentService.detailFeedbackDTO(feedbackid, studentid);
	}

	// update feedback
	@PutMapping("/feedback/update")
	public void updateFeedback(@RequestBody UFeedbackDTO uFeedbackDTO) {
		studentService.updateFeedback(uFeedbackDTO);
	}
	
	@GetMapping("/class")
	public List<LopDTO> listClass(){
		return studentService.listClass();
	}
	
	@GetMapping("/feedbackbooking/{tutorid}")
	public List<ListFeedbackBookingOfTutorDTO> listFeedbackBookingOfTutor(@PathVariable("tutorid") int tutorid) {
		return studentService.listFeedbackBookingOfTutor(tutorid);
	}
	
	@GetMapping("/listclass")
	public List<ClassEntity> listclass(){
		return studentService.listclass();
	}
	
	@GetMapping("/getStudentByBookId")
	public StudentDTO getStudentByBookId(@RequestParam int bookid){
		return studentService.getStudentByBookId(bookid);
	}
}
