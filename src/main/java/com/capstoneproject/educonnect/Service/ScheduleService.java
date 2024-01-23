package com.capstoneproject.educonnect.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.ScheduleDTO;
import com.capstoneproject.educonnect.Entity.ClassCourse;
import com.capstoneproject.educonnect.Repository.JPAClassCourseRepository;
import com.capstoneproject.educonnect.Repository.SchedulesRepostory;

@Service
public class ScheduleService {

	@Autowired
	private SchedulesRepostory schedulesRepostory;
	
	@Autowired
	private JPAClassCourseRepository jpaClassCourseRepository;

	public List<ScheduleDTO> listScheduled(int studentId, String week, String year) {
	    List<ClassCourse> result = jpaClassCourseRepository.findAll();
	    List<ScheduleDTO> schedule = new ArrayList<>();
	    for (ClassCourse classCourse : result) {
	        List<ScheduleDTO> scheduleDTOs = schedulesRepostory.scheduleStudent(studentId, week, year, classCourse.getClassCourseId());
	        List<ScheduleDTO> change = schedulesRepostory.schedulestudents(studentId, week, year, classCourse.getClassCourseId());
	        if (scheduleDTOs != null && !scheduleDTOs.isEmpty()) {
	        	schedule.addAll(scheduleDTOs);
	        	schedule.addAll(change);
	        }
	    }
	    return schedule;
	}
	public List<ScheduleDTO> listScheduledtutor (int tutorid, String week, String year) {
		List<ClassCourse> result = jpaClassCourseRepository.findAll();
	    List<ScheduleDTO> schedule = new ArrayList<>();
	    for (ClassCourse classCourse : result) {
	        List<ScheduleDTO> scheduleDTOs = schedulesRepostory.scheduletutor(tutorid, week, year, classCourse.getClassCourseId());
	        List<ScheduleDTO> change = schedulesRepostory.scheduletutors(tutorid, week, year, classCourse.getClassCourseId());
	        if (scheduleDTOs != null && !scheduleDTOs.isEmpty()) {
	        	schedule.addAll(scheduleDTOs);
	        	schedule.addAll(change);
	        }
	    }
	    return schedule;
	}
	
	public ScheduleDTO detailschdule (int tutorid, String date, int timeid) {
		return schedulesRepostory.detailschdule(tutorid, date, timeid);
	}
	
}
