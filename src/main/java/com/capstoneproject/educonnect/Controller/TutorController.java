package com.capstoneproject.educonnect.Controller;

import java.util.List;

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
import com.capstoneproject.educonnect.Entity.TeachTime;
import com.capstoneproject.educonnect.Service.FileService;
import com.capstoneproject.educonnect.Service.TutorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/educonnect")
@AllArgsConstructor
public class TutorController {

	private final FileService fileService;
	@Autowired
	private TutorService tutorService;

	@GetMapping("/tutor/top3")
	public List<Top3TutorDTO> listTop3Tutor() {
		return tutorService.listTop3Tutor();
	}

	@GetMapping("/viewTutor")
	public ViewTutorDTO listViewTutor(int tutorId) {
		return tutorService.listViewTutor(tutorId);
	}
	
	@GetMapping("/viewtutorcourse")
	public VTutorExerciseDTO viewtutor(@RequestParam int classcourseid, @RequestParam int tutorid) {
		return tutorService.viewtutor(classcourseid, tutorid);
	}

	@GetMapping("/feedbackofcourse/{tutorid}/{classcourseid}")
	public List<ListFeedbackBookingOfTutorDTO> listFeedbackBookingOfTutor(@PathVariable("tutorid") int tutorid, 
			@PathVariable("classcourseid") int classcourseid) {
		return tutorService.listFeedbackBookingOfTutor(tutorid, classcourseid);
	}
	
	@GetMapping("/viewscreentutor")
	public List<ViewScreenTutorDTO> ViewScreenTutor(int tutorid) {
		return tutorService.ViewScreenTutor(tutorid);
	}

	@GetMapping("/studentviewdetailtutor")
	public StudentViewDetailTutorDTO studentViewDetailTutorDTO(@RequestParam int tutorid) {
		return tutorService.studentViewDetailTutorDTO(tutorid);
	}

	@GetMapping("/countpage")
	public long countpage(@RequestParam int classcourseid) {
		return tutorService.countpage(classcourseid);
	}

	@PutMapping("/UpdateTutor")
	public void UpdateTutor(@RequestParam int tutorid, @RequestParam String fullname, @RequestParam String phone,
			@RequestPart(value = "file", required = false) MultipartFile file, @RequestParam int gender,
			@RequestParam String birthdate, @RequestParam String city, @RequestParam String wards) {
		ViewTutorDTO detail = listViewTutor(tutorid);
		if (file == null || file.isEmpty()) {
			tutorService.UpdateTutor(tutorid, fullname, phone, detail.getImg() == null ? null : detail.getImg(), gender, birthdate, city, wards);
		} else {
			if(detail.getImg() == null) {
				fileService.saveUser(file, tutorid);
				tutorService.UpdateTutor(tutorid, fullname, phone, file.getOriginalFilename(), gender, birthdate, city,
						wards);
			}else {
				fileService.deleteAll(detail.getImg());
				fileService.saveUser(file, tutorid);
				tutorService.UpdateTutor(tutorid, fullname, phone, file.getOriginalFilename(), gender, birthdate, city,
						wards);
			}
		}
	}

	@GetMapping("/ViewTutorForStudent")
	public List<TutorForStudentDTO> TutorForStudent(@RequestParam int tutorid) {
		return tutorService.TutorForStudent(tutorid);
	}

	@GetMapping("/ListAllDecsTutor")
	public List<DescListAllTutorDTO> DescListAllTutor(@RequestParam int courseid,
			@RequestParam(required = false) Integer page) {
		int pageNumber = (page != null) ? page : 1;
		return tutorService.DescListAllTutor(courseid, pageNumber);
	}

	@GetMapping("/tutor/course")
	public List<CourseAllByTutor> count(@RequestParam int tutorid) {
		return tutorService.AllCourseByTutor(tutorid);
	}

	@GetMapping("/tutor/listcourse")
	public List<CourseOfTutorDTO> courOfTutor(@RequestParam int tutorid) {
		return tutorService.courOfTutor(tutorid);
	}

	@GetMapping("/tutor/homework/detailhomework")
	public ShowDetailHomeworkDTO showDetailHomework(@RequestParam int homeworkid) {
		return tutorService.showDetailHomework(homeworkid);
	}

	// list exercise theo tutorid
	@GetMapping("/tutor/exercise/{id}")
	public List<ListExerciseByTutorDTO> listExerciseByTutorDTOs(@PathVariable("id") int tutorid) {
		return tutorService.listExerciseByTutorDTO(tutorid);
	}

