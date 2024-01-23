package com.capstoneproject.educonnect.Service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.BookTutorDTO;
import com.capstoneproject.educonnect.DTO.ChoicetimeDTO;
import com.capstoneproject.educonnect.DTO.CourseAllByTutor;
import com.capstoneproject.educonnect.DTO.CourseOfTutorDTO;
import com.capstoneproject.educonnect.DTO.DemoDetailOfClasscourseDTO;
import com.capstoneproject.educonnect.DTO.DemoFollowClasscourseDTO;
import com.capstoneproject.educonnect.DTO.DescListAllTutorDTO;
import com.capstoneproject.educonnect.DTO.DetailSubmitOfHomeworkDTO;
import com.capstoneproject.educonnect.DTO.ListClassroomByTutorDTO;
import com.capstoneproject.educonnect.DTO.ListExerciseByTutorDTO;
import com.capstoneproject.educonnect.DTO.ListFeedbackBookingOfTutorDTO;
import com.capstoneproject.educonnect.DTO.ListStudentFinishedOfTutorDTO;
import com.capstoneproject.educonnect.DTO.ListStudentOfTutorDTO;
import com.capstoneproject.educonnect.DTO.PayForTutorDTO;
import com.capstoneproject.educonnect.DTO.ShowDetailHomeworkDTO;
import com.capstoneproject.educonnect.DTO.ShowDetailVideoDTO;
import com.capstoneproject.educonnect.DTO.StudentViewDetailTutorDTO;
import com.capstoneproject.educonnect.DTO.Top3TutorDTO;
import com.capstoneproject.educonnect.DTO.TutorForStudentDTO;
import com.capstoneproject.educonnect.DTO.TutorScorePointsForSubmitDTO;
import com.capstoneproject.educonnect.DTO.TutorViewProfileStudentDTO;
import com.capstoneproject.educonnect.DTO.TutorViewProfileStudentFinishedDTO;
import com.capstoneproject.educonnect.DTO.TutorViewProfileTimelineStudentDTO;
import com.capstoneproject.educonnect.DTO.VTutorExerciseDTO;
import com.capstoneproject.educonnect.DTO.ViewScreenTutorDTO;
import com.capstoneproject.educonnect.DTO.ViewTutorDTO;
import com.capstoneproject.educonnect.Entity.Payment;
import com.capstoneproject.educonnect.Entity.TeachTime;
import com.capstoneproject.educonnect.Repository.PaymentTutorRepository;
import com.capstoneproject.educonnect.Repository.TeachTimeRepository;
import com.capstoneproject.educonnect.Repository.TutorRepository;
import com.capstoneproject.educonnect.Repository.Impl.PaymentRepository;

@Service
public class TutorService {

	@Autowired
	private TutorRepository tutorRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private TeachTimeRepository teachTimeRepository;
	
	@Autowired
	private PaymentTutorRepository paymentTutorRepository;

	public List<Top3TutorDTO> listTop3Tutor() {
		return tutorRepository.findTop3Tutors();
	}

	public VTutorExerciseDTO viewtutor(int classcourseid, int tutorid) {
		return tutorRepository.viewtutor(classcourseid, tutorid);
	}
	
	public ViewTutorDTO listViewTutor(int tutorId){
		return tutorRepository.findViewTutor(tutorId);
	}
	
	public List<ViewScreenTutorDTO> ViewScreenTutor(int tutorid){
		return tutorRepository.ViewScreenTutor(tutorid);
	}
	
	public void UpdateTutor(int tutorid, String fullname, String phone,
			String file, int gender, String birthdate, String city, String wards) {
		tutorRepository.UpdateTutor(tutorid, fullname, phone, file, gender, birthdate, city, wards);;
  }
	public List<TutorForStudentDTO> TutorForStudent(int tutorid){
		return tutorRepository.TutorForStudent(tutorid);
	}
	
	public List<DescListAllTutorDTO> DescListAllTutor(int courseid, int page){
		int pages = (page - 1) * 5;
		return tutorRepository.DescListAllTutor(courseid, pages);
	}

