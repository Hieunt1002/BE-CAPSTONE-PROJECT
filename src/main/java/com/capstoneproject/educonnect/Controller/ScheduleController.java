package com.capstoneproject.educonnect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.educonnect.DTO.ChangeCalenderDTO;
import com.capstoneproject.educonnect.DTO.EmailDTO;
import com.capstoneproject.educonnect.DTO.ScheduleDTO;
import com.capstoneproject.educonnect.Repository.SchedulesRepostory;
import com.capstoneproject.educonnect.Service.BookingService;
import com.capstoneproject.educonnect.Service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private BookingService bookingService;
	
	
	@GetMapping("/studentschedule")
	public List<ScheduleDTO> studentschedule(int studentid, String week, String year){
		return scheduleService.listScheduled(studentid, week, year);
	}
	
	@GetMapping("/studentscheduletutor")
	public List<ScheduleDTO> studentscheduletutor(int tutorid, String week, String year){
		return scheduleService.listScheduledtutor(tutorid, week, year);
	}
	
	@PostMapping("/changecalender")
	public void changecalender(@RequestBody ChangeCalenderDTO changeCalenderDTO) {
		bookingService.changecalender(changeCalenderDTO);
	}
	
	@GetMapping("/detailschedule")
	public ScheduleDTO detailschedule (int tutorid, String date, int timeid) {
		return scheduleService.detailschdule(tutorid, date, timeid);
	}
	
	@DeleteMapping("/deletecalender/{timeid}/{lessonid}/{tutorid}")
	public void deletecalender(
	    @PathVariable("timeid") int timeid,
	    @PathVariable("lessonid") int lessonid,
	    @PathVariable("tutorid") int tutorid) {
	    bookingService.deletecalender(timeid, lessonid, tutorid);
	}

	
}
