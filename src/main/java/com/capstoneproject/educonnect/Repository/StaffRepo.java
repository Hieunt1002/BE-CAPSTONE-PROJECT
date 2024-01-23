package com.capstoneproject.educonnect.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.AcceptFileDTO;
import com.capstoneproject.educonnect.DTO.CountTutorAndStudentByClassDTO;
import com.capstoneproject.educonnect.DTO.ListStaffByAdminDTO;
import com.capstoneproject.educonnect.DTO.ListStudentOfStaffDTO;
import com.capstoneproject.educonnect.DTO.ListTutorOfStaffDTO;
import com.capstoneproject.educonnect.DTO.ListWaitForConfirmTutorDTO;
import com.capstoneproject.educonnect.DTO.PayForTuttorDTO;
import com.capstoneproject.educonnect.DTO.RadarChartDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileClasscourseTutorDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileStudentDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileStudentTrylearnDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTimelineStudentDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTimelineTutorDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTutorDTO;
import com.capstoneproject.educonnect.DTO.USalaryStaffDTO;
import com.capstoneproject.educonnect.DTO.USalaryTutorDTO;
import com.capstoneproject.educonnect.DTO.ViewStudentTrylearnDTO;
import com.capstoneproject.educonnect.Entity.User;

@Repository
public interface StaffRepo {
	List<ViewStudentTrylearnDTO> viewStudentTrylearn(int page);
	
	long counttrylearn();
	
	long counttrylearnpage();

	StaffViewProfileStudentTrylearnDTO staffViewProfileStudentTrylearnDTO(int trylearningid);

	List<ListTutorOfStaffDTO> listTutorOfStaff(int staffid, int page);
	
	long totalpagetutor(int staffid);

	public void blockTutor(int tutorid);
	
	public int checkflage(int tutor);

	public void updateSalary(USalaryTutorDTO uSalaryTutorDTO);

	StaffViewProfileTutorDTO staffViewProfileTutor(int tutorid);

	List<StaffViewProfileClasscourseTutorDTO> staffViewProfileClasscourseTutor(int tutorid);

	List<StaffViewProfileTimelineTutorDTO> staffViewProfileTimelineTutor(int tutorid);
	
	List<StaffViewProfileTimelineTutorDTO> learntimestudentandtutor(int bookid);

	List<ListStudentOfStaffDTO> listStudentOfStaff(int page);
	
	long pagestudent();

	public void blockStudent(int studentid);
	
	long checkblockstudent(int studentid);

	StaffViewProfileStudentDTO staffViewProfileStudent(int studentid);

	List<StaffViewProfileTimelineStudentDTO> staffViewProfileTimelineStudent(int studentid);

	User detaiUser(String email);

	List<ListStaffByAdminDTO> listStaff(int page);

	long totalpage();

	public void updateSalaryStaff(USalaryStaffDTO uSalaryStaffDTO);

	public void deleteStaff(int staffid);

	long countWaitForConfirmTutor();

	List<ListWaitForConfirmTutorDTO> listWaitForConfirmTutor(int page);
	
	long pageWaitForConfirmTutor();

	public void confirmTutor(int tutor, int staffid, float price);
	
	long countTutorregistersforlessons();

	List<PayForTuttorDTO> payfortutor(int staffid, int page);
	
	long pagepay(int staffid);
	
	public void accept(int tutorid);
	
	long checkaddcourse(int tutorid, int classcourseid);
	
	void accpettrylean(int studentid, int status, String date);
	
	void acceptfile(AcceptFileDTO acceptFileDTO);
	
	void accepttutor(int tutor, int status, int staffid, float price, int experience);
	
	void deletetutorregiter(int tutorid);
	
	List<CountTutorAndStudentByClassDTO> countstudentandtutor();
	
	long counttutor();
	
	long countstudent();
	
	long countstaff();
	
	List<RadarChartDTO> radarChart();
	
	List<RadarChartDTO> radarChartAdmin();
}
