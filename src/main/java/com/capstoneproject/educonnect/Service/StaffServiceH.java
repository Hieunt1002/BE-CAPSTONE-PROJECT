package com.capstoneproject.educonnect.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.AcceptFileDTO;
import com.capstoneproject.educonnect.DTO.AcceptTutorDTO;
import com.capstoneproject.educonnect.DTO.AcceptutorRegisterDTO;
import com.capstoneproject.educonnect.DTO.CountTutorAndStudentByClassDTO;
import com.capstoneproject.educonnect.DTO.ListStudentOfStaffDTO;
import com.capstoneproject.educonnect.DTO.ListTutorOfStaffDTO;
import com.capstoneproject.educonnect.DTO.ListTutorRegistersForLessonsDTO;
import com.capstoneproject.educonnect.DTO.ListWaitForConfirmTutorDTO;
import com.capstoneproject.educonnect.DTO.ManageStudentRegistrationForCoursesDTO;
import com.capstoneproject.educonnect.DTO.PayForTuttorDTO;
import com.capstoneproject.educonnect.DTO.PieChartDTO;
import com.capstoneproject.educonnect.DTO.RadarChartDTO;
import com.capstoneproject.educonnect.DTO.StaffStatisticsByMonthDTO;
import com.capstoneproject.educonnect.DTO.StaffStatisticsByYearDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileClasscourseTutorDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileStudentDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileStudentTrylearnDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTimelineStudentDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTimelineTutorDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTutorDTO;
import com.capstoneproject.educonnect.DTO.USalaryTutorDTO;
import com.capstoneproject.educonnect.DTO.ViewStudentTrylearnDTO;
import com.capstoneproject.educonnect.Entity.Teach;
import com.capstoneproject.educonnect.Repository.AddClasscourseForTutor;
import com.capstoneproject.educonnect.Repository.ListTutorRegistersForLessons;
import com.capstoneproject.educonnect.Repository.ManageStudentRegistrationForCourses;
import com.capstoneproject.educonnect.Repository.StaffRepo;
import com.capstoneproject.educonnect.Repository.StaffStatistics;

@Service
public class StaffServiceH {
	@Autowired
	private StaffRepo staffRepo;
	
	private final JavaMailSender mailSender;
	
	@Autowired
	private ManageStudentRegistrationForCourses manageStudentRegistrationForCourses;
	
	@Autowired
	private ListTutorRegistersForLessons listTutorRegistersForLessons;
	
	@Autowired
	private AddClasscourseForTutor addClasscourseForTutor;
	
	@Autowired
	private StaffStatistics  staffStatistics;
  
	public StaffServiceH(JavaMailSender mailSender) {
		this.mailSender = mailSender;
  }
	
	public StaffStatisticsByMonthDTO staffStatisticsByCurrentMonth(Long staffId){
		StaffStatisticsByMonthDTO result = staffStatistics.staffStatisticsByCurrentMonth(staffId);;
			if (result == null) {
		        return null;
		    } else {
		        return result;
		    }
	}
	
	public List<CountTutorAndStudentByClassDTO> countstudentandtutor(){
		return staffRepo.countstudentandtutor();
	}
	
	public StaffStatisticsByMonthDTO staffStatisticsByPreviousMonth(Long staffId){
		StaffStatisticsByMonthDTO result = staffStatistics.staffStatisticsByPreviousMonth(staffId);;
			if (result == null) {
		        return null;
		    } else {
		        return result;
		    }
	}
	
