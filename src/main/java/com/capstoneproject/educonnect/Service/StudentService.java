package com.capstoneproject.educonnect.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.DetailFeedbackDTO;
import com.capstoneproject.educonnect.DTO.FeedBackDetailDTO;
import com.capstoneproject.educonnect.DTO.FeedbackDTO;
import com.capstoneproject.educonnect.DTO.ListAllFeedbackOfTutorDTO;
import com.capstoneproject.educonnect.DTO.ListFeedbackBookingOfTutorDTO;
import com.capstoneproject.educonnect.DTO.ListFeedbackDTO;
import com.capstoneproject.educonnect.DTO.ListFeedbackTrylearningOfTutorDTO;
import com.capstoneproject.educonnect.DTO.LopDTO;
import com.capstoneproject.educonnect.DTO.StudentDTO;
import com.capstoneproject.educonnect.DTO.StudentProfileDTO;
import com.capstoneproject.educonnect.DTO.UFeedbackDTO;
import com.capstoneproject.educonnect.Entity.BookingEntity;
import com.capstoneproject.educonnect.Entity.ClassEntity;
import com.capstoneproject.educonnect.Entity.Feedback;
import com.capstoneproject.educonnect.Repository.ClassEntityRepository;
import com.capstoneproject.educonnect.Repository.FeedbackRepository;
import com.capstoneproject.educonnect.Repository.StudentRepositoryT;

@Service
public class StudentService {
	@Autowired
	private StudentRepositoryT studentRepository;

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private ClassEntityRepository classEntityRepository;

	public StudentProfileDTO listStudent(int email) {
		StudentProfileDTO result = studentRepository.listInfomationStudent(email);
		if(result == null) {
			return null;
		}
		return result;
	}

	public void UpdateStudent(String fullname, int studentid,
			String file, int gender, String birthdate, String phone, String city,
			String wards, int classentity) {
		studentRepository.UpdateStudent(fullname, studentid, file, gender, birthdate, phone, city, wards, classentity);;
	}

	public List<ListFeedbackDTO> listFeedback(int studentid) {
		List<ListFeedbackDTO> result = studentRepository.listFeedback(studentid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public List<ListFeedbackDTO> listFeedbacktutor(int studentid) {
		List<ListFeedbackDTO> result = studentRepository.listFeedbacktutor(studentid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	// add feedback
	public void addFeedback(FeedbackDTO feedbackDTO) {
		Feedback feedback = new Feedback();
		BookingEntity bookingEntity = studentRepository.detailBookingDTO(feedbackDTO.getFeedbackid());
		feedback.setBooking(bookingEntity);
		feedback.setFeedbackid(bookingEntity.getBookId());
		feedback.setNotes(feedbackDTO.getNotes());
		feedback.setRanks(feedbackDTO.getRanks());
		feedbackRepository.save(feedback);
	}
	
	//detai feedback
	public DetailFeedbackDTO detailFeedbackDTO(int feedbackd, int studentid) {
		DetailFeedbackDTO result = studentRepository.detailFeedbackDTO(feedbackd, studentid);
		if(result == null) {
			return null;
		}else {
			return result;
		}
	}
	
	//update feedback
	public void updateFeedback(UFeedbackDTO uFeedbackDTO) {
		studentRepository.updateFeedback(uFeedbackDTO);
	}
	
	public FeedBackDetailDTO detailfeedback(int feedback){
		return studentRepository.detailfeedback(feedback);
	}
	
	public List<LopDTO> listClass(){
		return studentRepository.listclass();
	}
	
	public List<ListFeedbackBookingOfTutorDTO> listFeedbackBookingOfTutor(int tutorid) {
		List<ListFeedbackBookingOfTutorDTO> result = studentRepository.listFeedbackBookingOfTutor(tutorid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	public List<ClassEntity> listclass(){
		List<ClassEntity> result = classEntityRepository.findAll();
		return result;
	}
	public boolean checkstudent(int studentid) {
		return studentRepository.checkstudent(studentid);
	}
	
	public StudentDTO getStudentByBookId(int bookid){
		return studentRepository.getStudentByBookId(bookid);
	}
}
