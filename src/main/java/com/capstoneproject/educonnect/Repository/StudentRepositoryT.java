package com.capstoneproject.educonnect.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.DetailFeedbackDTO;
import com.capstoneproject.educonnect.DTO.FeedBackDetailDTO;
import com.capstoneproject.educonnect.DTO.ListFeedbackBookingOfTutorDTO;
import com.capstoneproject.educonnect.DTO.ListFeedbackDTO;
import com.capstoneproject.educonnect.DTO.LopDTO;
import com.capstoneproject.educonnect.DTO.StudentDTO;
import com.capstoneproject.educonnect.DTO.StudentProfileDTO;
import com.capstoneproject.educonnect.DTO.UFeedbackDTO;
import com.capstoneproject.educonnect.Entity.BookingEntity;


@Repository
public interface StudentRepositoryT {
	StudentProfileDTO listInfomationStudent(int email);

	public void UpdateStudent(String fullname, int studentid,
			String file, int gender, String birthdate, String phone, String city,
			String wards, int classentity);
	
	List<ListFeedbackDTO> listFeedback(int studentid);
	
	List<ListFeedbackDTO> listFeedbacktutor(int studentid);
	
	FeedBackDetailDTO detailfeedback(int feedback);
	
	BookingEntity detailBookingDTO(int bookid);
	
	DetailFeedbackDTO detailFeedbackDTO(int feedbackid, int studentid);
	
	public void updateFeedback(UFeedbackDTO uFeedbackDTO);
	
	public List<LopDTO> listclass();
	
	List<ListFeedbackBookingOfTutorDTO> listFeedbackBookingOfTutor(int tutorid);
	
	boolean checkstudent(int studentid);
	
	StudentDTO getStudentByBookId(int bookid);
	
}