	public List<StaffStatisticsByYearDTO> staffStatisticsByYear() {
		List<StaffStatisticsByYearDTO> result = staffStatistics.staffStatisticsByYear();
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public List<PieChartDTO> pieChart (){
		List<PieChartDTO> result = new ArrayList<>();
		long student = staffRepo.countstudent();
		long tutor = staffRepo.counttutor();
		result.add(new PieChartDTO("Student", student, "#0088FE"));
		result.add(new PieChartDTO("Tutor", tutor, "#FF0000"));
		return result;
	}
	
	public List<RadarChartDTO> radarChart(){
		return staffRepo.radarChart();
	}
	
	public List<ManageStudentRegistrationForCoursesDTO> listManageStudent(int page) {
		List<ManageStudentRegistrationForCoursesDTO> result = manageStudentRegistrationForCourses.getList(page);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public ManageStudentRegistrationForCoursesDTO detailManageStudent(int bookid) {
		return manageStudentRegistrationForCourses.detailregister(bookid);
	}
	
	public long totalPageStudentRegistrationForCourses() {
		long result = manageStudentRegistrationForCourses.countStudentRegistrationForCourses();
		long endpage = 0;
		endpage = result / 10;
		if (result % 10 != 0) {
			endpage++;
		}
		return endpage;
	}
	
	public long totalPageStudentRegistration() {
		long result = manageStudentRegistrationForCourses.countStudentRegistration();
		return result;
	}
	
	public long countTutorRegistersForLessons(int staffid) {
		return listTutorRegistersForLessons.countTutorRegistersForLessons(staffid);	 
	}
	
	public List<ListTutorRegistersForLessonsDTO> listTutorRegistersForLessons(int staffid, int page) {
		List<ListTutorRegistersForLessonsDTO> result = listTutorRegistersForLessons.getListTutorRegistersForLessons(staffid,page);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public long totalPageTutorRegistersForLessons(int staffid) {
		long result = listTutorRegistersForLessons.countTutorRegistersForLessons(staffid);
		long endpage = 0;
		endpage = result / 10;
		if (result % 10 != 0) {
			endpage++;
		}
		return endpage;
	}
	
	public boolean addClasscourseForTutor(int tutorid, int classcourseid) {
		long check = staffRepo.checkaddcourse(tutorid, classcourseid);
		if(check > 0) {
			return false;
		}else {
			Teach teach = new Teach();
			teach.setTutorid(tutorid);
			teach.setClasscourseid(classcourseid);
			addClasscourseForTutor.save(teach);
			return true;
		}
	}
	
	public List<ViewStudentTrylearnDTO> viewStudentTrylearn(int page) {
		List<ViewStudentTrylearnDTO> result = staffRepo.viewStudentTrylearn(page);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public long counttrylearn() {
		return staffRepo.counttrylearn();
	}
	
	public long counttrylearnpage() {
		return staffRepo.counttrylearnpage();
	}
	
	public StaffViewProfileStudentTrylearnDTO staffViewProfileStudentTrylearnDTO(int trylearningid) {
		StaffViewProfileStudentTrylearnDTO result = staffRepo.staffViewProfileStudentTrylearnDTO(trylearningid);
		return result;
	}
	
	public List<ListTutorOfStaffDTO> listTutorOfStaff(int staffid, int page) {
		List<ListTutorOfStaffDTO> result = staffRepo.listTutorOfStaff(staffid, page);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public void updateSalary(USalaryTutorDTO uSalaryTutorDTO) {
		staffRepo.updateSalary(uSalaryTutorDTO);
	}
	
	public StaffViewProfileTutorDTO staffViewProfileTutor(int tutorid) {
		StaffViewProfileTutorDTO result = staffRepo.staffViewProfileTutor(tutorid);
		return result;
	}

	public List<StaffViewProfileClasscourseTutorDTO> staffViewProfileClasscourseTutor(int tutorid) {
		List<StaffViewProfileClasscourseTutorDTO> result = staffRepo.staffViewProfileClasscourseTutor(tutorid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public List<StaffViewProfileTimelineTutorDTO> staffViewProfileTimelineTutor(int tutorid) {
		List<StaffViewProfileTimelineTutorDTO> result = staffRepo.staffViewProfileTimelineTutor(tutorid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public List<StaffViewProfileTimelineTutorDTO> learntimestudentandtutor(int bookid){
		return staffRepo.learntimestudentandtutor(bookid);
	}

	public List<ListStudentOfStaffDTO> listStudentOfStaff(int page) {
		List<ListStudentOfStaffDTO> result = staffRepo.listStudentOfStaff(page);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public StaffViewProfileStudentDTO staffViewProfileStudent(int studentid) {
		StaffViewProfileStudentDTO result = staffRepo.staffViewProfileStudent(studentid);
		return result;
	}

	public List<StaffViewProfileTimelineStudentDTO> staffViewProfileTimelineStudent(int studentid) {
		List<StaffViewProfileTimelineStudentDTO> result = staffRepo.staffViewProfileTimelineStudent(studentid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public boolean blockTutor(int tutorid) {
		int check = staffRepo.checkflage(tutorid);
		if(check > 0) {
			staffRepo.blockTutor(tutorid);
			return true;
		}
		return false;
	}
	
	public boolean blockStudent(int studentid) {
		long check = staffRepo.checkblockstudent(studentid);
		if(check > 0) {
			staffRepo.blockStudent(studentid);
			return true;
		}
		return false;
	}
	
	public long countWaitForConfirmTutor() {
		return staffRepo.countWaitForConfirmTutor();
	}
	
	public List<ListWaitForConfirmTutorDTO> listWaitForConfirmTutor(int page) {
		List<ListWaitForConfirmTutorDTO> result = staffRepo.listWaitForConfirmTutor(page);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public long pageForConfirmTutor() {
		long result = staffRepo.pageWaitForConfirmTutor();
		long endpage = 0;
		endpage = result / 10;
		if (result % 10 != 0) {
			endpage++;
		}
		return endpage;
	}
	
	public void confirmTutor(int tutorid, int staffid, float price)  {
		staffRepo.confirmTutor(tutorid, staffid, price);;
	}
	
	public List<PayForTuttorDTO> payfortutor(int staffid, int page){
		return staffRepo.payfortutor(staffid, page);
	}
	
	public long totalpay(int staffid) {
		long result = staffRepo.pagepay(staffid);
		long endpage = 0;
		endpage = result / 10;
		if (result % 10 != 0) {
			endpage++;
		}
		return endpage;
	}
	
	public long totalpayment(int staffid) {
		long result = staffRepo.pagepay(staffid);
		return result;
	}
	
	public void accept(int tutorid) {
		staffRepo.accept(tutorid);
	}
	
	public long totalpagetutor(int staffid) {
		long result = staffRepo.totalpagetutor(staffid);
		long endpage = 0;
		endpage = result / 10;
		if (result % 10 != 0) {
			endpage++;
		}
		return endpage;
	}
	
	public long pagestudent() {
		long result = staffRepo.pagestudent();
		long endpage = 0;
		endpage = result / 10;
		if (result % 10 != 0) {
			endpage++;
		}
		return endpage;
	}
	
	public void accpettrylean(int studentid, int status, String date, String emailstudent, String emailtutor, String text) {
		staffRepo.accpettrylean(studentid, status, date);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("EDU-CONNECT <capstoneeduconnect@gmail.com>");
		mailMessage.setTo(emailstudent, emailtutor);
		mailMessage.setSubject("Thông báo từ EDU-CONNECT");
		mailMessage.setText(text);
		mailSender.send(mailMessage);
	}
	
	public void acceptfile(AcceptFileDTO acceptFileDTO) {
		staffRepo.acceptfile(acceptFileDTO);
	}
	
	public void accepttutor(AcceptTutorDTO acceptTutorDTO) {
		staffRepo.accepttutor(acceptTutorDTO.getTutorid(), 2, acceptTutorDTO.getStaffid(), 0f, 0);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("EDU-CONNECT <capstoneeduconnect@gmail.com>");
		mailMessage.setTo(acceptTutorDTO.getEmail());
		mailMessage.setSubject("Thông báo từ EDU-CONNECT");
		mailMessage.setText(acceptTutorDTO.getMessage());
		mailSender.send(mailMessage);
	}
	public void accepttutorfinal(AcceptutorRegisterDTO acceptTutorDTO) {
		staffRepo.accepttutor(acceptTutorDTO.getTutorid(), 1, acceptTutorDTO.getStaffid(), acceptTutorDTO.getPrice(), acceptTutorDTO.getExperience());
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("EDU-CONNECT <capstoneeduconnect@gmail.com>");
		mailMessage.setTo(acceptTutorDTO.getEmail());
		mailMessage.setSubject("Thông báo từ EDU-CONNECT");
		mailMessage.setText(acceptTutorDTO.getMessage());
		mailSender.send(mailMessage);
	}
	
	public void deletetutorRegister(int tutorid) {
		staffRepo.deletetutorregiter(tutorid);
	}
}