	@GetMapping("/tutor/exercise/classroom/{id}")
	public List<ListClassroomByTutorDTO> lisClassroomByTutorDTOs(@PathVariable("id") int exerciseid) {
		return tutorService.lisClassroomByTutorDTO(exerciseid);
	}

	@GetMapping("/tutor/homework/detailsubmit")
	public DetailSubmitOfHomeworkDTO detailSubmitOfHomeworkDTO(@RequestParam int homeworkid) {
		return tutorService.detailSubmitOfHomeworkDTO(homeworkid);
	}

	@PutMapping("/tutor/homework/detailsubmit/scorepoints")
	public void tutorScorePointsForSubmit(@RequestBody TutorScorePointsForSubmitDTO tutorScorePointsForSubmitDTO) {
		tutorService.TutorScorePointsForSubmit(tutorScorePointsForSubmitDTO);
	}

	@GetMapping("/tutor/video")
	public ShowDetailVideoDTO showDetailVideo(@RequestParam int videoid) {
		return tutorService.showDetailVideo(videoid);
	}

	@GetMapping("/tutor/demo")
	public List<DemoFollowClasscourseDTO> demoFollowClasscourse(@RequestParam int classcourseid) {
		return tutorService.demoFollowClasscourse(classcourseid);
	}

	@GetMapping("/tutor/demo/demodetail")
	public DemoDetailOfClasscourseDTO demoDetalOfClasscourse(@RequestParam int demoid) {
		return tutorService.demoDetailOfClasscourse(demoid);
	}

	@GetMapping("/tutor/studentfinished")
	public List<ListStudentFinishedOfTutorDTO> listStudentFinishedOfTutorDTOs(@RequestParam int tutorid,@RequestParam int status,@RequestParam int page,@RequestParam int courseid) {
		return tutorService.listStudentFinishedOfTutorDTOs(tutorid, status, page,courseid);
	}
	
	@GetMapping("/countstudent")
	public long countstudent(@RequestParam int tutorid,@RequestParam int status,@RequestParam int courseid) {
		return tutorService.countstudent(tutorid, status,courseid);
	}

	@GetMapping("/tutor/studentfinished/viewprofile")
	public TutorViewProfileStudentFinishedDTO tutorViewProfileStudentFinishedDTO(@RequestParam int studentid) {
		return tutorService.tutorViewProfileStudentFinishedDTO(studentid);
	}

	@GetMapping("/tutor/student")
	public List<ListStudentOfTutorDTO> listStudentOfTutor(@RequestParam int tutorid) {
		return tutorService.listStudentOfTutor(tutorid);
	}

	@GetMapping("/tutor/student/viewprofile")
	public TutorViewProfileStudentDTO tutorViewProfileStudent(@RequestParam int studentid) {
		return tutorService.tutorViewProfileStudent(studentid);
	}

	@GetMapping("/tutor/student/viewprofile/timeline")
	public List<TutorViewProfileTimelineStudentDTO> tutorViewProfileTimelineStudent(@RequestParam int bookid) {
		return tutorService.tutorViewProfileTimelineStudent(bookid);
	}

	@GetMapping("/tutor/booktutor")
	public BookTutorDTO booktutor(@RequestParam int tutorid, @RequestParam int classcourseid) {
		return tutorService.booktutor(tutorid, classcourseid);
	}
	
	@GetMapping("/checktutor")
	public boolean checktutor(@RequestParam int tutorid) {
		return tutorService.checktutor(tutorid);
	}
	
	@PostMapping("/choicetime")
	public void choicetime(@RequestBody ChoicetimeDTO choicetimeDTO) {
		tutorService.choiceteachtime(choicetimeDTO);
	}
	
	@GetMapping("/listteachtime")
	public List<TeachTime> listteachtime(@RequestParam int tutorid){
		return tutorService.listteachtime(tutorid);
	}
	
	@PostMapping("/paymenttutor")
	public void paymenttutor(@RequestBody PayForTutorDTO payForTutorDTO) {
		tutorService.PaymentFortutor(payForTutorDTO);
	}
	
	@GetMapping("/showbank")
	public PayForTutorDTO showbank(@RequestParam int tutorid) {
		return tutorService.detailbank(tutorid);
	}
	
	@GetMapping("/historypay")
	public List<PayForTutorDTO> historypay(@RequestParam int tutorid) {
		return tutorService.historypayment(tutorid);
	}
}
