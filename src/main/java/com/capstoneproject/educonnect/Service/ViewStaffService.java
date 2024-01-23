package com.capstoneproject.educonnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.AddLinkMeetDTO;
import com.capstoneproject.educonnect.DTO.ViewStaffDTO;
import com.capstoneproject.educonnect.Repository.ViewStaffRepository;

@Service
public class ViewStaffService {

	@Autowired
	private ViewStaffRepository viewStaffRepository;
	
	public ViewStaffDTO ViewInfoStaff (int staffId){
		return viewStaffRepository.ListviewStaff(staffId);
	}
	
	public void UpdateStaff(int staffid, String fullname,String file,
			String birthdate, String city, String wards) {
		viewStaffRepository.UpdateStaff(staffid, fullname, file, birthdate, city, wards);;
	}
	
	public void addlinkmeet(AddLinkMeetDTO addLinkMeetDTO) {
		viewStaffRepository.addlinkmeet(addLinkMeetDTO);
	}
}
