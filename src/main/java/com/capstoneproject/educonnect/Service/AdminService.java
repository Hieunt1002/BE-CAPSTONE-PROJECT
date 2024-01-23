package com.capstoneproject.educonnect.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.AdminStatisticsByMonthDTO;
import com.capstoneproject.educonnect.DTO.AdminStatisticsByYearDTO;
import com.capstoneproject.educonnect.DTO.ListStaffByAdminDTO;
import com.capstoneproject.educonnect.DTO.ManageStudentRegistrationForCoursesDTO;
import com.capstoneproject.educonnect.DTO.PieChartDTO;
import com.capstoneproject.educonnect.DTO.RadarChartDTO;
import com.capstoneproject.educonnect.DTO.USalaryStaffDTO;
import com.capstoneproject.educonnect.Entity.Staff;
import com.capstoneproject.educonnect.Entity.User;
import com.capstoneproject.educonnect.Repository.AddStaffRepository;
import com.capstoneproject.educonnect.Repository.AddUserRepository;
import com.capstoneproject.educonnect.Repository.AdminStatistics;
import com.capstoneproject.educonnect.Repository.StaffRepo;

@Service
public class AdminService {
	@Autowired
	private StaffRepo staffRepo;
	
	@Autowired
	private AddStaffRepository addStaffRepository;
	
	@Autowired
	private AddUserRepository addUserRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AdminStatistics adminStatistics;
	
	LocalDate today = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String currentDay = today.format(formatter);
	
	
	public List<PieChartDTO> pieChart (){
		List<PieChartDTO> result = new ArrayList<>();
		long student = staffRepo.countstudent();
		long tutor = staffRepo.counttutor();
		long staff = staffRepo.countstaff();
		result.add(new PieChartDTO("Student", student, "#0088FE"));
		result.add(new PieChartDTO("Tutor", tutor, "#FF0000"));
		result.add(new PieChartDTO("Staff", staff, "green"));
		return result;
	}
	
	public AdminStatisticsByMonthDTO adminStatisticsByCurrentMonth(){
		AdminStatisticsByMonthDTO result = adminStatistics.adminStatisticsByCurrentMonth();;
			if (result == null) {
		        return null;
		    } else {
		        return result;
		    }
	}
	
	public AdminStatisticsByMonthDTO adminStatisticsByPreviousMonth(){
		AdminStatisticsByMonthDTO result = adminStatistics.adminStatisticsByPreviousMonth();;
			if (result == null) {
		        return null;
		    } else {
		        return result;
		    }
	}
	
	public List<AdminStatisticsByYearDTO> adminStatisticsByYear() {
		List<AdminStatisticsByYearDTO> result = adminStatistics.adminStatisticsByYear();
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public List<RadarChartDTO> radarChartAdmin(){
		return staffRepo.radarChartAdmin();
	}
	
	public void AddStaff(String fullName, String email, String password, 
			String phone, int gender, String img, 
			String birthdate, String city, String wards, float salary,
			int experience) {
		User user= new User();
		user.setCreatedate(currentDay);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode((password)));
		user.setFullname(fullName);
		user.setPhone(phone);
		user.setGender(gender);
		user.setRole(3);
		user.setStatus(1);
		addUserRepository.save(user);
		
		Staff staff = new Staff();
		User userAdd = staffRepo.detaiUser(email);
		staff.setUser(userAdd);
		staff.setStaffid(userAdd.getUserid());
		staff.setBirthdate(birthdate);
		staff.setImg(img);
		staff.setCity(city);
		staff.setWards(wards);
		staff.setExperience(experience);
		staff.setSalary(salary);
		addStaffRepository.save(staff);	
	}
	
	public List<ListStaffByAdminDTO> listStaff(int page) {
		List<ListStaffByAdminDTO> result = staffRepo.listStaff(page);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	
	public void updateSalaryStaff(USalaryStaffDTO uSalaryStaffDTO) {
		staffRepo.updateSalaryStaff(uSalaryStaffDTO);
	}
	
	public void deleteStaff(int staffid) {
		staffRepo.deleteStaff(staffid);;
	}
	
	public long totalpage() {
		return staffRepo.totalpage();
	}
}