	public List<CourseAllByTutor> AllCourseByTutor(int tutorid) {
		List<CourseAllByTutor> result = tutorRepository.AllCourseByTutor(tutorid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public ShowDetailHomeworkDTO showDetailHomework(int homeworkid) {
		ShowDetailHomeworkDTO showDetailHomeworkDTO = tutorRepository.showDetailHomework(homeworkid);
		return showDetailHomeworkDTO;
	}
	public DetailSubmitOfHomeworkDTO detailSubmitOfHomeworkDTO(int homeworkid) {
		DetailSubmitOfHomeworkDTO detailSubmitOfHomeworkDTO = tutorRepository.detailSubmitOfHomeworkDTO(homeworkid);
		return detailSubmitOfHomeworkDTO;
	}
	public void TutorScorePointsForSubmit(TutorScorePointsForSubmitDTO tutorScorePointsForSubmitDTO)  {
		tutorRepository.TutorScorePointsForSubmit(tutorScorePointsForSubmitDTO);;
	}
	

	public ShowDetailVideoDTO showDetailVideo(int videoid) {
		ShowDetailVideoDTO result = tutorRepository.showDetailVideo(videoid);
		return result;
	}

	public List<DemoFollowClasscourseDTO> demoFollowClasscourse(int classcourseid) {
		List<DemoFollowClasscourseDTO> result = tutorRepository.demoFollowClasscourse(classcourseid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public List<ListStudentFinishedOfTutorDTO> listStudentFinishedOfTutorDTOs(int tutorid, int status, int page, int courseid) {
		int pages = (page - 1) * 10;
		List<ListStudentFinishedOfTutorDTO> result = tutorRepository.listStudentFinishedOfTutorDTOs(tutorid, status, pages,courseid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public long countstudent(int tutorid, int status,int courseid ) {
		long result = tutorRepository.countstudent(tutorid, status,courseid);
		long endpage = 0;
		endpage = result / 10;
		if (result % 10 != 0) {
			endpage++;
		}
		return endpage;
	}
	
	public TutorViewProfileStudentFinishedDTO tutorViewProfileStudentFinishedDTO(int studentid) {
		TutorViewProfileStudentFinishedDTO result = tutorRepository.tutorViewProfileStudentFinishedDTO(studentid);
		return result;
	}
	
	public List<ListStudentOfTutorDTO> listStudentOfTutor(int tutorid) {
		List<ListStudentOfTutorDTO> result = tutorRepository.listStudentOfTutor(tutorid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public TutorViewProfileStudentDTO tutorViewProfileStudent(int studentid) {
		TutorViewProfileStudentDTO result = tutorRepository.tutorViewProfileStudent(studentid);
		return result;
	}

	public List<TutorViewProfileTimelineStudentDTO> tutorViewProfileTimelineStudent(int bookid) {
		List<TutorViewProfileTimelineStudentDTO> result = tutorRepository.tutorViewProfileTimelineStudent(bookid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public DemoDetailOfClasscourseDTO demoDetailOfClasscourse(int demoid) {
		DemoDetailOfClasscourseDTO result = tutorRepository.demoDetailOfClasscourse(demoid);
		return result;
	}
	public BookTutorDTO booktutor(int tutorid, int classcouseid) {
		BookTutorDTO result = tutorRepository.booktutor(tutorid, classcouseid);
		if(result == null) {
			return null;
		}else {
			return result;
		}
	}
	
	public List<ListClassroomByTutorDTO> lisClassroomByTutorDTO(int exerciseid) {
		List<ListClassroomByTutorDTO> result = tutorRepository.listClassroomByTutor(exerciseid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public List<ListExerciseByTutorDTO> listExerciseByTutorDTO(int tutorid) {
		List<ListExerciseByTutorDTO> result = tutorRepository.listExerciseByTutorDTOs(tutorid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	

	public List<CourseOfTutorDTO> courOfTutor(int tutorid) {
		List<CourseOfTutorDTO> result = tutorRepository.courOfTutor(tutorid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
  }
	public long countpage(int classcourseid) {
		return tutorRepository.countpage(classcourseid);
	}
	
	public void updatesalary(String email) {
		tutorRepository.updatesalary(email);
	}
	public StudentViewDetailTutorDTO studentViewDetailTutorDTO(int tutorid) {
		StudentViewDetailTutorDTO result = tutorRepository.studentViewDetailTutorDTO(tutorid);
		if(result == null) {
			return null;
		}else {
			return result;
		}
	}
	public boolean checktutor(int tutorid) {
		return tutorRepository.checktutor(tutorid);
	}
	
	public void choiceteachtime(ChoicetimeDTO choicetimeDTO) {
		TeachTime teachTime = new TeachTime();
		teachTime.setTutorId(choicetimeDTO.getTutorid());
		teachTime.setLessonid(choicetimeDTO.getLessonid());
		teachTime.setTimeId(choicetimeDTO.getTimeid());
		teachTimeRepository.save(teachTime);
	}
	
	public List<TeachTime> listteachtime(int tutorid){
		List<TeachTime> result = teachTimeRepository.findAllByTutorId(tutorid);
		return result;
	}
	
	public void PaymentFortutor(PayForTutorDTO payForTutorDTO) {
		Payment payment = new Payment();
		payment.setTutorid(payForTutorDTO.getTutorid());
		payment.setMoney(payForTutorDTO.getMoney());
		payment.setBanknumber(payForTutorDTO.getBanknumber());
		payment.setBank(payForTutorDTO.getBank());
		paymentRepository.save(payment);
		paymentTutorRepository.paymenttutor(payForTutorDTO.getTutorid(), payForTutorDTO.getMoney());	
	}
	
	public PayForTutorDTO detailbank(int tutorid) {
		return paymentTutorRepository.showbank(tutorid);
	}
	
	public List<PayForTutorDTO> historypayment(int tutorid) {
		return paymentTutorRepository.historypayment(tutorid);
	}
	
	public List<ListFeedbackBookingOfTutorDTO> listFeedbackBookingOfTutor(int tutorid, int classcourseid) {
		List<ListFeedbackBookingOfTutorDTO> result = tutorRepository.listFeedbackBookingOfTutor(tutorid, classcourseid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
}
