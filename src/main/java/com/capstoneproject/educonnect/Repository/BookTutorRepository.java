package com.capstoneproject.educonnect.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.BookLesson;
import com.capstoneproject.educonnect.DTO.InforDTO;
import com.capstoneproject.educonnect.DTO.ListTimelineOfTutorDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTimelineTutorDTO;

@Repository
public interface BookTutorRepository {
	public void payDone(int studentid, int status);
	
	public void payDonebank(int studentid, int status);
	
	public String mailStudent(int studentid);
	
	public String mailTutorByStudent(int studentid);
	
	public float pricecourse(int studentid);
	
	public InforDTO inforstudent(int studentid);
	
	public InforDTO infortutor(int studentid);
	
	List<ListTimelineOfTutorDTO> listTimelineOfTutor(int tutorid);
	
	List<BookLesson> listtimeandlesson(int tutor, int studentid);
	
	void acceptcardpaymet(int bookid);
	
	void cancelbook(int studentid);
	
	void cancelcardpay(int bookid);
	
	void deletetimebook(int student);
	
	void deletetimebookPay(int bookid);
	
	int bookid (int studentid);
	
	int checktime(int bookid);
	
	void checkcalender(int bookid);
	
	String emailStudent(int bookid);
	
	List<StaffViewProfileTimelineTutorDTO> learntimestudentandtutor(int studentid);
	
	List<StaffViewProfileTimelineTutorDTO> learntimestudent(int studentid);
	
	String emailTutor(int bookid);
	
	void deletecalender(int timeid, int lessonid, int tutorid);
	
	boolean checkbook(int studentid);
}
