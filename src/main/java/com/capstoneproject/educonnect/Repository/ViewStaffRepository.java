package com.capstoneproject.educonnect.Repository;

import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.AddLinkMeetDTO;
import com.capstoneproject.educonnect.DTO.ViewStaffDTO;

@Repository
public interface ViewStaffRepository {

	ViewStaffDTO ListviewStaff(int staffId);
	
	public void UpdateStaff(int staffid, String fullname,String file,
			String birthdate, String city, String wards);
	
	void addlinkmeet(AddLinkMeetDTO addLinkMeetDTO);
}
